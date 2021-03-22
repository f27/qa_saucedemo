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
    OverviewPage overviewPage;
    CartPage cartPage;

    @BeforeAll
    static void login() {
        LoginPage loginpage = open("/", LoginPage.class);
        loginpage.login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToCheckOut() {
        checkOutPage = open(DEFAULT_CHECKOUT_PAGE, CheckOutPage.class);
    }

    @Test
    void checkSubHeaderTest() {
        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }

    @Test
    void socialButtonsTest() {
        checkOutPage.checkSocialButtons();
    }

    @Test
    void successFillForm() {
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP);
        overviewPage = checkOutPage.submitForm();
        overviewPage.checkSubHeader(DEFAULT_CHECKOUT_OVERVIEW_LABEL);
    }

    @Test
    void fillFormNoFirstName() {
        checkOutPage.fillForm("", DEFAULT_LASTNAME, DEFAULT_ZIP);
        checkOutPage.submitForm();
        checkOutPage.checkError("Error: First Name is required");
    }

    @Test
    void fillFormNoLastName() {
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, "", DEFAULT_ZIP);
        checkOutPage.submitForm();
        checkOutPage.checkError("Error: Last Name is required");
    }

    @Test
    void fillFormNoZipCode() {
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, "");
        checkOutPage.submitForm();
        checkOutPage.checkError("Error: Postal Code is required");
    }

    @Test
    void checkCancelButton() {
        cartPage = checkOutPage.cancelClick();
        cartPage.checkSubHeader(DEFAULT_CART_LABEL);
    }
}
