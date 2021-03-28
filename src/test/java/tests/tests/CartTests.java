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
        cartPage.cartClear();
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
        cartPage.continueShopping()
                .checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    @DisplayName("Testing checkout")
    void checkOutTest() {
        cartPage.checkOut()
                .checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }

    @Test
    @DisplayName("Testing item is in cart")
    void itemInCartTest() {
        cartPage.continueShopping()
                .addToCart(DEFAULT_ITEM)
                .addToCart(DEFAULT_CHEAP_ITEM)
                .goToCart()
                .itemInCart(DEFAULT_ITEM)
                .itemInCart(DEFAULT_CHEAP_ITEM)
                .checkDesc(DEFAULT_ITEM_DESCRIPTION)
                .checkPrice(DEFAULT_ITEM_PRICE)
                .quantityShouldBe("1");
    }

    @Test
    @DisplayName("Checking item's name")
    void checkItemNameTest() {
        cartPage.continueShopping()
                .addToCart(DEFAULT_ITEM)
                .goToCart()
                .checkItemsInCartName(DEFAULT_ITEM);
    }

    @Test
    @DisplayName("Checking item's description")
    void checkItemDescTest() {
        cartPage.continueShopping()
                .addToCart(DEFAULT_ITEM)
                .goToCart()
                .checkDesc(DEFAULT_ITEM_DESCRIPTION);
    }

    @Test
    @DisplayName("Checking item's price")
    void checkItemPriceTest() {
        cartPage.continueShopping()
                .addToCart(DEFAULT_ITEM)
                .goToCart()
                .checkPrice(DEFAULT_ITEM_PRICE);
    }

    @Test
    @DisplayName("Checking item's quantity")
    void checkItemQuantityTest() {
        cartPage.continueShopping()
                .addToCart(DEFAULT_ITEM)
                .goToCart()
                .quantityShouldBe("1");
    }

    @Test
    @DisplayName("Testing remove from cart")
    void removeItemTest() {
        cartPage.continueShopping()
                .addToCart(DEFAULT_ITEM)
                .goToCart()
                .itemInCart(DEFAULT_ITEM)
                .cartClear();
    }

    @Test
    @DisplayName("Testing checkout item")
    void checkOutItemTest() {
        cartPage.continueShopping()
                .addToCart(DEFAULT_ITEM)
                .goToCart()
                .checkOut()
                .checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }
}
