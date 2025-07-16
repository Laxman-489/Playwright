package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private final Page page;


    public CartPage(Page page) {
        this.page = page;
    }
    private Locator cartItems() {
        return page.locator(".cart_item");
    }
    public List<String> getCartItemNames() {
        List<String> itemNames = new ArrayList<>();
        int count = cartItems().count();

        for (int i = 0; i < count; i++) {
            String name = cartItems().nth(i).locator(".inventory_item_name").innerText().trim();
            itemNames.add(name);
        }

        return itemNames;
    }
    public boolean verifyCartItems(List<String> expectedItems) {
        List<String> actualItems = getCartItemNames();
        return actualItems.containsAll(expectedItems) && expectedItems.containsAll(actualItems);
    }


}



