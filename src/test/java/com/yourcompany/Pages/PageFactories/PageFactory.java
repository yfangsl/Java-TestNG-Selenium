package com.yourcompany.Pages.PageFactories;
import com.yourcompany.Pages.desktopWeb.DesktopSauceHomePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by neil on 3/1/17.
 */
public interface PageFactory {
    DesktopSauceHomePage createSauceHomePage(WebDriver driver);
}