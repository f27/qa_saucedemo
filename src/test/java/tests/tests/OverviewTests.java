package tests.tests;

import org.junit.jupiter.api.*;
import tests.TestBase;
import tests.pages.*;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Overview tests")
public class OverviewTests extends TestBase {
    OverviewPage overviewPage;

    @BeforeAll
    static void login() {
        open("/", LoginPage.class)
                .login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToOverview() {
        overviewPage = open(DEFAULT_CHECKOUT_PAGE, CheckOutPage.class)
                .fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP)
                .submitForm();
    }

    @AfterEach
    void clearCart() {
        new CartPage().cartClear();
    }

    @Test
    @DisplayName("Testing header")
    void checkSubHeaderTest() {
        overviewPage.checkSubHeader(DEFAULT_CHECKOUT_OVERVIEW_LABEL);
    }

    @Test
    @DisplayName("Social buttons")
    void socialButtonsTest() {
        overviewPage.checkSocialButtons();
    }

    @Test
    @DisplayName("Testing empty checkout")
    void emptyCheckOut() {
        overviewPage.checkEmpty()
                .checkSubHeader(DEFAULT_THANKS_LABEL);
    }

    @Test
    @DisplayName("Testing with item")
    void checkWithItem() {
        overviewPage.goToShop()
                .addToCart(DEFAULT_ITEM)
                .goToCart()
                .checkOut()
                .fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP)
                .submitForm()
                .checkItemsNameInOverview(DEFAULT_ITEM)
                .checkItemDesc(DEFAULT_ITEM_DESCRIPTION)
                .checkItemPrice(DEFAULT_ITEM_PRICE)
                .checkItemQuantity("1")
                .checkTax(DEFAULT_TAX)
                .checkItemsTotalPrice(DEFAULT_ITEM_PRICE)
                .checkPriceWithTaxes(DEFAULT_TOTAL)
                .finishClick()
                .checkSubHeader(DEFAULT_THANKS_LABEL);
    }
}
