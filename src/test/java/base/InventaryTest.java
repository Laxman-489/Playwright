package base;


import org.testng.annotations.Test;

import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

public class InventaryTest extends BaseTest {

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login();

        InventoryPage inventoryPage = new InventoryPage(page);
        // Add items to the cart
        //inventoryPage.addItemToCart("add-to-cart-sauce-labs-backpack");
        List<String> itemsToAdd = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light"
        );
        inventoryPage.goToCart();
        inventoryPage.logOut();

    }
}
