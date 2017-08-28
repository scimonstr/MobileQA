package org.no.pages.popup;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.no.pages.BasePage;
import org.no.pages.SettingsScreen;

import static org.no.Application.settingsScreen;

public class SearchTypePopup extends BasePage {
    public enum Option {
        START,
        EXACT,
        ANY
    }

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/title_template']")
    private AndroidElement title;

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/text1'][1]")
    private AndroidElement wordStartOption;

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/text1'][2]")
    private AndroidElement exactMatchOption;

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/text1'][3]")
    private AndroidElement anySubstring;

    public SettingsScreen selectOption(Option option) {
        logger.info("Selecting the Search Type option \"{}\"", option.name());
        switch (option) {
            case START:
                wordStartOption.click();
                break;
            case EXACT:
                exactMatchOption.click();
                break;
            case ANY:
                anySubstring.click();
        }
        return settingsScreen;
    }
}
