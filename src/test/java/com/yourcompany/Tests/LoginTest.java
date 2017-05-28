package com.yourcompany.Tests;

import com.yourcompany.Pages.desktopWeb.DesktopSauceHomePage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;


/**
 * Created by neil
 */

public class LoginTest extends TestBase {

    /**
     * Runs a simple test verifying if the comment input is functional.
     * @throws InvalidElementStateException
     */
    @org.testng.annotations.Test(dataProvider = "hardCodedBrowsers")
    public void verifyCommentInputTest(String browser, String version, String os, String pageobject, Method method)
            throws Exception {
        this.createDriver(browser, version, os, pageobject,method.getName());
        WebDriver driver = getWebDriver();


        DesktopSauceHomePage page = new DesktopSauceHomePage(driver);
        page.visitPage();
        page.goToLogin();

//        x.invalidSignin
//        assert

    }

}