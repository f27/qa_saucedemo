package uitest.swagshop;

import org.junit.jupiter.api.Test;
import uitest.TestBase;

import static com.codeborne.selenide.Selenide.open;

public class LoginTests extends TestBase {
    LoginPage loginpage;

    @Test
    public void successLoginTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login("standard_user", "secret_sauce");
        loginpage.assertLogin();
        loginpage.logout();
    }

    @Test
    public void lockedOutLoginTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login("locked_out_user", "secret_sauce");
        loginpage.assertError("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void wrongLoginTest(){
        loginpage = open("/", LoginPage.class);

        loginpage.login("user", "secret_sauce");
        loginpage.assertError("Epic sadface: Username and password do not match any user in this service");
    }
}
