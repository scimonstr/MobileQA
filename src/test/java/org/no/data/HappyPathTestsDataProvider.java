package org.no.data;

import org.testng.annotations.DataProvider;

public class HappyPathTestsDataProvider {
    @DataProvider(name = "basicSearchTest")
    public static Object[][] basicSearchPhrasesProvider() {
        return new Object[][] {{"hello"}};
    }

    @DataProvider(name = "exactSearchTest")
    public static Object[][] exactSearchPhrasesProvider() {
        return new Object[][] {{"big"}};
    }

    @DataProvider(name = "anySubstringSearchTest")
    public static Object[][] anySubstringSearchPhrasesProvider() {
        return new Object[][] {{"ugly"}};
    }

    @DataProvider(name = "fullSearchBasicTest")
    public static Object[][] fullSearchBasicProviderProvider() {
        return new Object[][] {{"ogre"}};
    }

    @DataProvider(name = "fullSearchExactTest")
    public static Object[][] fullSearchExactTest() {
        return new Object[][] {{"dingo"}};
    }

    @DataProvider(name = "fullSearchAnyTest")
    public static Object[][] fullSearchAnyTest() {
        return new Object[][] {{"beast"}};
    }

    @DataProvider(name = "searchBarTest")
    public static Object[][] searchBarTest() {
        return new Object[][] {{"Very long sentence..."}};
    }
}
