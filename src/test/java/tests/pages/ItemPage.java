package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ItemPage extends StaticElements {

    final static SelenideElement itemName = $(".inventory_details_name"),
            itemDesc = $(".inventory_details_desc"),
            itemPrice = $(".inventory_details_price"),
            itemPic = $(".inventory_details_img"),
            backButton = $(".inventory_details_back_button"),
            headerName = $(".header_label"),
            addAndRemoveButton = $(".btn_inventory");

    public void checkName(String name) {
        itemName.shouldHave(text(name));
    }

    public void checkDesc(String desc) {
        itemDesc.shouldHave(text(desc));
    }

    public void checkPrice(String price) {
        itemPrice.shouldHave(text(price));
    }

    public void checkImgSrc(String pic) {
        itemPic.shouldHave(attribute("src", pic));
    }

    public void checkImgAlt(String alt) {
        itemPic.shouldHave(attribute("alt", alt));
    }

    public ShopPage goBack() {
        backButton.click();

        return page(ShopPage.class);
    }

    @Step("Page's header should have {name}")
    public void checkHeaderName(String name) {
        headerName.shouldHave(text(name));
    }

    @Step("Click on Add to cart")
    public ItemPage addToCart() {
        addAndRemoveButton.shouldHave(text("ADD TO CART"));
        addAndRemoveButton.click();
        addAndRemoveButton.shouldHave(text("REMOVE"));
        cartBadge.shouldBe(visible);

        return this;
    }

    @Step("Click on Remove")
    public ItemPage removeFromCart() {
        addAndRemoveButton.shouldHave(text("REMOVE"));
        addAndRemoveButton.click();
        addAndRemoveButton.shouldHave(text("ADD TO CART"));
        cartBadge.shouldNotBe(visible);

        return this;
    }
}
