package tests.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.CartPage;
import tests.pages.LoginPage;
import tests.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Shop tests")
public class ShopTests extends TestBase {
    static ShopPage shopPage;
    CartPage cartPage;

    @BeforeAll
    static void login() {
        LoginPage loginpage = open("/", LoginPage.class);
        loginpage.login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToMain() {
        shopPage = open(DEFAULT_INVENTORY_PAGE, ShopPage.class);
    }

    @Test
    @DisplayName("Add and remove from cart")
    void addAndRemoveFromCartTest() {
        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();
        cartPage.cartShouldHave(DEFAULT_ITEM);
        shopPage = cartPage.continueShopping();
        shopPage.removeFromCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();
        cartPage.cartShouldNotHave(DEFAULT_ITEM);
        shopPage = cartPage.continueShopping();
    }

    @Test
    @DisplayName("Testing picture")
    void pictureTest() {
        shopPage.checkPicture(DEFAULT_ITEM, DEFAULT_ITEM_PICTURE);
    }

    @Test
    @DisplayName("Checking page label")
    void labelTest() {
        shopPage.checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    @DisplayName("Testing picture's alt")
    void pictureAltTest() {
        shopPage.checkAltForPicture(DEFAULT_ITEM);
    }

    @Test
    @DisplayName("Testing price")
    void priceTest() {
        shopPage.checkPrice(DEFAULT_ITEM, DEFAULT_ITEM_PRICE);
    }

    @Test
    @DisplayName("Testing sort A to Z")
    void sortAtoZTest() {
        shopPage.sortAtoZ();
        shopPage.assertSortAtoZ(DEFAULT_A_ITEM, DEFAULT_Z_ITEM);
    }

    @Test
    @DisplayName("Testing sort Z to A")
    void sortZtoATest() {
        shopPage.sortZtoA();
        shopPage.assertSortZtoA(DEFAULT_A_ITEM, DEFAULT_Z_ITEM);
    }

    @Test
    @DisplayName("Testing sort low to high")
    void sortLowToHighTest() {
        shopPage.sortLowToHigh();
        shopPage.assertSortLowToHigh(DEFAULT_CHEAP_ITEM, DEFAULT_EXPENSIVE_ITEM);
    }

    @Test
    @DisplayName("Testing sort high to low")
    void sortHighToLowTest() {
        shopPage.sortHighToLow();
        shopPage.assertSortHighToLow(DEFAULT_CHEAP_ITEM, DEFAULT_EXPENSIVE_ITEM);
    }

    @Test
    @DisplayName("Testing social buttons")
    void socialButtonsTest() {
        shopPage.checkSocialButtons();
    }
}
