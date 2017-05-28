package com.yourcompany.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by neil on 5/26/17.
 */
public class BasePage {

    protected WebDriver driver;

    public void click(By locator) throws Exception {
        WebElement element = waitForElement(locator);
        element.click();
    }

    private WebElement waitForElement(By locator) {
        return new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
