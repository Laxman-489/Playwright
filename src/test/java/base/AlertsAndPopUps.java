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

        public void handleJsDialog(Page page, String action, String inputMessage) {
            page.onceDialog(dialog -> {
                System.out.println("Dialog Text: " + dialog.message());

                if (action == null) {
                    return;
                }

                if (action.equals("accept")) {
                    if (inputMessage != null) {
                        dialog.accept(inputMessage); // For prompt dialogs
                    } else {
                        dialog.accept();
                    }
                } else if (action.equals("dismiss")) {
                    dialog.dismiss();
                }
            });
        }


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
        public void testJsAlert( ) {
            handleJsDialog(page , "accept", null);
            page.locator("text=Click for JS Alert").click();

            String result = page.locator("#result").textContent();
            System.out.println("Result after JS Alert: " + result);
            assert result.contains("You successfully clicked an alert");
        }

        @Test
        public void testJsConfirmAccept() {
            handleJsDialog(page , "accept", null);
            page.locator("text=Click for JS Confirm").click();
            String result = page.locator("#result").textContent();
            System.out.println("Result after Confirm Accept: " + result);
            assert result.contains("You clicked: Ok");
        }

        @Test
        public void testJsConfirmDismiss() {
            handleJsDialog(page , "dismiss", null);
            page.locator("text=Click for JS Confirm").click();
            String result = page.locator("#result").textContent();
            assert result.contains("You clicked: Cancel");
        }

        @Test
        public void testJsPromptAcceptWithInput() {
            handleJsDialog(page, "accept", "Playwright Test");
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

