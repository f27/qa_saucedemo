package uitest.swagshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uitest.TestBase;
import uitest.swagshop.pages.*;

import static com.codeborne.selenide.Selenide.open;
import static uitest.TestData.*;

public class FinishTests extends TestBase {
    FinishPage finishPage;

    @BeforeAll
    static void login(){
        open("/");

        new LoginPage().login(DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    @Test
    void hasThanksTest(){
        finishPage = new FinishPage();
        open(DEFAULT_THANKS_PAGE);
        finishPage.hasThanks();
    }

    @Test
    void finishResetCartTest(){
        ShopPage shopPage;
        CartPage cartPage;
        CheckOutPage checkOutPage;
        OverviewPage overviewPage;

        shopPage = new ShopPage();
        cartPage = new CartPage();
        checkOutPage = new CheckOutPage();
        overviewPage = new OverviewPage();
        finishPage = new FinishPage();

        open(DEFAULT_INVENTORY_PAGE);

        shopPage.addToCart(DEFAULT_ITEM);
        shopPage.goToCart();
        cartPage.checkOut();
        checkOutPage.fillForm(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_ZIP);
        checkOutPage.submitForm();
        overviewPage.finishClick();
        finishPage.checkSubHeader(DEFAULT_THANKS_LABEL);
        finishPage.menuClickByText("All Items");
        shopPage.goToCart();
        cartPage.isEmpty();
    }

    @Test
    void visitFinishNotResetCartTest(){
        ShopPage shopPage;
        CartPage cartPage;

        shopPage = new ShopPage();
        cartPage = new CartPage();
        finishPage = new FinishPage();

        open(DEFAULT_INVENTORY_PAGE);

        shopPage.addToCart(DEFAULT_ITEM);

        open(DEFAULT_THANKS_PAGE);

        finishPage.checkSubHeader(DEFAULT_THANKS_LABEL);

        open(DEFAULT_CART_PAGE);

        cartPage.itemInCart(DEFAULT_ITEM);
        cartPage.clear();
    }

}
