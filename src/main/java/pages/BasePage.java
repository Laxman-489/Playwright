package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public Locator waitForElement(String selector, int timeout) {
        Locator locator = page.locator(selector);
        locator.waitFor(new Locator.WaitForOptions().setTimeout(timeout).setState(WaitForSelectorState.VISIBLE));
        return locator;
    }
    public Locator waitForElement(String selector) {
        return waitForElement(selector, 30000);
    }
}

