package tests.pages;

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
    public OverviewPage checkItemsNameInOverview(String name) {
        itemName.shouldHave(text(name));

        return this;
    }

    @Step("Item's description is {desc}")
    public OverviewPage checkItemDesc(String desc) {
        itemDesc.shouldHave(text(desc));

        return this;
    }

    @Step("Item's price is {price}")
    public OverviewPage checkItemPrice(String price) {
        itemPrice.shouldHave(text(price));

        return this;
    }

    @Step("Item's quantity is {quantity}")
    public OverviewPage checkItemQuantity(String quantity) {
        itemQuantity.shouldHave(text(quantity));

        return this;
    }

    @Step("Total price is {totalPrice}")
    public OverviewPage checkItemsTotalPrice(String totalPrice) {
        itemsTotalPrice.shouldHave(text(totalPrice));

        return this;
    }

    @Step("Tax is {tax}")
    public OverviewPage checkTax(String tax) {
        taxEl.shouldHave(text(tax));

        return this;
    }

    @Step("Total price with tax is {totalWithTax}")
    public OverviewPage checkPriceWithTaxes(String totalWithTax) {
        priceWithTaxes.shouldHave(text(totalWithTax));

        return this;
    }
}
