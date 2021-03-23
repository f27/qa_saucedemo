package uitest.swagshop;

import org.junit.jupiter.api.*;
import uitest.TestBase;
import uitest.swagshop.pages.CartPage;
import uitest.swagshop.pages.CheckOutPage;
import uitest.swagshop.pages.LoginPage;
import uitest.swagshop.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

@DisplayName("Cart tests")
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
        shopPage = cartPage.continueShopping();
        shopPage.checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    @DisplayName("Testing checkout")
    void checkOutTest() {
        checkOutPage = cartPage.checkOut();
        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }

    @Test
    @DisplayName("Testing item is in cart")
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
    @DisplayName("Checking item's name")
    void checkItemNameTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkName(DEFAULT_ITEM);
    }

    @Test
    @DisplayName("Checking item's description")
    void checkItemDescTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkDesc(DEFAULT_ITEM_DESCRIPTION);
    }

    @Test
    @DisplayName("Checking item's price")
    void checkItemPriceTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkPrice(DEFAULT_ITEM_PRICE);
    }

    @Test
    @DisplayName("Checking item's quantity")
    void checkItemQuantityTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.checkQuantity("1");
    }

    @Test
    @DisplayName("Testing remove from cart")
    void removeItemTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        cartPage.itemInCart(DEFAULT_ITEM);
        cartPage.clear();
    }

    @Test
    @DisplayName("Testing checkout item")
    void checkOutItemTest() {
        shopPage = cartPage.continueShopping();

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();

        checkOutPage = cartPage.checkOut();

        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }
}
