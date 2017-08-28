package org.no.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.no.Application;
import org.no.models.SearchResult;
import org.no.pages.popup.EULAPopup;
import org.no.pages.popup.WelcomePopup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Class represents model of the Home Screen, i.e. first screen that appears after application start
 */
public class HomeScreen extends BasePage {
    /**
     * Access for {@link EULAPopup} popup instance for the screen
     */
    public final EULAPopup eulaPopup = new EULAPopup();

    /**
     * Access for {@link WelcomePopup} popup instance for the screen
     */
    public final WelcomePopup welcomePopup = new WelcomePopup();

    @FindBy(xpath = "//*[@resource-id='com.movinapp.dict.english.american:id/edit_text_search']")
    private AndroidElement searchBar;

    @FindBy(xpath = "//*[@resource-id='android:id/list']")
    private AndroidElement resultsListContainer;

    @FindBy(xpath = "//*[@resource-id='com.movinapp.dict.english.american:id/settings']")
    private AndroidElement settingsButton;

    @FindBy(xpath = "//*[@resource-id='com.movinapp.dict.english.american:id/ad_free_version']")
    private AndroidElement addFreeVersionButton;

    @FindBy(xpath = "//*[@content-desc='More options']")
    private AndroidElement moreOptionsButton;

    @FindBy(xpath = "//*[@resource-id='com.movinapp.dict.english.american:id/btn_clear']")
    private AndroidElement clearSearchButton;

    @FindBy(xpath = "//*[@resource-id='com.movinapp.dict.english.american:id/btn_search']")
    private AndroidElement searchButton;

    /**
     * Searches for a phrase provided as argument
     * @param searchPhrase
     * @return {@link HomeScreen} instance for call chaining
     */
    public HomeScreen searchFor(String searchPhrase) {
        enterSearchPhrase(searchPhrase);

        logger.info("Starting search...");
        searchButton.click();

        logger.info("Waiting till search finishes...");
        WebDriverWait wait = new WebDriverWait(getDriver(), EXPLICIT_WAIT_VALUE);
        wait.until(visibilityOf(resultsListContainer));
        logger.info("Search done");
        return this;
    }

    /**
     * Enters phrase provided as argument to the Search Bar
     * @param searchPhrase
     * @return {@link HomeScreen} instance for call chaining
     */
    public HomeScreen enterSearchPhrase(String searchPhrase) {
        logger.info("Entering search query text \"{}\" to the search bar", searchPhrase);
        searchBar.click();
        searchBar.sendKeys(searchPhrase);
        return this;
    }

    /**
     * Returns text currently displayed at the Search Bar
     */
    public String getSearchBarText() {
        logger.info("Getting text from search bar...");
        String result = searchBar.getText();
        logger.info("Search text is - \"{}\"", result);
        return result;
    }

    /**
     * Checks if search results are available
     */
    public Boolean isAnythingFound() {
        logger.info("Looking for search results...");
        try {
            resultsListContainer.isDisplayed();
            logger.info("Seach results found");
            return true;
        } catch (NoSuchElementException e) {
            logger.info("No search results found");
            return false;
        }
    }

    /**
     * Returns 5 first results visible in the view
     * @return List of {@link SearchResult} instances as a result
     */
    public List<SearchResult> getFirstResults() {
        final Integer FIRST_RESULTS_NUMBER = 5;

        logger.info("Reading search results in view");
        final List<MobileElement> results = resultsListContainer.findElements(new By.ByXPath(".//*[@class='android.widget.LinearLayout'][*[@class='android.widget.CheckBox']]"));
        final int actualResultsSize = results.size();
        List<SearchResult> searchResults = new ArrayList<SearchResult>(FIRST_RESULTS_NUMBER);

        int resultSize = actualResultsSize < FIRST_RESULTS_NUMBER ? actualResultsSize : FIRST_RESULTS_NUMBER;

        for (int i = 0; i < resultSize; i++) {
            logger.info("Getting search result with index {}...", i);
            MobileElement result = results.get(i);

            List<MobileElement> resultLines = result.findElements(new By.ByXPath(".//*[@class='android.widget.TextView']"));

            String title = resultLines.get(0).getText();

            StringBuilder definition = new StringBuilder();
            for (int j = 1; j < resultLines.size(); j++) {
                definition.append(resultLines.get(j).getText()).append("\n");
            }

            Boolean favourite = Boolean.valueOf(
                    result.findElement(new By.ByXPath(".//*[@class='android.widget.CheckBox']")).getAttribute("checked"));
            logger.info("\nTitle:\n\t\"{}\"\nDefinition:\n\t\"{}\"", title, definition);
            searchResults.add(new SearchResult(title, definition.toString(), favourite));
        }

        logger.info("Search results reading done");
        return searchResults;
    }

    /**
     * Navigates to the Settings screen by clicking on the gear button
     * @return {@link SettingsScreen} instance for call chaining
     */
    public SettingsScreen goToSettings() {
        logger.info("Going to Settings screen...");
        settingsButton.click();
        return Application.settingsScreen;
    }

    /**
     * Clears the Search Bar contents
     * @return HomeScreen instance for call chaining
     */
    public HomeScreen clearSearch() {
        logger.info("Clearing the search bar by clicking on a X button");
        clearSearchButton.click();
        return this;
    }

    /**
     * Adds search result to favourites list by clicking on it's "star" button
     */
    public HomeScreen setFavouriteByIndex(int index, Boolean favourite) {
        MobileElement star = resultsListContainer.findElements(new By.ByXPath(
                ".//*[@class='android.widget.CheckBox']")).get(index);

        if(Boolean.valueOf(star.getAttribute("checked")) ^ favourite) {
            logger.info("Clicking on a star button for result with index {}", index);
            star.click();
        } else {
            logger.info("No action required");
        }
        return this;
    }
}
