package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ItemPage extends StaticElements {

    final static SelenideElement itemName = $(".inventory_details_name"),
            itemDesc = $(".inventory_details_desc"),
            itemPrice = $(".inventory_details_price"),
            itemPic = $(".inventory_details_img"),
            backButton = $(".inventory_details_back_button"),
            headerName = $(".header_label"),
            addAndRemoveButton = $(".btn_inventory");

    public void checkName(String name){
        itemName.shouldHave(text(name));
    }

    public void checkDesc(String desc){
        itemDesc.shouldHave(text(desc));
    }

    public void checkPrice(String price){
        itemPrice.shouldHave(text(price));
    }

    public void checkImgSrc(String pic){
        itemPic.shouldHave(attribute("src", pic));
    }

    public void checkImgAlt(String alt){
        itemPic.shouldHave(attribute("alt", alt));
    }

    public void goBack(){
        backButton.click();
    }

    public void checkHeaderName(String name){
        headerName.shouldHave(text(name));
    }

    public void addToCart(){
        addAndRemoveButton.click();
    }

    public void removeFromCart(){
        addAndRemoveButton.click();
    }

    public void assertAddToCart(String item) {
        addAndRemoveButton.shouldHave(text("REMOVE"));
        cartBadge.shouldBe(visible);
    }

    public void assertRemoveFromCart(String item) {
        addAndRemoveButton.shouldHave(text("ADD TO CART"));
        cartBadge.shouldNotBe(visible);
    }
}
