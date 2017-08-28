package org.no.pages.popup;

import io.appium.java_client.android.AndroidElement;
import org.no.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePopup extends BasePage {
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    private AndroidElement accept;

    /**
     * Closes Welcome popup
     */
    public void accept() {
        logger.info("Closing the Welcome popup");
        accept.click();
    }
}
