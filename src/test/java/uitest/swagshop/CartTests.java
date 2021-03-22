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
    CartPage cartPage;
    ShopPage shopPage;
    CheckOutPage checkOutPage;


    @BeforeAll
    static void login() {
        LoginPage loginpage = open("/", LoginPage.class);
        loginpage.login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToCart() {
        cartPage = open(DEFAULT_CART_PAGE, CartPage.class);
    }

    @AfterEach
    void cleanCart() {
        cartPage.clear();
    }

    @Test
    void checkSubHeaderTest() {
        cartPage.checkSubHeader(DEFAULT_CART_LABEL);
    }

    @Test
    void socialButtonsTest() {
        cartPage.checkSocialButtons();
    }

    @Test
    void isEmptyTest() {
        cartPage.isEmpty();
    }

    @Test
    void continueShoppingTest() {
        shopPage = cartPage.continueShopping();
        shopPage.checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    void checkOutTest() {
        checkOutPage = cartPage.checkOut();
        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }

    @Test
    void itemInCartTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        shopPage.addToCart(DEFAULT_CHEAP_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.itemInCart(DEFAULT_ITEM);
        cartPage.itemInCart(DEFAULT_CHEAP_ITEM);

        cartPage.checkDesc(DEFAULT_ITEM_DESCRIPTION);
        cartPage.checkPrice(DEFAULT_ITEM_PRICE);
        cartPage.checkQuantity("1");
    }

    @Test
    void checkItemNameTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkName(DEFAULT_ITEM);
    }

    @Test
    void checkItemDescTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkDesc(DEFAULT_ITEM_DESCRIPTION);
    }

    @Test
    void checkItemPriceTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkPrice(DEFAULT_ITEM_PRICE);
    }

    @Test
    void checkItemQuantityTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkQuantity("1");
    }

    @Test
    void removeItemTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.itemInCart(DEFAULT_ITEM);
        cartPage.clear();
    }

    @Test
    void checkOutItemTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        checkOutPage = cartPage.checkOut();

        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }
}
