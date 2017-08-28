package org.no.cases;

import org.no.data.HappyPathTestsDataProvider;
import org.no.models.SearchResult;
import org.no.pages.popup.SearchTypePopup;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.Collection;

import static org.no.Application.homeScreen;
import static org.no.Application.settingsScreen;
import static org.testng.Assert.assertEquals;

public class HappyPathTests extends BaseTest{
    private String SPLIT_WORDS_PATTERN = "[^a-zA-Z]+";
    private String DEFAULT_SEARCH_OVERLAY = "Search";



    @Test(dataProvider = "basicSearchTest", dataProviderClass = HappyPathTestsDataProvider.class)
    @Severity(SeverityLevel.MINOR)
    @Features("Basic filtering")
    @Title("Basic filtering search test")
    public void T01basicSearchTest(@Parameter("Search phrase")final String testPhrase) throws InterruptedException {
        Collection<SearchResult> results = homeScreen.searchFor(testPhrase).getFirstResults();

        SoftAssert assertResults = new SoftAssert();
        for (SearchResult result : results) {
            String title = result.getTitle();
            assertResults.assertTrue(checkIfStartsWith(title, testPhrase),
                    String.format("Result \"%s\" does not start with \"%s\"", title, testPhrase));
        }
        assertResults.assertAll();
    }

    @Test(dataProvider = "exactSearchTest", dataProviderClass = HappyPathTestsDataProvider.class)
    @Severity(SeverityLevel.MINOR)
    @Features("Exact filtering")
    @Title("Exact filtering search test")
    public void T02exactSearchTest(@Parameter("Search phrase") final String testPhrase) throws InterruptedException {
        homeScreen.goToSettings().selectSearchType().selectOption(SearchTypePopup.Option.EXACT).navigateBack();

        Collection<SearchResult> results = homeScreen.searchFor(testPhrase).getFirstResults();

        SoftAssert assertResults = new SoftAssert();
        for (SearchResult result : results) {
            String title = result.getTitle();
            assertResults.assertTrue(checkIfContainExact(title, testPhrase),
                    String.format("Result \"%s\" does not contain exact words \"%s\"", title, testPhrase));
        }
        assertResults.assertAll();
    }

