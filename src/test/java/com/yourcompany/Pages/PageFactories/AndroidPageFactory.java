package com.yourcompany.Pages.PageFactories;

import com.yourcompany.Pages.Android.AndroidSauceHomePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by neil on 5/27/17.
 */
public class AndroidPageFactory implements PageFactory {
    @Override
    public AndroidSauceHomePage createSauceHomePage(WebDriver driver) {
        return new AndroidSauceHomePage(driver);
    }
}
