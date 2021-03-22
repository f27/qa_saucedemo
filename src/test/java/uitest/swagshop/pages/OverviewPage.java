package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;

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

    public void cancelClick() {
        cancelButton.click();
    }

    public FinishPage finishClick() {
        finishButton.click();

        return page(FinishPage.class);
    }

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

    public void checkItemName(String name) {
        itemName.shouldHave(text(name));
    }

    public void checkItemDesc(String desc) {
        itemDesc.shouldHave(text(desc));
    }

    public void checkItemPrice(String price) {
        itemPrice.shouldHave(text(price));
    }

    public void checkItemQuantity(String quantity) {
        itemQuantity.shouldHave(text(quantity));
    }

    public void checkItemsTotalPrice(String totalPrice) {
        itemsTotalPrice.shouldHave(text(totalPrice));
    }

    public void checkTax(String tax) {
        taxEl.shouldHave(text(tax));
    }

    public void checkPriceWithTaxes(String totalWithTax) {
        priceWithTaxes.shouldHave(text(totalWithTax));
    }
}
