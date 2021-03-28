package tests.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.ItemPage;
import tests.pages.LoginPage;
import tests.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Item page test")
public class ItemTests extends TestBase {
    ItemPage itemPage;

    @BeforeAll
    static void login() {
        open("/", LoginPage.class)
                .login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToItemPage() {
        itemPage = open(DEFAULT_INVENTORY_PAGE, ShopPage.class)
                .goToItem(DEFAULT_ITEM);
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
        itemPage.goBack()
                .checkPageLabel(DEFAULT_SHOP_LABEL);
    }

    @Test
    @DisplayName("Testing Header")
    void checkHeaderName() {
        itemPage.checkHeaderName(DEFAULT_HEADER);
    }

    @Test
    @DisplayName("Testing add and remove from cart")
    void addAndRemoveFromCartTest() {
        itemPage.addToCart()
                .goToCart()
                .cartShouldHave(DEFAULT_ITEM)
                .continueShopping()
                .goToItem(DEFAULT_ITEM)
                .removeFromCart()
                .goToCart()
                .cartShouldNotHave(DEFAULT_ITEM)
                .continueShopping();
    }
}
