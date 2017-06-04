package com.yourcompany.Pages.MobileNative.iOS;

import com.yourcompany.Pages.BasePage;
import com.yourcompany.Pages.GuineaPigPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by neil on 6/3/17.
 */
public class MobileNativeIosGuineaPigPage extends BasePage implements GuineaPigPage {

    By h1Text = By.id("h1Text");

    By yourComments = By.id("submittedComments");

    By commentsTextInput = By.id("comments");

    By submitBtn = By.id("submit");

    public MobileNativeIosGuineaPigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void submitComment(String comment) {
        driver.findElement(commentsTextInput).click();
        driver.findElement(commentsTextInput).sendKeys(comment);
        driver.findElement(h1Text).click();
        driver.findElement(submitBtn).click();
    }

    @Override
    public String getSubmittedCommentText() {
        return driver.findElement(yourComments).getText();
    }
}
