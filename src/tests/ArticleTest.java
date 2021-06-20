package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class ArticleTest extends CoreTestCase {

    @Test
    public void testCheckArticleNamePresent() {
        String word = "Java";
        String firstArticleName;

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.skip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchByInputText(word);
        searchPageObject.checkIsResultsPresents();

        firstArticleName = searchPageObject.getArticleNameByIndex(1);
        searchPageObject.chooseArticleByIndex(1);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.checkArticleTitle(firstArticleName);
    }

}
