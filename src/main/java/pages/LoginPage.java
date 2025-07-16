package pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage  {

    private final String username = "#user-name";
    private final String password = "#password";
    private final String loginButton = "#login-button";

    public LoginPage(Page page) {
        super(page);
    }
    public void navigateTo(String url) {
        page.navigate(url);
    }
    public void login() {
        waitForElement(username);
        page.fill(username, "standard_user");
        waitForElement(password);
        page.fill(password, "secret_sauce");
        waitForElement(loginButton);
        page.click(loginButton);
    }

}
