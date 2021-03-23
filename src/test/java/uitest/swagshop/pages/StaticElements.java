package uitest.swagshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public abstract class StaticElements {

    final SelenideElement menuButton = $("#react-burger-menu-btn"),
            twitterIcon = $(".social_twitter"),
            fbIcon = $(".social_facebook"),
            linkedIcon = $(".social_linkedin"),
            cartBadge = $(".shopping_cart_badge"),
            cartLink = $(".shopping_cart_link"),
            subHeader = $(".subheader");

    final ElementsCollection menuList = $$("a.menu-item");

    @Step("Header should have {label}")
    public void checkSubHeader(String label) {
        subHeader.shouldHave(text(label));
    }

    @Step("Page should have menu button")
    public void hasMenu() {
        menuButton.shouldBe(visible);
    }

    @Step("Click in menu on {text}")
    public void menuClickByText(String text) {
        menuButton.click();
        menuList.findBy(text(text)).click();
    }

    @Step("Going to shop")
    public ShopPage goToShop() {
        menuClickByText("All Items");

        return page(ShopPage.class);
    }

    @Step("Going to cart")
    public CartPage goToCart() {
        cartLink.click();
        return page(CartPage.class);
    }

    @Step("Verify social buttons")
    public void checkSocialButtons() {
        twitterIcon.shouldBe(visible);
        fbIcon.shouldBe(visible);
        linkedIcon.shouldBe(visible);
    }
}
