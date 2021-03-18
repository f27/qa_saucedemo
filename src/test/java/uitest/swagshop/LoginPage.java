package uitest.swagshop;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    final private SelenideElement usernameField = $("[data-test = username]"),
                            passwordField = $("[data-test = password]"),
                            error = $("[data-test = error]"),
                            menuButton = $("#react-burger-menu-btn");

    public void login(String username, String password){
        usernameField.setValue(username);
        passwordField.setValue(password).pressEnter();
    }

    public void assertError(String errorMessage){
        error.shouldHave(text(errorMessage));
    }

    public void assertLogin(){
        menuButton.shouldBe(visible);
    }

    public void logout(){
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
