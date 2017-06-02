package com.yourcompany.Tests;

import com.yourcompany.Pages.GuineaPigPage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.Random;

/**
 * Created by mehmetgerceker on 12/7/15.
 */

public class FollowLinkTest extends TestBase {

    /**
     * Runs a simple test verifying link can be followed.
     *
     * @throws InvalidElementStateException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void verifyLinkTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        Random rand = new Random();
        int num = rand.nextInt(3);
        System.out.println("num is " + num);


        Assert.assertNotEquals(num, 2);
    }

    @Test(dataProvider = "hardCodedBrowsers")
    public void verifyHeaderTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        Random rand = new Random();
        int num = rand.nextInt(3);
        System.out.println("num is " + num);


        Assert.assertNotEquals(num, 2);
    }

    @Test(dataProvider = "hardCodedBrowsers")
    public void verifyFooterTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        Random rand = new Random();
        int num = rand.nextInt(3);
        System.out.println("num is " + num);


        Assert.assertNotEquals(num, 2);
    }

    @Test(dataProvider = "hardCodedBrowsers")
    public void verifyBodyTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        Random rand = new Random();
        int num = rand.nextInt(3);
        System.out.println("num is " + num);


        Assert.assertNotEquals(num, 2);
    }



}