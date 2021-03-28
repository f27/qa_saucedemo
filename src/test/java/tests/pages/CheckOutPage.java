package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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

    @Step("Fill form")
    public CheckOutPage fillForm(String firstName, String lastName, String zipCode) {
        firstNameEl.setValue(firstName);
        lastNameEl.setValue(lastName);
        zipCodeEl.setValue(zipCode);

        return this;
    }

    @Step("Submit form")
    public OverviewPage submitForm() {
        continueButton.click();

        return page(OverviewPage.class);
    }

    @Step("Submit form")
    public CheckOutPage unsuccessfulSubmitForm() {
        continueButton.click();

        return this;
    }

    @Step("Page has error {errorMessage}")
    public void checkError(String errorMessage) {
        errorMess.shouldHave(text(errorMessage));
    }

    @Step("Click cancel")
    public CartPage cancelClick() {
        cancelButton.click();

        return page(CartPage.class);
    }
}
