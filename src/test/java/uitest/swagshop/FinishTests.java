package uitest.swagshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.*;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

public class FinishTests extends TestBase {
    FinishPage finishPage;
    CheckOutPage checkOutPage;
    OverviewPage overviewPage;
    ShopPage shopPage;
    CartPage cartPage;

    @BeforeAll
    static void login() {
        LoginPage loginpage = open("/", LoginPage.class);
        loginpage.login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @Test
    void hasThanksTest() {
        finishPage = open(DEFAULT_THANKS_PAGE, FinishPage.class);
        finishPage.hasThanks();
    }

    @Test
    void finishResetCartTest() {
        shopPage = open(DEFAULT_INVENTORY_PAGE, ShopPage.class);

        shopPage.addToCart(DEFAULT_ITEM);
        cartPage = shopPage.goToCart();
        checkOutPage = cartPage.checkOut();
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP);
        overviewPage = checkOutPage.submitForm();
        finishPage = overviewPage.finishClick();
        finishPage.checkSubHeader(DEFAULT_THANKS_LABEL);
        shopPage = finishPage.goToShop();
        cartPage = shopPage.goToCart();
        cartPage.isEmpty();
    }

    @Test
    void visitFinishNotResetCartTest() {
        shopPage = open(DEFAULT_INVENTORY_PAGE, ShopPage.class);

        shopPage.addToCart(DEFAULT_ITEM);

        finishPage = open(DEFAULT_THANKS_PAGE, FinishPage.class);

        finishPage.checkSubHeader(DEFAULT_THANKS_LABEL);

        cartPage = open(DEFAULT_CART_PAGE, CartPage.class);

        cartPage.itemInCart(DEFAULT_ITEM);
        cartPage.clear();
    }

}
