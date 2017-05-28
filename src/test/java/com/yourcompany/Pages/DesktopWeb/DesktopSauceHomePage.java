package com.yourcompany.Pages.DesktopWeb;

import com.yourcompany.Pages.BasePage;
import com.yourcompany.Pages.SauceHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DesktopSauceHomePage extends BasePage implements SauceHomePage {

    public DesktopSauceHomePage(WebDriver driver) {
        super(driver);
    }
    private By signInButton = By.cssSelector("header a[href='/beta/login']");

    public static String url = "https://www.saucelabs.com";

    public void visitPage() {
        this.driver.get(url);
    }

    public void goToLogin() throws Exception { click(signInButton); }
}
