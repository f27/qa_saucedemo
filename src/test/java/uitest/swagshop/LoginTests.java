package uitest.swagshop;

import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

public class LoginTests extends TestBase {
    LoginPage loginpage;

    @Test
    public void successLoginTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
        loginpage.assertLogin();
        loginpage.logout();
    }

    @Test
    public void lockedOutLoginTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login(LOCKED_OUT_LOGIN, DEFAULT_PASSWORD);
        loginpage.isLockedOut();
    }

    @Test
    public void wrongLoginTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login("wrongUser", DEFAULT_PASSWORD);
        loginpage.isWrongUsernameOrPassword();
    }

    @Test
    public void wrongPasswordTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login(DEFAULT_LOGIN, "wrongPass");
        loginpage.isWrongUsernameOrPassword();
    }
}
