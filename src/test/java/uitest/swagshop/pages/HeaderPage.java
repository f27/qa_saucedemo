package uitest.swagshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HeaderPage {

    final SelenideElement menuButton = $("#react-burger-menu-btn"),
            pageLabel = $(".product_label"),
            twitterIcon = $(".social_twitter"),
            fbIcon = $(".social_facebook"),
            linkedIcon = $(".social_linkedin"),
            cartBadge = $(".shopping_cart_badge"),
            cartLink = $(".shopping_cart_link");

    final ElementsCollection menuList = $$("a.menu-item");

    public void checkPageLabel(String label) {
        pageLabel.shouldHave(text(label));
    }

    public void hasMenu() {
        menuButton.shouldBe(visible);
    }

    public void menuClickByText(String text) {
        menuButton.click();
        menuList.findBy(text(text)).click();
        menuButton.shouldNotBe(visible);
    }

    public void goToCart() {
        cartLink.click();
    }
}
