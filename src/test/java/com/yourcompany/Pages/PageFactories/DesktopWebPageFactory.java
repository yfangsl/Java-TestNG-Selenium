package com.yourcompany.Pages.PageFactories;

import com.yourcompany.Pages.DesktopWeb.DesktopSauceHomePage;
import com.yourcompany.Pages.DesktopWeb.DesktopSauceLoginPage;
import com.yourcompany.Pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;

/**
 * Created by neil on 5/27/17.
 */
public class DesktopWebPageFactory implements PageFactory {
    @Override
    public DesktopSauceHomePage createSauceHomePage(WebDriver driver) {
        return new DesktopSauceHomePage(driver);
    }

    @Override
    public SauceLoginPage createSauceLoginPage(WebDriver driver) {
        return new DesktopSauceLoginPage(driver);
    }
}
