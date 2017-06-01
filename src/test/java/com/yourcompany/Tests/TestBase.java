package com.yourcompany.Tests;

// import Sauce TestNG helper libraries

import com.yourcompany.Pages.PageFactories.MobileWebPageFactory;
import com.yourcompany.Pages.PageFactories.DesktopWebPageFactory;
import com.yourcompany.Pages.PageFactories.PageFactory;
import com.yourcompany.Utils.TOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import com.yourcompany.Utils.SauceUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

// import testng annotations
// import java libraries

/**
 * Simple TestNG test which demonstrates being instantiated via a DataProvider in order to supply multiple browser combinations.
 *
 * @author Neil Manvar
 */
public class TestBase  {

    protected PageFactory pageFactory;

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod
     * @return Two dimensional array of objects with browser, version, and platform information
     */
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                // Browsers on Sauce
                new Object[]{"MicrosoftEdge", "14.14393", "Windows 10", "DesktopWeb"},
                new Object[]{"firefox", "49.0", "Windows 10", "DesktopWeb"},
                new Object[]{"internet explorer", "11.0", "Windows 7", "DesktopWeb"},
                new Object[]{"safari", "latest", "OS X 10.11", "DesktopWeb"},
                new Object[]{"chrome", "54.0", "OS X 10.10", "DesktopWeb"},

                // Emulators on Sauce
                new Object[]{"chrome", "6.0", "Android Emulator", "Android"},
                new Object[]{"Browser", "5.0", "Android Emulator", "Android"},

                // Simulators on Sauce
                new Object[]{"Safari", "10.3", "iPhone 6 Simulator", "iOS"},
                new Object[]{"Safari", "9.3", "iPad 2 Simulator", "DesktopWeb"},

                // iOS Real Devices on TO
                new Object[]{"TestObject - Safari", "10.0", "iPhone_6_Plus_real_us", "iOS"},
                new Object[]{"TestObject - Safari", "9.3", "iPad_Pro_9_7_real_us", "DesktopWeb"},

                // Android Real Devices on TO
                new Object[]{"TestObject - Chrome", "7.0", "Samsung_Galaxy_S8_plus_real_us", "Android"},
                new Object[]{"TestObject - Chrome", "6.0", "Asus_Google_Nexus_7_2013_real", "DesktopWeb"},

        };
    }

    /**
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }


    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part of the test run.
     * @param os Represents the operating system to be used as part of the test run.
     * @param methodName Represents the name of the test case that will be used to identify the test on Sauce.
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */
    protected void createDriver(String browser, String version, String os, String pageobject, String methodName)
            throws MalformedURLException, UnexpectedException {

        DesiredCapabilities capabilities = null;
        String url = "";

        if (TOUtils.isTO(browser)) {
            capabilities = TOUtils.CreateCapabilities(browser, version, os, pageobject, methodName);
            url = TOUtils.getURL();
        } else { // Test Object
            capabilities = SauceUtils.CreateCapabilities(browser, version, os, pageobject, methodName);
            url = SauceUtils.getURL();
        }
        // Launch remote browser and set it as the current thread
        webDriver.set(new RemoteWebDriver(
                new URL(url),
                capabilities));

        // set current sessionId
        sessionId.set(((RemoteWebDriver) getWebDriver()).getSessionId().toString());

        pageFactory = createPageFactory(pageobject);



    }

    private PageFactory createPageFactory(String pageobject) {
        if (pageobject.equals("DesktopWeb")) {
            return new DesktopWebPageFactory();
        } else if (pageobject.equals("Android") || pageobject.equals("iOS")) {
            return new MobileWebPageFactory();
        }
        return null;
    }

    /**
     * Method that gets invoked after test.
     * Dumps browser log and
     * Closes the browser
     */
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        WebDriver driver = getWebDriver();
        Boolean status = result.isSuccess();

        if (TOUtils.isTO(driver)) {
            TOUtils.updateResults(getSessionId(), status);
        } else {
            SauceUtils.updateResults(driver, status);
        }
//        System.out.println("\n\n\n" + webDriver.get().getPageSource() + "\n\n\n");

        driver.quit();
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) getWebDriver()).executeScript("sauce:context=" + text);
    }
}
