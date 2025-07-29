package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.microsoft.playwright .*;
import org.testng.annotations .*;

public class AlertsAndPopUps {

    public static class JavaScriptAlertsTest {

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
        public void testJsAlert() {
            page.onDialog(dialog -> {
                System.out.println("JS Alert Text: " + dialog.message());
                dialog.accept();
            });

            page.locator("text=Click for JS Alert").click();

            String result = page.locator("#result").textContent();
            System.out.println("Result after JS Alert: " + result);
            assert result.contains("You successfully clicked an alert");
        }

        @Test
        public void testJsConfirmAccept() {
            page.onDialog(dialog -> {
                System.out.println("JS Confirm Text: " + dialog.message());
                dialog.accept();
            });

            page.locator("text=Click for JS Confirm").click();

            String result = page.locator("#result").textContent();
            System.out.println("Result after Confirm Accept: " + result);
            assert result.contains("You clicked: Ok");
        }

        @Test
        public void testJsConfirmDismiss() {
            page.onceDialog(dialog -> {
                System.out.println("JS Confirm Text: " + dialog.message());
                dialog.dismiss();
            });

            page.locator("text=Click for JS Confirm").click();

            String result = page.locator("#result").textContent();
            assert result.contains("You clicked: Cancel");
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
}

