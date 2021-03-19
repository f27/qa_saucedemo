package uitest.swagshop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import uitest.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShopPage extends TestBase {

    final private SelenideElement sorting = $(".product_sort_container"),
            twitterIcon = $(".social_twitter"),
            fbIcon = $(".social_facebook"),
            linkedIcon = $(".social_linkedin"),
            cartBadge = $(".shopping_cart_badge"),
            cartLink = $(".shopping_cart_link"),
            priceInInventory = $(".inventory_details_price");

    final private ElementsCollection itemNames = $$(".inventory_item_name");

    public void goToItem(String item) {
        itemNames.findBy(text(item)).click();
    }

    public void goToCart() {
        cartLink.click();
    }

    public void addToCartOnMainClick(String item) {
        itemNames.findBy(text(item)).parent().parent().parent().$(".btn_primary.btn_inventory").click();
    }

    public void removeFromCartOnMainClick(String item) {
        itemNames.findBy(text(item)).parent().parent().parent().$(".btn_secondary.btn_inventory").click();
    }

    public void assertAddToCartOnMain(String item) {
        itemNames.findBy(text(item)).parent().parent().parent().$(".btn_inventory").shouldHave(text("REMOVE"));
        cartBadge.shouldBe(visible);
    }

    public void assertRemoveFromCartOnMain(String item) {
        itemNames.findBy(text(item)).parent().parent().parent().$(".btn_inventory").shouldHave(text("ADD TO CART"));
        cartBadge.shouldNotBe(visible);
    }

    public void checkPicture(String item, String picUrl) {
        itemNames.findBy(text(item)).parent().parent().parent().find("img").shouldHave(attribute("src", picUrl));
    }

    public void checkAltForPicture(String item) {
        itemNames.findBy(text(item)).parent().parent().parent().find("img").shouldHave(attribute("alt", item));
    }

    public void checkPrice(String item, String price) {
        itemNames.findBy(text(item)).parent().parent().parent().find(".inventory_item_price").shouldHave(text(price));
        itemNames.findBy(text(item)).click();
        priceInInventory.shouldHave(text(price));
    }

    public void sortAtoZ() {
        sorting.selectOption("Name (A to Z)");
    }

    public void assertSortAtoZ(String aItem, String zItem) {
        sorting.getSelectedOption().shouldHave(text("Name (A to Z)"));
        itemNames.first().shouldHave(text(aItem));
        itemNames.last().shouldHave(text(zItem));
    }

    public void sortZtoA() {
        sorting.selectOption("Name (Z to A)");
    }

    public void assertSortZtoA(String aItem, String zItem) {
        sorting.getSelectedOption().shouldHave(text("Name (Z to A)"));
        itemNames.last().shouldHave(text(aItem));
        itemNames.first().shouldHave(text(zItem));
    }

    public void sortLowToHigh() {
        sorting.selectOption("Price (low to high)");
    }

    public void assertSortLowToHigh(String cheapItem, String expensiveItem) {
        sorting.getSelectedOption().shouldHave(text("Price (low to high)"));
        itemNames.first().shouldHave(text(cheapItem));
        itemNames.last().shouldHave(text(expensiveItem));
    }

    public void sortHighToLow() {
        sorting.selectOption("Price (high to low)");
    }

    public void assertSortHighToLow(String cheapItem, String expensiveItem) {
        sorting.getSelectedOption().shouldHave(text("Price (high to low)"));
        itemNames.last().shouldHave(text(cheapItem));
        itemNames.first().shouldHave(text(expensiveItem));
    }

    public void checkSocialButtons() {
        twitterIcon.shouldBe(visible);
        fbIcon.shouldBe(visible);
        linkedIcon.shouldBe(visible);
    }
}
