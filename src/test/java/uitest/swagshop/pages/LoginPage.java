package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    final private SelenideElement usernameField = $("[data-test = username]"),
                            passwordField = $("[data-test = password]"),
                            error = $("[data-test = error]");

    final String wrongUsernameOrPasswordMess = "Epic sadface: Username and password do not match any user in this service",
                lockedOutMess = "Epic sadface: Sorry, this user has been locked out.";

    public void login(String username, String password){
        usernameField.setValue(username);
        passwordField.setValue(password).pressEnter();
    }

    public void isLockedOut(){
        assertError(lockedOutMess);
    }

    public void isWrongUsernameOrPassword(){
        assertError(wrongUsernameOrPasswordMess);
    }

    public void assertLogin(){
        new ShopPage().hasMenu();
    }

    public void logout(){
        new ShopPage().menuClickByText("Logout");
    }

    private void assertError(String errorMessage){
        error.shouldHave(text(errorMessage));
    }
}
