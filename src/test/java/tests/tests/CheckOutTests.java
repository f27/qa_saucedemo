package tests.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.CheckOutPage;
import tests.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

@DisplayName("Checkout test")
public class CheckOutTests extends TestBase {
    CheckOutPage checkOutPage;

    @BeforeAll
    static void login() {
        open("/", LoginPage.class)
                .login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToCheckOut() {
        checkOutPage = open(DEFAULT_CHECKOUT_PAGE, CheckOutPage.class);
    }

    @Test
    @DisplayName("Checking page label")
    void checkSubHeaderTest() {
        checkOutPage.checkSubHeader(DEFAULT_CHECKOUT_LABEL);
    }

    @Test
    @DisplayName("Testing social buttons")
    void socialButtonsTest() {
        checkOutPage.checkSocialButtons();
    }

    @Test
    @DisplayName("Correct from submit")
    void successFillForm() {
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP)
                .submitForm()
                .checkSubHeader(DEFAULT_CHECKOUT_OVERVIEW_LABEL);
    }

    @Test
    @DisplayName("From submit without first name")
    void fillFormNoFirstName() {
        checkOutPage.fillForm("", DEFAULT_LASTNAME, DEFAULT_ZIP)
                .unsuccessfulSubmitForm()
                .shouldHaveError("Error: First Name is required");
    }

    @Test
    @DisplayName("From submit without last name")
    void fillFormNoLastName() {
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, "", DEFAULT_ZIP)
                .unsuccessfulSubmitForm()
                .shouldHaveError("Error: Last Name is required");
    }

    @Test
    @DisplayName("From submit without zip code")
    void fillFormNoZipCode() {
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, "")
                .unsuccessfulSubmitForm()
                .shouldHaveError("Error: Postal Code is required");
    }

    @Test
    @DisplayName("Testing cancel button")
    void checkCancelButton() {
        checkOutPage.cancelClick()
                .checkSubHeader(DEFAULT_CART_LABEL);
    }
}
