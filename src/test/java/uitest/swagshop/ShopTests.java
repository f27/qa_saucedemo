package uitest.swagshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.CartPage;
import uitest.swagshop.pages.LoginPage;
import uitest.swagshop.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

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
    void pictureTest() {
        shopPage.checkPicture(DEFAULT_ITEM, DEFAULT_ITEM_PICTURE);
    }

    @Test
    void labelTest() {
        shopPage.checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    void pictureAltTest() {
        shopPage.checkAltForPicture(DEFAULT_ITEM);
    }

    @Test
    void priceTest() {
        shopPage.checkPrice(DEFAULT_ITEM, DEFAULT_ITEM_PRICE);
    }

    @Test
    void sortAtoZTest() {
        shopPage.sortAtoZ();
        shopPage.assertSortAtoZ(DEFAULT_A_ITEM, DEFAULT_Z_ITEM);
    }

    @Test
    void sortZtoATest() {
        shopPage.sortZtoA();
        shopPage.assertSortZtoA(DEFAULT_A_ITEM, DEFAULT_Z_ITEM);
    }

    @Test
    void sortLowToHighTest() {
        shopPage.sortLowToHigh();
        shopPage.assertSortLowToHigh(DEFAULT_CHEAP_ITEM, DEFAULT_EXPENSIVE_ITEM);
    }

    @Test
    void sortHighToLowTest() {
        shopPage.sortHighToLow();
        shopPage.assertSortHighToLow(DEFAULT_CHEAP_ITEM, DEFAULT_EXPENSIVE_ITEM);
    }

    @Test
    void socialButtonsTest() {
        shopPage.checkSocialButtons();
    }
}
