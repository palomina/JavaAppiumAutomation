package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class SearchTest extends CoreTestCase {

    @Test
    public void testCheckText() {
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.skip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
    }

    @Test
    public void testSearchWordAndCleanResult() {
        String word = "Java";

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.skip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchByInputText(word);
        searchPageObject.checkIsResultsPresents();
        searchPageObject.clearResults();
    }

    @Test
    public void testSearchWordAndCheckResult() {
        String word = "Java";

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.skip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchByInputText(word);
        searchPageObject.checkIsResultsPresents();
        searchPageObject.checkTextResults(word);
        searchPageObject.clearResults();
    }
}
