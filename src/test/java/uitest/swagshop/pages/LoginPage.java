package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    final private SelenideElement usernameField = $("[data-test = username]"),
            passwordField = $("[data-test = password]"),
            error = $("[data-test = error]");

    @Step("Login: {username}, Password:{password}")
    public ShopPage login(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password).pressEnter();

        return page(ShopPage.class);
    }

    @Step("Login is successful")
    public void assertLogin() {
        new ShopPage().hasMenu();
    }

    @Step("Error message should be: {errorMessage}")
    public void assertError(String errorMessage) {
        error.shouldHave(text(errorMessage));
    }
}
