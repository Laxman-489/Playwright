package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.*;

public class TableTest {
    private static Playwright playwright;
    private static Browser browser;
    private Page page;

    @BeforeClass
    public void Setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeMethod
    public void setupPage() {
        page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void testJsPromptAcceptWithInput() {
        page.onDialog(dialog -> {
            System.out.println("JS Prompt Text: " + dialog.message());
            dialog.accept("Playwright Test");
        });

        page.locator("text=Click for JS Prompt").click();
        String result = page.locator("#result").textContent();

    }

    @AfterMethod
    public void tearDownPage() {
        if (page != null) {
            page.close();
        }
    }

    @AfterClass
    public void tearDownBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}

