package tests.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.CartPage;
import tests.pages.FinishPage;
import tests.pages.LoginPage;
import tests.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Thanks page test")
public class FinishTests extends TestBase {

    @BeforeAll
    static void login() {
        open("/", LoginPage.class)
                .login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @Test
    @DisplayName("Testing thanks page")
    void hasThanksTest() {
        open(DEFAULT_THANKS_PAGE, FinishPage.class)
                .hasThanks();
    }

    @Test
    @DisplayName("Visit to thanks page should reset cart")
    void finishResetCartTest() {
        open(DEFAULT_INVENTORY_PAGE, ShopPage.class)
                .addToCart(DEFAULT_ITEM)
                .goToCart()
                .checkOut()
                .fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP)
                .submitForm()
                .finishClick()
                .goToShop()
                .goToCart()
                .isEmpty();
    }

    @Test
    @DisplayName("Direct visit to thanks page should not reset cart")
    void visitFinishNotResetCartTest() {
        open(DEFAULT_INVENTORY_PAGE, ShopPage.class)
                .addToCart(DEFAULT_ITEM);

        open(DEFAULT_THANKS_PAGE, FinishPage.class)
                .checkSubHeader(DEFAULT_THANKS_LABEL);

        open(DEFAULT_CART_PAGE, CartPage.class)
                .itemInCart(DEFAULT_ITEM)
                .cartClear();
    }

}
