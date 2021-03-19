package uitest.swagshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.CartPage;
import uitest.swagshop.pages.CheckOutPage;
import uitest.swagshop.pages.LoginPage;
import uitest.swagshop.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

public class CartTests extends TestBase {
    CartPage cartpage;

    @BeforeAll
    static void login(){
        open("/");

        new LoginPage().login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToCart(){
        open(DEFAULT_CART_PAGE);
    }

    @AfterEach
    void cleanCart(){
        cartpage = new CartPage();
        cartpage.clear();
    }

    @Test
    void checkSubHeaderTest(){
        cartpage = new CartPage();

        cartpage.checkSubHeader(DEFAULT_CART_LABEL);
    }

    @Test
    void socialButtonsTest(){
        cartpage = new CartPage();

        cartpage.checkSocialButtons();
    }

    @Test
    void isEmptyTest(){
        cartpage = new CartPage();

        cartpage.isEmpty();
    }

    @Test
    void continueShoppingTest(){
        cartpage = new CartPage();

        cartpage.continueShopping();
        new ShopPage().checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    void checkOutTest(){
        cartpage = new CartPage();

        cartpage.checkOut();
        new CheckOutPage().checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }

    @Test
    void itemInCartTest(){
        cartpage = new CartPage();
        ShopPage shoppage;

        cartpage.continueShopping();

        shoppage = new ShopPage();
        shoppage.addToCart(DEFAULT_ITEM);
        shoppage.addToCart(DEFAULT_CHEAP_ITEM);
        shoppage.goToCart();

        cartpage.itemInCart(DEFAULT_ITEM);
        cartpage.itemInCart(DEFAULT_CHEAP_ITEM);

        cartpage.checkDesc(DEFAULT_ITEM_DESCRIPTION);
        cartpage.checkPrice(DEFAULT_ITEM_PRICE);
        cartpage.checkQuantity("1");
    }
    @Test
    void checkItemNameTest(){
        cartpage = new CartPage();
        ShopPage shoppage;

        cartpage.continueShopping();

        shoppage = new ShopPage();
        shoppage.addToCart(DEFAULT_ITEM);
        shoppage.goToCart();

        cartpage.checkName(DEFAULT_ITEM);
    }

    @Test
    void checkItemDescTest(){
        cartpage = new CartPage();
        ShopPage shoppage;

        cartpage.continueShopping();

        shoppage = new ShopPage();
        shoppage.addToCart(DEFAULT_ITEM);
        shoppage.goToCart();

        cartpage.checkDesc(DEFAULT_ITEM_DESCRIPTION);
    }

    @Test
    void checkItemPriceTest(){
        cartpage = new CartPage();
        ShopPage shoppage;

        cartpage.continueShopping();

        shoppage = new ShopPage();
        shoppage.addToCart(DEFAULT_ITEM);
        shoppage.goToCart();

        cartpage.checkPrice(DEFAULT_ITEM_PRICE);
    }

    @Test
    void checkItemQuantityTest(){
        cartpage = new CartPage();
        ShopPage shoppage;

        cartpage.continueShopping();

        shoppage = new ShopPage();
        shoppage.addToCart(DEFAULT_ITEM);
        shoppage.goToCart();

        cartpage.checkQuantity("1");
    }

    @Test
    void removeItemTest(){
        cartpage = new CartPage();
        ShopPage shoppage;

        cartpage.continueShopping();

        shoppage = new ShopPage();
        shoppage.addToCart(DEFAULT_ITEM);
        shoppage.goToCart();

        cartpage.itemInCart(DEFAULT_ITEM);
        cartpage.clear();
    }

    @Test
    void checkOutItemTest(){
        cartpage = new CartPage();
        ShopPage shoppage;

        cartpage.continueShopping();

        shoppage = new ShopPage();
        shoppage.addToCart(DEFAULT_ITEM);
        shoppage.goToCart();

        cartpage.checkOut();

        new CheckOutPage().checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }
}
