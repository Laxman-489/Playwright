package pages;

import com.microsoft.playwright.Page;

public class LoginPage  {
    private final Page page;
    private final String username = "#user-name";
    private final String password = "#password";
    private final String loginButton = "#login-button";
    public LoginPage(Page page) {
        this.page = page;
    }
    public void navigateTo(String url) {
        page.navigate(url);
    }
    public void login() {
        page.fill(username, "standard_user");
        page.fill(password, "secret_sauce");
        page.click(loginButton);
    }

}
