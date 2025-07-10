package base;

import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest{

    @Test
    public void testLogin() {

        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login();

        // Add assertions or further actions to verify successful login
        // For example, check if the URL is correct or if a specific element is visible
    }
}
