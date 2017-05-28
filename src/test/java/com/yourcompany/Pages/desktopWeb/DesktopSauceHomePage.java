package com.yourcompany.Pages.desktopWeb;

import com.yourcompany.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DesktopSauceHomePage extends BasePage{

    private By signInButton = By.cssSelector("header a[href='/beta/login']");

    public static String url = "https://saucelabs.com";

    public DesktopSauceHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void visitPage() {
        this.driver.get(url);
    }

    public void goToLogin() throws Exception { this.click(signInButton); }
}
