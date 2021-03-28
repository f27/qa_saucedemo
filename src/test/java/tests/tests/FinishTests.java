package tests.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.*;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Thanks page test")
public class FinishTests extends TestBase {
    FinishPage finishPage;

    @BeforeAll
    static void login() {
        open("/", LoginPage.class)
                .login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @Test
    @DisplayName("Testing thanks page")
    void hasThanksTest() {
        finishPage = open(DEFAULT_THANKS_PAGE, FinishPage.class);
        finishPage.hasThanks();
    }

    @Test
    @DisplayName("Visit to thanks page should reset cart")
    void finishResetCartTest() {
        ShopPage shopPage = open(DEFAULT_INVENTORY_PAGE, ShopPage.class);

        shopPage.addToCart(DEFAULT_ITEM);
        CartPage cartPage = shopPage.goToCart();
        CheckOutPage checkOutPage = cartPage.checkOut();
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP);
        OverviewPage overviewPage = checkOutPage.submitForm();
        finishPage = overviewPage.finishClick();
        finishPage.checkSubHeader(DEFAULT_THANKS_LABEL);
        shopPage = finishPage.goToShop();
        cartPage = shopPage.goToCart();
        cartPage.isEmpty();
    }

    @Test
    @DisplayName("Direct visit to thanks page should not reset cart")
    void visitFinishNotResetCartTest() {
        ShopPage shopPage = open(DEFAULT_INVENTORY_PAGE, ShopPage.class);

        shopPage.addToCart(DEFAULT_ITEM);

        finishPage = open(DEFAULT_THANKS_PAGE, FinishPage.class);

        finishPage.checkSubHeader(DEFAULT_THANKS_LABEL);

        CartPage cartPage = open(DEFAULT_CART_PAGE, CartPage.class);

        cartPage.itemInCart(DEFAULT_ITEM);
        cartPage.clear();
    }

}
