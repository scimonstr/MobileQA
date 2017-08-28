package org.no.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final static Integer IMPLICIT_WAIT_VALUE = 10;
    protected final static TimeUnit IMPLICIT_WAI_UNIT = TimeUnit.SECONDS;
    protected final static Integer EXPLICIT_WAIT_VALUE = 10;
    private static AndroidDriver<AndroidElement> driver;

    {PageFactory.initElements(new AppiumFieldDecorator(driver), this);}

    /**
     * Initializes driver to be shared with extending classes
     * @param driverInstance
     */
    public static void initDriver(AndroidDriver<AndroidElement> driverInstance) {
        //driver can be initialized once
        if(driver == null) {
            driver = driverInstance;
            driver.manage().timeouts().implicitlyWait(BasePage.IMPLICIT_WAIT_VALUE, BasePage.IMPLICIT_WAI_UNIT);
        }
    }

    /**
     * Performs Android Back key push
     */
    public void navigateBack() {
        logger.info("Pressing back button");
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    /**
     * Access to the driver instance for extending classes
     * @return
     */
    protected AndroidDriver<AndroidElement> getDriver() {
        return driver;
    }
}
