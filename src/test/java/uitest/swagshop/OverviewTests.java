package uitest.swagshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static uitest.TestData.*;

public class OverviewTests extends TestBase {
    OverviewPage overviewPage;

    @BeforeAll
    static void login(){
        open("/");

        new LoginPage().login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @BeforeEach
    void goToOverview(){
        open(DEFAULT_CHECKOUT_PAGE);
        new CheckOutPage().fillForm(DEFAULT_FIRSTNAME,DEFAULT_LASTNAME,DEFAULT_ZIP);
        new CheckOutPage().submitForm();
    }
    @AfterEach
    void clearCart(){
        new CartPage().clear();
    }

    @Test
    void checkSubHeaderTest(){
        overviewPage = new OverviewPage();

        overviewPage.checkSubHeader(DEFAULT_CHECKOUT_OVERVIEW_LABEL);
    }

    @Test
    void socialButtonsTest(){
        overviewPage = new OverviewPage();

        overviewPage.checkSocialButtons();
    }

    @Test
    void emptyCheckOut(){
        overviewPage = new OverviewPage();

        overviewPage.checkEmpty();
        new FinishPage().checkSubHeader(DEFAULT_THANKS_LABEL);
    }

    @Test
    void checkWithItem(){
        overviewPage = new OverviewPage();

        overviewPage.menuClickByText("All Items");
        new ShopPage().addToCart(DEFAULT_ITEM);
        new ShopPage().goToCart();
        new CartPage().checkOut();
        new CheckOutPage().fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP);
        new CheckOutPage().submitForm();

        overviewPage.checkItemName(DEFAULT_ITEM);
        overviewPage.checkItemDesc(DEFAULT_ITEM_DESCRIPTION);
        overviewPage.checkItemPrice(DEFAULT_ITEM_PRICE);
        overviewPage.checkItemQuantity("1");
        overviewPage.checkTax(DEFAULT_TAX);
        overviewPage.checkItemsTotalPrice(DEFAULT_ITEM_PRICE);
        overviewPage.checkPriceWithTaxes(DEFAULT_TOTAL);
        overviewPage.finishClick();

        new FinishPage().checkSubHeader(DEFAULT_THANKS_LABEL);
    }
}
