package uitest;

import com.codeborne.selenide.Configuration;

public class TestData {
    public static String
            DEFAULT_LOGIN = "standard_user", //Can be: standard_user, problem_user, performance_glitch_user
            LOCKED_OUT_LOGIN = "locked_out_user",
            DEFAULT_PASSWORD = "secret_sauce",
            DEFAULT_A_ITEM = "Sauce Labs Backpack",
            DEFAULT_Z_ITEM = "Test.allTheThings() T-Shirt (Red)",
            DEFAULT_ITEM = "Sauce Labs Backpack",
            DEFAULT_ITEM_PRICE = "29.99",
            DEFAULT_TAX = "2.40",
            DEFAULT_TOTAL = "32.39",
            DEFAULT_ITEM_PICTURE = Configuration.baseUrl + "/static/media/sauce-backpack-1200x1500.34e7aa42.jpg",
            DEFAULT_ITEM_DESCRIPTION = "carry.allTheThings() with the sleek, streamlined Sly" +
                    " Pack that melds uncompromising style with unequaled laptop and tablet protection.",
            DEFAULT_CHEAP_ITEM = "Sauce Labs Onesie",
            DEFAULT_EXPENSIVE_ITEM = "Sauce Labs Fleece Jacket",
            DEFAULT_INVENTORY_PAGE = "/inventory.html",
            DEFAULT_CART_PAGE = "/cart.html",
            DEFAULT_CHECKOUT_PAGE = "/checkout-step-one.html",
            DEFAULT_THANKS_PAGE = "/checkout-complete.html",
            DEFAULT_HEADER = "Swag Labs",
            DEFAULT_SHOP_LABEL = "Products",
            DEFAULT_CART_LABEL = "Your Cart",
            DEFAULT_CHECKOUT_LABEL = "Checkout: Your Information",
            DEFAULT_CHECKOUT_OVERVIEW_LABEL = "Checkout: Overview",
            DEFAULT_THANKS_LABEL = "Finish",
            DEFAULT_FIRSTNAME = "Alex",
            DEFAULT_LASTNAME = "Petrov",
            DEFAULT_ZIP = "12321";
}
