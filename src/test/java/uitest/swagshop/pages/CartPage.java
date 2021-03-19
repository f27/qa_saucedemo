package uitest.swagshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends StaticElements {

    final private SelenideElement cartList = $(".cart_list"),
            backButton = $(byText("Continue Shopping")),
            checkoutButton = $(".checkout_button"),
            itemName = $(".inventory_item_name"),
            itemDesc = $(".inventory_item_desc"),
            itemPrice = $(".inventory_item_price"),
            itemQuantity = $(".cart_quantity");

    final private ElementsCollection cartItems = $$(".cart_item");

    public void clear() {
        cartLink.click();
        cartItems.forEach(item -> item.$(".btn_secondary").click());

        cartItems.first().shouldNotBe(visible);
        cartBadge.shouldNotBe(visible);
    }

    public void cartShouldHave(String item) {
        cartList.shouldHave(text(item));
    }

    public void cartShouldNotHave(String item) {
        cartList.shouldNotHave(text(item));
    }

    public void continueShopping() {
        backButton.click();
    }

    public void isEmpty() {
        cartItems.first().shouldBe(hidden);
    }

    public void checkOut() {
        checkoutButton.click();
    }

    public void itemInCart(String name) {
        cartItems.findBy(text(name)).shouldBe(visible);
    }


    public void checkName(String name) {
        itemName.shouldHave(text(name));
    }

    public void checkDesc(String desc) {
        itemDesc.shouldHave(text(desc));
    }

    public void checkPrice(String price) {
        itemPrice.shouldHave(text(price));
    }

    public void checkQuantity(String quantity) {
        itemQuantity.shouldHave(text(quantity));

    }
}
