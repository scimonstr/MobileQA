package org.no.pages;

import io.appium.java_client.android.AndroidElement;
import org.no.pages.popup.EULAPopup;
import org.no.pages.popup.SearchTypePopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.no.Application.settingsScreen;
/**
 * Class represents model of Settings Screen page
 */
public class SettingsScreen extends BasePage {
    /**
     * Access for {@link SearchTypePopup} popup instance for the screen
     */
    public final SearchTypePopup searchTypePopup = new SearchTypePopup();

    @FindBy(xpath = "//*[@resource-id='android:id/list']/node[position()=1]")
    private AndroidElement searchCategory;

    @FindBy(xpath = "//*[@resource-id='android:id/list']/child::*[position()=2]")
    private AndroidElement fullSearchSetting;

    @FindBy(xpath = "//*[@resource-id='android:id/list']/child::*[position()=3]")
    private AndroidElement searchTypeSetting;

    @FindBy(xpath = "//*[@resource-id='android:id/list']/node[position()=4]")
    private AndroidElement userInterfaceCategory;

    @FindBy(xpath = "//*[@resource-id='android:id/list']/child::*[position()=5]")
    private AndroidElement biggerTextSetting;

    @FindBy(xpath = "//*[@resource-id='android:id/list']/node[position()=6]")
    private AndroidElement gradientBackgroundSetting;

    @FindBy(xpath = "//*[@resource-id='android:id/list']/node[position()=7]")
    private AndroidElement textToSpeechCategory;

    @FindBy(xpath = "//*[@resource-id='android:id/list']/node[position()=7]")
    private AndroidElement enableSpeech;

    @FindBy(xpath = "//*[@resource-id='android:id/list']/node[position()=8]")
    private AndroidElement speechAccentSetting;

    /**
     * Opens Select Search Type popup by clicking on the Search Type button on the Setting Screen
     * @return {@link org.no.pages.popup.SearchTypePopup} instance for call chaining
     */
    public SearchTypePopup selectSearchType() {
        logger.info("Opening Search Type popup");
        searchTypeSetting.click();
        return searchTypePopup;
    }

    /**
     * Enables Full Search setting by clicking on appropriate checkbox on the Settings Screen
     * @return {@link org.no.pages.SettingsScreen} instance for call chaining
     */
    public SettingsScreen enableFullSearch() {
        logger.info("Enabling Full Search setting");
        if(!Boolean.valueOf(fullSearchSetting.findElement(new By.ByXPath(".//*[@resource-id='android:id/widget_frame']")).getAttribute("checked"))) {
            logger.info("Full Search setting enabled");
            fullSearchSetting.click();
        } else {
            logger.info("Full Search setting was already enabled, no action performed");
        }

        return settingsScreen;
    }
 }
