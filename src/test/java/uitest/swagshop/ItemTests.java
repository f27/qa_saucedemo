package uitest.swagshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.CartPage;
import uitest.swagshop.pages.ItemPage;
import uitest.swagshop.pages.LoginPage;
import uitest.swagshop.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

@DisplayName("Item page test")
public class ItemTests extends TestBase {
    ItemPage itemPage;
    ShopPage shopPage;
    CartPage cartPage;

    @BeforeAll
    static void login() {
        LoginPage loginpage = open("/", LoginPage.class);
        loginpage.login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToItemPage() {
        ShopPage shopPage = open(DEFAULT_INVENTORY_PAGE, ShopPage.class);
        itemPage = shopPage.goToItem(DEFAULT_ITEM);
    }

    @Test
    @DisplayName("Testing item's name")
    void itemNameTest() {
        itemPage.checkName(DEFAULT_ITEM);
    }

    @Test
    @DisplayName("Testing item's description")
    void itemDescTest() {
        itemPage.checkDesc(DEFAULT_ITEM_DESCRIPTION);
    }

    @Test
    @DisplayName("Testing item's price")
    void itemPriceTest() {
        itemPage.checkPrice(DEFAULT_ITEM_PRICE);
    }

    @Test
    @DisplayName("Testing item's picture")
    void itemImgSrcTest() {
        itemPage.checkImgSrc(DEFAULT_ITEM_PICTURE);
    }

    @Test
    @DisplayName("Testing item's picture's alt")
    void itemImgAltTest() {
        itemPage.checkImgAlt(DEFAULT_ITEM);
    }

    @Test
    @DisplayName("Testing Back button")
    void backButtonTest() {
        shopPage = itemPage.goBack();
        shopPage.checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    @DisplayName("Testing Header")
    void checkHeaderName() {
        itemPage.checkHeaderName(DEFAULT_HEADER);
    }

    @Test
    @DisplayName("Testing add and remove from cart")
    void addAndRemoveFromCartTest() {
        itemPage = new ItemPage();

        itemPage.addToCart();
        cartPage = itemPage.goToCart();

        cartPage.cartShouldHave(DEFAULT_ITEM);
        shopPage = cartPage.continueShopping();

        itemPage = shopPage.goToItem(DEFAULT_ITEM);

        itemPage.removeFromCart();
        cartPage = itemPage.goToCart();

        cartPage.cartShouldNotHave(DEFAULT_ITEM);
        cartPage.continueShopping();
    }
}
