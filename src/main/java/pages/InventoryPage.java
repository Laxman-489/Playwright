package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {


    private final String addToCartButtonSelector = "#shopping_cart_container";
    private final String slideBarMenu = "#react-burger-menu-btn";
    private final String logOutLinkSelector = "#logout_sidebar_link";
   // private final String selectItem="//*[@name='xxx']";

    public InventoryPage(Page page) {
        super(page);
    }
    public Locator cartItems() {
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
//    public void addItemToCart(String itemName) {
//
//        String itemToClick = selectItem.replace("xxx", itemName);
//        page.click(itemToClick);
//    }
    public void goToCart(){
        page.click(addToCartButtonSelector);
    }
    public void logOut(){
        page.click(slideBarMenu);
        page.click(logOutLinkSelector);
    }
}

