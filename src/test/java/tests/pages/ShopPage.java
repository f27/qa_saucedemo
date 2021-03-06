package tests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ShopPage extends StaticElements {

    final private SelenideElement sorting = $(".product_sort_container"),
            pageLabel = $(".product_label"),
            priceInInventory = $(".inventory_details_price");

    final private ElementsCollection itemNames = $$(".inventory_item_name");

    @Step("Header should have {label}")
    public ShopPage checkPageLabel(String label) {
        pageLabel.shouldHave(text(label));

        return this;
    }

    @Step("Going to {item}'s page")
    public ItemPage goToItem(String item) {
        itemNames.findBy(text(item)).click();

        return page(ItemPage.class);
    }

    @Step("Add {item} to cart")
    public ShopPage addToCart(String item) {
        itemNames.findBy(text(item)).parent().parent().parent().$(".btn_primary.btn_inventory").click();
        itemNames.findBy(text(item)).parent().parent().parent().$(".btn_inventory").shouldHave(text("REMOVE"));
        cartBadge.shouldBe(visible);

        return this;
    }

    @Step("Remove {item} from cart")
    public ShopPage removeFromCart(String item) {
        itemNames.findBy(text(item)).parent().parent().parent().$(".btn_secondary.btn_inventory").click();
        itemNames.findBy(text(item)).parent().parent().parent().$(".btn_inventory").shouldHave(text("ADD TO CART"));
        cartBadge.shouldNotBe(visible);

        return this;
    }

    @Step("{item}'s picture should be {picUrl}")
    public ShopPage checkPicture(String item, String picUrl) {
        itemNames.findBy(text(item)).parent().parent().parent().find("img").shouldHave(attribute("src", picUrl));

        return this;
    }

    @Step("Picture should have alt='{item}'")
    public ShopPage checkAltForPicture(String item) {
        itemNames.findBy(text(item)).parent().parent().parent().find("img").shouldHave(attribute("alt", item));

        return this;
    }

    @Step("{item}'s price should be {price}")
    public ShopPage checkPrice(String item, String price) {
        itemNames.findBy(text(item)).parent().parent().parent().find(".inventory_item_price").shouldHave(text(price));
        itemNames.findBy(text(item)).click();
        priceInInventory.shouldHave(text(price));

        return this;
    }

    @Step("Sort A to Z")
    public ShopPage sortAtoZ() {
        sorting.selectOption("Name (A to Z)");

        return this;
    }

    @Step("First item should be: {aItem}, last: {zItem}")
    public ShopPage assertSortAtoZ(String aItem, String zItem) {
        sorting.getSelectedOption().shouldHave(text("Name (A to Z)"));
        itemNames.first().shouldHave(text(aItem));
        itemNames.last().shouldHave(text(zItem));

        return this;
    }

    @Step("Sort Z to A")
    public ShopPage sortZtoA() {
        sorting.selectOption("Name (Z to A)");

        return this;
    }

    @Step("First item should be: {zItem}, last: {aItem}")
    public void assertSortZtoA(String aItem, String zItem) {
        sorting.getSelectedOption().shouldHave(text("Name (Z to A)"));
        itemNames.last().shouldHave(text(aItem));
        itemNames.first().shouldHave(text(zItem));
    }

    @Step("Sort low to high")
    public ShopPage sortLowToHigh() {
        sorting.selectOption("Price (low to high)");

        return this;
    }

    @Step("First item should be: {cheapItem}, last: {expensiveItem}")
    public void assertSortLowToHigh(String cheapItem, String expensiveItem) {
        sorting.getSelectedOption().shouldHave(text("Price (low to high)"));
        itemNames.first().shouldHave(text(cheapItem));
        itemNames.last().shouldHave(text(expensiveItem));
    }

    @Step("Sort high to low")
    public ShopPage sortHighToLow() {
        sorting.selectOption("Price (high to low)");

        return this;
    }

    @Step("First item should be: {expensiveItem}, last: {cheapItem}")
    public void assertSortHighToLow(String cheapItem, String expensiveItem) {
        sorting.getSelectedOption().shouldHave(text("Price (high to low)"));
        itemNames.last().shouldHave(text(cheapItem));
        itemNames.first().shouldHave(text(expensiveItem));
    }

    @Step("Page should have menu button")
    public ShopPage shouldHaveMenu() {
        hasMenu();

        return this;
    }
}
