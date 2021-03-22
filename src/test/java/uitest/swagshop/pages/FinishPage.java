package uitest.swagshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class FinishPage extends StaticElements {
    final private SelenideElement thanksMess = $(".complete-header");

    public void hasThanks() {
        thanksMess.shouldHave(text("Thank"));
    }
}
