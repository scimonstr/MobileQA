package org.no.cases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.no.pages.BasePage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.no.Application.homeScreen;

public abstract class BaseTest {
    private static AndroidDriver<AndroidElement> driver;

    private static Capabilities initCapabilities() throws IOException {
        Properties capabilitiesProperties = new Properties();
        capabilitiesProperties.load(BaseTest.class.getClassLoader().getResourceAsStream("capabilities.properties"));
        Map<String, String> capabilities = new HashMap<String, String>();

        for (String property : capabilitiesProperties.stringPropertyNames()) {
            capabilities.put(property, capabilitiesProperties.getProperty(property));
        }

        return new DesiredCapabilities(capabilities);
    }

    private static URL initAppiumServerURL() throws MalformedURLException {
        final Boolean runLocal = Boolean.valueOf(System.getProperty("org.no.run.local"));
        URL appiumServerURL;

        if (runLocal) {
            appiumServerURL = new URL("http://127.0.0.1:4723/wd/hub");
        } else {
            final String SAUCE_USER = System.getenv("SAUCE_USER");
            final String SAUCE_KEY = System.getenv("SAUCE_KEY");
            appiumServerURL = new URL(String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub",
                    SAUCE_USER, SAUCE_KEY));
        }

        return appiumServerURL;
    }

    @BeforeSuite
    public static void setupSuite() throws IOException {
        driver = new AndroidDriver<AndroidElement>(initAppiumServerURL(), initCapabilities());
        BasePage.initDriver(driver);
    }

    @AfterSuite
    public static void tearDownSuite() {
        driver.quit();
    }

    @BeforeMethod
    public static void commonActions() throws InterruptedException {
        homeScreen.eulaPopup.accept();
        homeScreen.welcomePopup.accept();
    }

    @AfterMethod
    public static void tearDownApp() {
        driver.resetApp();
    }
}
