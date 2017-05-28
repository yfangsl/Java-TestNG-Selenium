package com.yourcompany.Pages.PageFactories;

import com.yourcompany.Pages.MobileWeb.MobileWebSauceHomePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by neil on 5/27/17.
 */
public class MobileWebPageFactory implements PageFactory {
    @Override
    public MobileWebSauceHomePage createSauceHomePage(WebDriver driver) {
        return new MobileWebSauceHomePage(driver);
    }
}
