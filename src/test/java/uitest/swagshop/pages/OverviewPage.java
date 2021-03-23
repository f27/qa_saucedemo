package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class OverviewPage extends StaticElements {
    final private SelenideElement itemName = $(".inventory_item_name"),
            itemDesc = $(".inventory_item_desc"),
            itemPrice = $(".inventory_item_price"),
            itemQuantity = $(".summary_quantity"),
            itemsTotalPrice = $(".summary_subtotal_label"),
            cancelButton = $(".cart_cancel_link"),
            taxEl = $(".summary_tax_label"),
            priceWithTaxes = $(".summary_total_label"),
            finishButton = $(".cart_button");

    public FinishPage finishClick() {
        finishButton.click();

        return page(FinishPage.class);
    }

    @Step("Empty checkout")
    public FinishPage checkEmpty() {
        itemName.shouldNotBe(visible);
        itemDesc.shouldNotBe(visible);
        itemPrice.shouldNotBe(visible);
        itemQuantity.shouldNotBe(visible);
        cancelButton.shouldBe(visible);
        taxEl.shouldHave(text("$0.00"));
        priceWithTaxes.shouldHave(text("$0.00"));
        finishButton.click();

        return page(FinishPage.class);
    }

    @Step("Item is {name}")
    public void checkItemName(String name) {
        itemName.shouldHave(text(name));
    }

    @Step("Item's description is {desc}")
    public void checkItemDesc(String desc) {
        itemDesc.shouldHave(text(desc));
    }

    @Step("Item's price is {price}")
    public void checkItemPrice(String price) {
        itemPrice.shouldHave(text(price));
    }

    @Step("Item's quantity is {quantity}")
    public void checkItemQuantity(String quantity) {
        itemQuantity.shouldHave(text(quantity));
    }

    @Step("Total price is {totalPrice}")
    public void checkItemsTotalPrice(String totalPrice) {
        itemsTotalPrice.shouldHave(text(totalPrice));
    }

    @Step("Tax is {tax}")
    public void checkTax(String tax) {
        taxEl.shouldHave(text(tax));
    }

    @Step("Total price with tax is {totalWithTax}")
    public void checkPriceWithTaxes(String totalWithTax) {
        priceWithTaxes.shouldHave(text(totalWithTax));
    }
}
