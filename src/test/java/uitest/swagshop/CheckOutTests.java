package uitest.swagshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.CartPage;
import uitest.swagshop.pages.CheckOutPage;
import uitest.swagshop.pages.LoginPage;
import uitest.swagshop.pages.OverviewPage;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

public class CheckOutTests extends TestBase {
    CheckOutPage checkOutPage;

    @BeforeAll
    static void login(){
        open("/");

        new LoginPage().login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToCheckOut(){
        open(DEFAULT_CHECKOUT_PAGE);
    }

    @Test
    void checkSubHeaderTest(){
        checkOutPage = new CheckOutPage();

        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }

    @Test
    void socialButtonsTest(){
        checkOutPage = new CheckOutPage();

        checkOutPage.checkSocialButtons();
    }

    @Test
    void successFillForm(){
        checkOutPage = new CheckOutPage();

        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP);
        checkOutPage.submitForm();
        new OverviewPage().checkSubHeader(DEFAULT_CHECKOUT_OVERVIEW_LABEL);
    }

    @Test
    void fillFormNoFirstName(){
        checkOutPage = new CheckOutPage();

        checkOutPage.fillForm("", DEFAULT_LASTNAME, DEFAULT_ZIP);
        checkOutPage.submitForm();
        checkOutPage.checkError("Error: First Name is required");
    }

    @Test
    void fillFormNoLastName(){
        checkOutPage = new CheckOutPage();

        checkOutPage.fillForm(DEFAULT_FIRSTNAME, "", DEFAULT_ZIP);
        checkOutPage.submitForm();
        checkOutPage.checkError("Error: Last Name is required");
    }

    @Test
    void fillFormNoZipCode(){
        checkOutPage = new CheckOutPage();

        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, "");
        checkOutPage.submitForm();
        checkOutPage.checkError("Error: Postal Code is required");
    }

    @Test
    void checkCancelButton(){
        checkOutPage = new CheckOutPage();

        checkOutPage.cancelClick();
        new CartPage().checkSubHeader(DEFAULT_CART_LABEL);
    }
}
