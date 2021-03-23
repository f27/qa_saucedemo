package uitest.swagshop;

import org.junit.jupiter.api.*;
import uitest.TestBase;
import uitest.swagshop.pages.*;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

@DisplayName("Overview tests")
public class OverviewTests extends TestBase {
    static OverviewPage overviewPage;
    ShopPage shopPage;
    CartPage cartPage;
    CheckOutPage checkOutPage;
    FinishPage finishPage;

    @BeforeAll
    static void login() {
        LoginPage loginpage = open("/", LoginPage.class);
        loginpage.login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToOverview() {
        checkOutPage = open(DEFAULT_CHECKOUT_PAGE, CheckOutPage.class);
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP);
        overviewPage = checkOutPage.submitForm();
    }

    @AfterEach
    void clearCart() {
        new CartPage().clear();
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
        finishPage = overviewPage.checkEmpty();
        finishPage.checkSubHeader(DEFAULT_THANKS_LABEL);
    }

    @Test
    @DisplayName("Testing with item")
    void checkWithItem() {
        shopPage = overviewPage.goToShop();
        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();
        checkOutPage = cartPage.checkOut();
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP);
        overviewPage = checkOutPage.submitForm();

        overviewPage.checkItemName(DEFAULT_ITEM);
        overviewPage.checkItemDesc(DEFAULT_ITEM_DESCRIPTION);
        overviewPage.checkItemPrice(DEFAULT_ITEM_PRICE);
        overviewPage.checkItemQuantity("1");
        overviewPage.checkTax(DEFAULT_TAX);
        overviewPage.checkItemsTotalPrice(DEFAULT_ITEM_PRICE);
        overviewPage.checkPriceWithTaxes(DEFAULT_TOTAL);
        overviewPage.finishClick();

        new FinishPage().checkSubHeader(DEFAULT_THANKS_LABEL);
    }
}
