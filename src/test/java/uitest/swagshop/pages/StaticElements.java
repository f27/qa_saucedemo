package uitest.swagshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StaticElements {

    final SelenideElement menuButton = $("#react-burger-menu-btn"),
            twitterIcon = $(".social_twitter"),
            fbIcon = $(".social_facebook"),
            linkedIcon = $(".social_linkedin"),
            cartBadge = $(".shopping_cart_badge"),
            cartLink = $(".shopping_cart_link"),
            subHeader = $(".subheader");

    final ElementsCollection menuList = $$("a.menu-item");

    public void checkSubHeader(String label){
        subHeader.shouldHave(text(label));
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

    public void checkSocialButtons() {
        twitterIcon.shouldBe(visible);
        fbIcon.shouldBe(visible);
        linkedIcon.shouldBe(visible);
    }
}
