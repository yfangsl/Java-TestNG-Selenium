package com.yourcompany.Pages.MobileNative.Android;

import com.yourcompany.Pages.BasePage;
import com.yourcompany.Pages.GuineaPigPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by neil on 6/3/17.
 */
public class MobileNativeAndroidGuineaPigPage extends BasePage implements GuineaPigPage {
    By h1Text = By.id("Heading1_1");
    By yourComments = By.id("your_comments");
    By commentsTextInput = By.id("comments");
    By submitBtn = By.id("submit");

    public MobileNativeAndroidGuineaPigPage(WebDriver driver) {
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
