package uitest.swagshop;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
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

    @Test
    void addAndRemoveFromCartOnMainTest(){
        CartPage cart;
        shoppage = new ShopPage();
        cart = new CartPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.addToCartOnMainClick(DEFAULT_ITEM);
        shoppage.assertAddToCartOnMain(DEFAULT_ITEM);

        shoppage.goToCart();

        cart.cartShouldHave(DEFAULT_ITEM);
        cart.continueShopping();

        shoppage.removeFromCartOnMainClick(DEFAULT_ITEM);
        shoppage.assertRemoveFromCartOnMain(DEFAULT_ITEM);

        shoppage.goToCart();

        cart.cartShouldNotHave(DEFAULT_ITEM);
        cart.continueShopping();
    }

    @Test
    void pictureTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.checkPicture(DEFAULT_ITEM, Configuration.baseUrl + DEFAULT_ITEM_PICTURE);
    }

    @Test
    void pictureAltTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.checkAltForPicture(DEFAULT_ITEM);
    }

    @Test
    void priceTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.checkPrice(DEFAULT_ITEM, DEFAULT_ITEM_PRICE);
    }

    @Test
    void sortAtoZTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.sortAtoZ();
        shoppage.assertSortAtoZ(DEFAULT_A_ITEM, DEFAULT_Z_ITEM);
    }

    @Test
    void sortZtoATest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.sortZtoA();
        shoppage.assertSortZtoA(DEFAULT_A_ITEM, DEFAULT_Z_ITEM);
    }

    @Test
    void sortLowToHighTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.sortLowToHigh();
        shoppage.assertSortLowToHigh(DEFAULT_CHEAP_ITEM, DEFAULT_EXPENSIVE_ITEM);
    }

    @Test
    void sortHighToLowTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.sortHighToLow();
        shoppage.assertSortHighToLow(DEFAULT_CHEAP_ITEM, DEFAULT_EXPENSIVE_ITEM);
    }

    @Test
    void socialButtonsTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.checkSocialButtons();
    }
}
