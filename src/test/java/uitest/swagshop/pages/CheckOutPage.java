package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CheckOutPage extends StaticElements {
    final private SelenideElement firstNameEl = $("[data-test = firstName]"),
            lastNameEl = $("[data-test = lastName]"),
            zipCodeEl = $("[data-test = postalCode]"),
            errorMess = $("[data-test = error]"),
            cancelButton = $(".cart_cancel_link"),
            continueButton = $(".cart_button");

    public void fillForm(String firstName, String lastName, String zipCode) {
        firstNameEl.setValue(firstName);
        lastNameEl.setValue(lastName);
        zipCodeEl.setValue(zipCode);
    }

    public OverviewPage submitForm() {
        continueButton.click();

        return page(OverviewPage.class);
    }

    public void checkError(String errorMessage) {
        errorMess.shouldHave(text(errorMessage));
    }

    public CartPage cancelClick() {
        cancelButton.click();

        return page(CartPage.class);
    }
}
