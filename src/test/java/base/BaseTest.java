package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        try {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            browserContext = browser.newContext();
            page = browserContext.newPage();
        } catch (Exception e) {
            System.err.println("Error setting up Playwright: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (page != null) {
                page.close();
            }
            if (browserContext != null) {
                browserContext.close();
            }
            if (browser != null) {
                browser.close();
            }
            if (playwright != null) {
                playwright.close();
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        }
    }
}