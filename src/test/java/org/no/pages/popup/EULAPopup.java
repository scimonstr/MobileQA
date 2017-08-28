package org.no.pages.popup;

import io.appium.java_client.android.AndroidElement;
import org.no.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class EULAPopup extends BasePage {
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    private AndroidElement accept;

    /**
     * Closes EULA popup by accepting the agreement
     */
    public void accept() {
        logger.info("Waiting EULA popup to be visible");
        WebDriverWait wait = new WebDriverWait(getDriver(), EXPLICIT_WAIT_VALUE);
        logger.info("Accepting EULA");
        wait.until(visibilityOf(accept));
        accept.click();
    }
}
