package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CheckOutPage extends StaticElements {
    final private SelenideElement firstNameEl = $("[data-test = firstName]"),
            lastNameEl = $("[data-test = lastName]"),
            zipCodeEl = $("[data-test = postalCode]"),
            errorMess = $("[data-test = error]"),
            cancelButton = $(".cart_cancel_link"),
            continueButton = $(".cart_button");

    public void fillForm(String firstName, String lastName, String zipCode){
        firstNameEl.setValue(firstName);
        lastNameEl.setValue(lastName);
        zipCodeEl.setValue(zipCode);
    }

    public void submitForm(){
        continueButton.click();
    }

    public void checkError(String errorMessage){
        errorMess.shouldHave(text(errorMessage));
    }

    public void cancelClick(){
        cancelButton.click();
    }
}
