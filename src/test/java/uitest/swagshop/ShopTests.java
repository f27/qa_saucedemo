package uitest.swagshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.CartPage;
import uitest.swagshop.pages.LoginPage;
import uitest.swagshop.pages.ShopPage;

import static com.codeborne.selenide.Selenide.*;
import static uitest.TestData.*;

public class ShopTests extends TestBase {
    ShopPage shoppage;

    @BeforeAll
    static void login(){
        open("/");

        new LoginPage().login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToMain(){
        open(DEFAULT_INVENTORY_PAGE);
    }

    @Test
    void addAndRemoveFromCartTest(){
        CartPage cart;
        shoppage = new ShopPage();
        cart = new CartPage();

        shoppage.addToCart(DEFAULT_ITEM);
        shoppage.assertAddToCart(DEFAULT_ITEM);

        shoppage.goToCart();

        cart.cartShouldHave(DEFAULT_ITEM);
        cart.continueShopping();

        shoppage.removeFromCart(DEFAULT_ITEM);
        shoppage.assertRemoveFromCart(DEFAULT_ITEM);

        shoppage.goToCart();

        cart.cartShouldNotHave(DEFAULT_ITEM);
        cart.continueShopping();
    }

    @Test
    void pictureTest(){
        shoppage = new ShopPage();

        shoppage.checkPicture(DEFAULT_ITEM, DEFAULT_ITEM_PICTURE);
    }

    @Test
    void labelTest(){
        shoppage = new ShopPage();

        shoppage.checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    void pictureAltTest(){
        shoppage = new ShopPage();

        shoppage.checkAltForPicture(DEFAULT_ITEM);
    }

    @Test
    void priceTest(){
        shoppage = new ShopPage();

        shoppage.checkPrice(DEFAULT_ITEM, DEFAULT_ITEM_PRICE);
    }

    @Test
    void sortAtoZTest(){
        shoppage = new ShopPage();

        shoppage.sortAtoZ();
        shoppage.assertSortAtoZ(DEFAULT_A_ITEM, DEFAULT_Z_ITEM);
    }

    @Test
    void sortZtoATest(){
        shoppage = new ShopPage();

        shoppage.sortZtoA();
        shoppage.assertSortZtoA(DEFAULT_A_ITEM, DEFAULT_Z_ITEM);
    }

    @Test
    void sortLowToHighTest(){
        shoppage = new ShopPage();

        shoppage.sortLowToHigh();
        shoppage.assertSortLowToHigh(DEFAULT_CHEAP_ITEM, DEFAULT_EXPENSIVE_ITEM);
    }

    @Test
    void sortHighToLowTest(){
        shoppage = new ShopPage();

        shoppage.sortHighToLow();
        shoppage.assertSortHighToLow(DEFAULT_CHEAP_ITEM, DEFAULT_EXPENSIVE_ITEM);
    }

    @Test
    void socialButtonsTest(){
        shoppage = new ShopPage();

        shoppage.checkSocialButtons();
    }
}
