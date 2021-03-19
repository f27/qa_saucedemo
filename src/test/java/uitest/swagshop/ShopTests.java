package uitest.swagshop;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uitest.TestBase;

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
    void addToCartByNameTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.addToCartFromMain(DEFAULT_ITEM);
        shoppage.assertAddToCartFromMain(DEFAULT_ITEM);
    }

    @Test
    void pictureTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.checkPicture(DEFAULT_ITEM, Configuration.baseUrl + DEFAULT_ITEM_PICTURE);
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
        shoppage.assertSortLowToHigh();
    }

    @Test
    void sortHighToLowTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.sortHighToLow();
        shoppage.assertSortHighToLow();
    }

    @Test
    void socialButtonsTest(){
        shoppage = new ShopPage();
        open(DEFAULT_INVENTORY_PAGE);

        shoppage.checkSocialButtons();
    }
}
