package tests.tests;

import org.junit.jupiter.api.*;
import tests.TestBase;
import tests.pages.CartPage;
import tests.pages.CheckOutPage;
import tests.pages.LoginPage;
import tests.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Cart tests")
public class CartTests extends TestBase {
    CartPage cartPage;

    @BeforeAll
    static void login() {
        open("/", LoginPage.class)
                .login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
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
    @DisplayName("Checking page label")
    void checkSubHeaderTest() {
        cartPage.checkSubHeader(DEFAULT_CART_LABEL);
    }

    @Test
    @DisplayName("Testing social buttons")
    void socialButtonsTest() {
        cartPage.checkSocialButtons();
    }

    @Test
    @DisplayName("Checking cart is empty")
    void isEmptyTest() {
        cartPage.isEmpty();
    }

    @Test
    @DisplayName("Testing continue shopping button")
    void continueShoppingTest() {
        ShopPage shopPage = cartPage.continueShopping();
        shopPage.checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    @DisplayName("Testing checkout")
    void checkOutTest() {
        CheckOutPage checkOutPage = cartPage.checkOut();
        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }

    @Test
    @DisplayName("Testing item is in cart")
    void itemInCartTest() {
        ShopPage shopPage = cartPage.continueShopping();

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
    @DisplayName("Checking item's name")
    void checkItemNameTest() {
        ShopPage shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkName(DEFAULT_ITEM);
    }

    @Test
    @DisplayName("Checking item's description")
    void checkItemDescTest() {
        ShopPage shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkDesc(DEFAULT_ITEM_DESCRIPTION);
    }

    @Test
    @DisplayName("Checking item's price")
    void checkItemPriceTest() {
        ShopPage shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkPrice(DEFAULT_ITEM_PRICE);
    }

    @Test
    @DisplayName("Checking item's quantity")
    void checkItemQuantityTest() {
        ShopPage shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkQuantity("1");
    }

    @Test
    @DisplayName("Testing remove from cart")
    void removeItemTest() {
        ShopPage shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.itemInCart(DEFAULT_ITEM);
        cartPage.clear();
    }

    @Test
    @DisplayName("Testing checkout item")
    void checkOutItemTest() {
        ShopPage shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        CheckOutPage checkOutPage = cartPage.checkOut();

        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }
}
