package tests.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.LoginPage;
import tests.pages.ShopPage;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Login tests")
public class LoginTests extends TestBase {
    LoginPage loginpage;

    @Test
    @DisplayName("Successful login")
    public void successLoginTest(){
        loginpage = open("/", LoginPage.class);

        ShopPage shopPage = loginpage.login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
        loginpage.assertLogin();
        shopPage.menuClickByText("Logout");
    }

    @Test
    @DisplayName("Locked out")
    public void lockedOutLoginTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login(LOCKED_OUT_LOGIN, DEFAULT_PASSWORD);
        loginpage.assertError(ERROR_LOGIN_LOCKED_OUT);
    }

    @Test
    @DisplayName("Wrong login")
    public void wrongLoginTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login("wrongUser", DEFAULT_PASSWORD);
        loginpage.assertError(ERROR_LOGIN_WRONG_PASSWORD);
    }

    @Test
    @DisplayName("Wrong password")
    public void wrongPasswordTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login(DEFAULT_LOGIN, "wrongPass");
        loginpage.assertError(ERROR_LOGIN_WRONG_PASSWORD);
    }
}
