package com.yourcompany.Pages.PageFactories;
import com.yourcompany.Pages.SauceHomePage;
import com.yourcompany.Pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;

/**
 * Created by neil on 3/1/17.
 */
public interface PageFactory {
    SauceHomePage createSauceHomePage(WebDriver driver);

    SauceLoginPage createSauceLoginPage(WebDriver driver);
}