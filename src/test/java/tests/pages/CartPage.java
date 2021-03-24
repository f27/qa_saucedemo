package tests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
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

    @Step("Clear cart")
    public void clear() {
        cartLink.click();
        cartItems.forEach(item -> item.$(".btn_secondary").click());

        cartItems.shouldHave(sizeLessThan(1));
        cartBadge.shouldNotBe(visible);
    }

    @Step("Cart should have {item}")
    public void cartShouldHave(String item) {
        cartList.shouldHave(text(item));
    }

    @Step("Cart should not have {item}")
    public void cartShouldNotHave(String item) {
        cartList.shouldNotHave(text(item));
    }

    @Step("Click continue shopping")
    public ShopPage continueShopping() {
        backButton.click();

        return page(ShopPage.class);
    }

    @Step("Verify cart is empty")
    public void isEmpty() {
        cartItems.first().shouldBe(hidden);
    }

    @Step("Click Checkout")
    public CheckOutPage checkOut() {
        checkoutButton.click();

        return page(CheckOutPage.class);
    }

    @Step("Verify {name} is in cart")
    public void itemInCart(String name) {
        cartItems.findBy(text(name)).shouldBe(visible);
    }

    @Step("Item should be {name}")
    public void checkName(String name) {
        itemName.shouldHave(text(name));
    }

    @Step("Description should be {desc}")
    public void checkDesc(String desc) {
        itemDesc.shouldHave(text(desc));
    }

    @Step("Price should be {price}")
    public void checkPrice(String price) {
        itemPrice.shouldHave(text(price));
    }

    @Step("Quantity should be {quantity}")
    public void checkQuantity(String quantity) {
        itemQuantity.shouldHave(text(quantity));

    }
}
