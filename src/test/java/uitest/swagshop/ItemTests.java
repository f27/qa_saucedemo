package uitest.swagshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.CartPage;
import uitest.swagshop.pages.ItemPage;
import uitest.swagshop.pages.LoginPage;
import uitest.swagshop.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

public class ItemTests extends TestBase {
    ItemPage itempage;

    @BeforeAll
    static void login(){
        open("/");

        new LoginPage().login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
        new ShopPage().goToItem(DEFAULT_ITEM);
    }

    @BeforeEach
    void goToItemPage(){
        open(DEFAULT_INVENTORY_PAGE);
        new ShopPage().goToItem(DEFAULT_ITEM);
    }

    @Test
    void itemNameTest(){
        itempage = new ItemPage();

        itempage.checkName(DEFAULT_ITEM);
    }

    @Test
    void itemDescTest(){
        itempage = new ItemPage();

        itempage.checkDesc(DEFAULT_ITEM_DESCRIPTION);
    }

    @Test
    void itemPriceTest(){
        itempage = new ItemPage();

        itempage.checkPrice(DEFAULT_ITEM_PRICE);
    }

    @Test
    void itemImgSrcTest(){
        itempage = new ItemPage();

        itempage.checkImgSrc(DEFAULT_ITEM_PICTURE);
    }

    @Test
    void itemImgAltTest(){
        itempage = new ItemPage();

        itempage.checkImgAlt(DEFAULT_ITEM);
    }

    @Test
    void backButtonTest(){
        itempage = new ItemPage();

        itempage.goBack();
        new ShopPage().checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    void checkHeaderName(){
        itempage = new ItemPage();

        itempage.checkHeaderName(DEFAULT_HEADER);
    }

    @Test
    void addAndRemoveFromCartTest(){
        CartPage cart;
        cart = new CartPage();

        itempage = new ItemPage();

        itempage.addToCart();
        itempage.goToCart();

        cart.cartShouldHave(DEFAULT_ITEM);
        cart.continueShopping();

        new ShopPage().goToItem(DEFAULT_ITEM);

        itempage.removeFromCart();
        itempage.goToCart();

        cart.cartShouldNotHave(DEFAULT_ITEM);
        cart.continueShopping();
    }
}
