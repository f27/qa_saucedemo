package tests.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Login tests")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Successful login")
    public void successLoginTest() {
        open("/", LoginPage.class)
                .login(DEFAULT_LOGIN, DEFAULT_PASSWORD)
                .shouldHaveMenu()
                .menuClickByText("Logout");
    }

    @Test
    @DisplayName("Locked out")
    public void lockedOutLoginTest() {
        open("/", LoginPage.class)
                .unsuccessfulLogin(LOCKED_OUT_LOGIN, DEFAULT_PASSWORD)
                .shouldHaveError(ERROR_LOGIN_LOCKED_OUT);
    }

    @Test
    @DisplayName("Wrong login")
    public void wrongLoginTest() {
        open("/", LoginPage.class)
                .unsuccessfulLogin("wrongUser", DEFAULT_PASSWORD)
                .shouldHaveError(ERROR_LOGIN_WRONG_PASSWORD);
    }

    @Test
    @DisplayName("Wrong password")
    public void wrongPasswordTest() {
        open("/", LoginPage.class)
                .unsuccessfulLogin(DEFAULT_LOGIN, "wrongPass")
                .shouldHaveError(ERROR_LOGIN_WRONG_PASSWORD);
    }
}
