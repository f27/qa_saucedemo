package uitest.swagshop;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uitest.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class ShopTests extends TestBase {
    ShopPage shoppage;

    @BeforeAll
    static void login(){
        open("/");

        new LoginPage().login("standard_user", "secret_sauce");
    }

    @Test
    void addToCartByNameTest(){
        shoppage = new ShopPage();
        open("/inventory.html");

        shoppage.addToCartFromMain("Sauce Labs Fleece Jacket");
        shoppage.assertAddToCartFromMain("Sauce Labs Fleece Jacket");
    }

    @Test
    void pictureTest(){
        shoppage = new ShopPage();
        open("/inventory.html");

        shoppage.checkPicture("Sauce Labs Fleece Jacket", Configuration.baseUrl + "/static/media/sauce-pullover-1200x1500.439fc934.jpg");
    }

    @Test
    void priceTest(){
        shoppage = new ShopPage();
        open("/inventory.html");

        shoppage.checkPrice("Sauce Labs Fleece Jacket", "49.99");
    }

    @Test
    void sortAtoZTest(){
        shoppage = new ShopPage();
        open("/inventory.html");

        shoppage.sortAtoZ();
        shoppage.assertSortAtoZ();
    }

    @Test
    void sortZtoATest(){
        shoppage = new ShopPage();
        open("/inventory.html");

        shoppage.sortZtoA();
        shoppage.assertSortZtoA();
    }

    @Test
    void sortLowToHighTest(){
        shoppage = new ShopPage();
        open("/inventory.html");

        shoppage.sortLowToHigh();
        shoppage.assertSortLowToHigh();
    }

    @Test
    void sortHighToLowTest(){
        shoppage = new ShopPage();
        open("/inventory.html");

        shoppage.sortHighToLow();
        shoppage.assertSortHighToLow();
    }

    @Test
    void socialButtonsTest(){
        shoppage = new ShopPage();
        open("/inventory.html");

        shoppage.checkSocialButtons();
    }
}