    @Test(dataProvider = "anySubstringSearchTest", dataProviderClass = HappyPathTestsDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Features("Any Substring filtering")
    @Title("Any Substring filtering search test")
    public void T03anySubstringSearchTest(@Parameter("Search phrase") final String testPhrase) throws InterruptedException {
        homeScreen.goToSettings().selectSearchType().selectOption(SearchTypePopup.Option.ANY).navigateBack();

        Collection<SearchResult> results = homeScreen.searchFor(testPhrase).getFirstResults();

        SoftAssert assertResults = new SoftAssert();
        for (SearchResult result : results) {
            String title = result.getTitle();
            assertResults.assertTrue(checkIfContainsAsSubstring(title, testPhrase), String.format("Result \"%s\" does not contain \"%s\"", title, testPhrase));
        }
        assertResults.assertAll();
    }

    @Test(dataProvider = "fullSearchBasicTest", dataProviderClass = HappyPathTestsDataProvider.class)
    @Severity(SeverityLevel.NORMAL)
    @Features("Full search basic")
    @Title("Full search basic test")
    public void T04fullSearchBasicTest(@Parameter("Search phrase") final String testPhrase) throws InterruptedException {
        homeScreen.goToSettings().enableFullSearch().navigateBack();
        Collection<SearchResult> results = homeScreen.searchFor(testPhrase).getFirstResults();

        SoftAssert assertResults = new SoftAssert();
        for (SearchResult result : results) {
            String title = result.getTitle();
            String definition = result.getDefinition();
            assertResults.assertTrue(   checkIfStartsWith(title,        testPhrase)
                                    ||  checkIfStartsWith(definition,   testPhrase),
                    String.format("Result \"%s\" title and definition does not start with \"%s\"", title, testPhrase));
        }
        assertResults.assertAll();
    }

    @Test(dataProvider = "fullSearchExactTest", dataProviderClass = HappyPathTestsDataProvider.class)
    @Severity(SeverityLevel.NORMAL)
    @Features("Full search exact")
    @Title("Full search exact test")
    public void T05fullSearchExactTest(@Parameter("Search phrase") final String testPhrase) throws InterruptedException {
        homeScreen.goToSettings().enableFullSearch();
        settingsScreen.selectSearchType().selectOption(SearchTypePopup.Option.EXACT).navigateBack();
        Collection<SearchResult> results = homeScreen.searchFor(testPhrase).getFirstResults();

        SoftAssert assertResults = new SoftAssert();
        for (SearchResult result : results) {
            String title = result.getTitle();
            String definition = result.getDefinition();
            assertResults.assertTrue(   checkIfContainExact(title,      testPhrase)
                                    ||  checkIfContainExact(definition, testPhrase),
                    String.format("Result \"%s\" does not contain exact words \"%s\" in title nor in definition", result.getTitle(), testPhrase));
        }
        assertResults.assertAll();
    }

    @Test(dataProvider = "fullSearchAnyTest", dataProviderClass = HappyPathTestsDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Features("Full search any")
    @Title("Full search any test")
    public void T06fullSearchAnyTest(@Parameter("Search phrase") final String testPhrase) throws InterruptedException {
        homeScreen.goToSettings().enableFullSearch();
        settingsScreen.selectSearchType().selectOption(SearchTypePopup.Option.ANY).navigateBack();
        Collection<SearchResult> results = homeScreen.searchFor(testPhrase).getFirstResults();

        SoftAssert assertResults = new SoftAssert();
        for (SearchResult result : results) {
            String title = result.getTitle();
            String definition = result.getDefinition();
            assertResults.assertTrue(   checkIfContainsAsSubstring(title,       testPhrase)
                                    ||  checkIfContainsAsSubstring(definition,  testPhrase),
                    String.format("Result \"%s\" does not contain \"%s\" as substring in title nor in definition", result.getTitle(), testPhrase));
        }
        assertResults.assertAll();
    }

    @Test(dataProvider = "searchBarTest", dataProviderClass = HappyPathTestsDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Features("Search Bar")
    @Title("Search Bar test")
    public void T07clearSearchBarTest(@Parameter("Query string") final String testPhrase) {
        assertEquals(homeScreen.getSearchBarText(), DEFAULT_SEARCH_OVERLAY, "Search bar does not contain text overlay by default");

        homeScreen.enterSearchPhrase(testPhrase);
        assertEquals(homeScreen.getSearchBarText(), testPhrase, "Search bar does not contain entered text");

        homeScreen.clearSearch();
        assertEquals(homeScreen.getSearchBarText(), DEFAULT_SEARCH_OVERLAY, "Search bar does not contain text overlay by default");
    }

    @Test
     @Severity(SeverityLevel.MINOR)
     @Features("Clear Search Results")
     @Title("Clear Search Results test")
     public void T08clearSearchResultsTest() {
        homeScreen.searchFor("word");
        homeScreen.clearSearch();
        Assert.assertFalse(homeScreen.isAnythingFound(), "Search results not cleared");
    }

    @Test
     @Severity(SeverityLevel.MINOR)
     @Features("Add to Favourites")
     @Title("Add to Favourites Test")
     public void T09AddToFavouritesTest() {
        final int index = 1;
        final String searchPhrase = "word";
        homeScreen.searchFor(searchPhrase).setFavouriteByIndex(index, true);
        SearchResult favourite = homeScreen.getFirstResults().get(index);
        Assert.assertTrue(favourite.isFavourite(),
                String.format("Search result \"%s\" was not marked as favourite", favourite.getTitle()));
        homeScreen.clearSearch();
        Assert.assertEquals(homeScreen.getFirstResults().get(0), favourite,
                "Favourite result not displayed when the search bar is cleared");
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Features("Remove from Favourites")
    @Title("Remove from Favourites Test")
    public void T10RemoveFromFavouritesTest() {
        final int index = 1;
        final String searchPhrase = "word";
        homeScreen.searchFor(searchPhrase).setFavouriteByIndex(index, true).clearSearch().setFavouriteByIndex(0, false);

        SearchResult result = homeScreen.searchFor(searchPhrase).getFirstResults().get(index);

        Assert.assertFalse(result.isFavourite(),
                String.format("Favourite mark was not removed from search result \"%s\"", result.getTitle()));
    }

    private boolean checkIfStartsWith(String actualPhrase, String searchPhrase) {
        String[] actualTitleWords = actualPhrase.split(SPLIT_WORDS_PATTERN);
        String[] searchWords = searchPhrase.split(SPLIT_WORDS_PATTERN);
        for (String actualWord : actualTitleWords) {
            for(String searchWord : searchWords) {
                if(actualWord.toLowerCase().startsWith(searchWord.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfContainExact(String actualPhrase, String searchPhrase) {
        String[] searchWords = searchPhrase.split(SPLIT_WORDS_PATTERN);
        String[] actualWords = actualPhrase.split(SPLIT_WORDS_PATTERN);
        for (String actualWord : actualWords) {
            for (String searchWord : searchWords) {
                if (actualWord.equalsIgnoreCase(searchWord)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfContainsAsSubstring(String actualPhrase, String searchPhrase) {
        String[] actualTitleWords = actualPhrase.split(SPLIT_WORDS_PATTERN);
        String[] searchWords = searchPhrase.split(SPLIT_WORDS_PATTERN);
        for (String actualWord : actualTitleWords) {
            for(String searchWord : searchWords) {
                if(actualWord.toLowerCase().contains(searchWord.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
}
