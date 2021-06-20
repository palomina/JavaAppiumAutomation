package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;

public class LIstTest extends CoreTestCase {

    @Test
    public void testSaveTwoArticle() {
        String word = "Java";
        String listName = "List1";
        String firstArticleName;
        String secondArticleName;
        String titleArticleFromList;


        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.skip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchByInputText(word);

        searchPageObject.checkIsResultsPresents();

        firstArticleName = searchPageObject.getArticleNameByIndex(1);
        searchPageObject.chooseArticleByIndex(1);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.addToNewList(listName);

        NavigationObject navigationObject = new NavigationObject(driver);
        navigationObject.back();

        secondArticleName = searchPageObject.getArticleNameByIndex(2);
        searchPageObject.chooseArticleByIndex(2);
        articlePageObject.addToList(listName);

        ListPageObject listPageObject = new ListPageObject(driver);
        listPageObject.viewList();

        listPageObject.removeFromList(firstArticleName);

        listPageObject.checkArticleInList(secondArticleName);

        titleArticleFromList = listPageObject.getArticleNameInList(secondArticleName);

        Assert.assertEquals("The name of the article is not the same is expected!", secondArticleName, titleArticleFromList);
    }
}
