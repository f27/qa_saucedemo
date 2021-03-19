package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;
import uitest.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CartPage extends TestBase {

    final private SelenideElement cartList = $(".cart_list"),
            backButton = $(byText("Continue Shopping"));

    public void cartShouldHave(String item) {
        cartList.shouldHave(text(item));
    }

    public void cartShouldNotHave(String item) {
        cartList.shouldNotHave(text(item));
    }

    public void continueShopping() {
        backButton.click();
    }

}
