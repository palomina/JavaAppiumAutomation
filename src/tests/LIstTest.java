package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
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


        WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);
        welcomePageObject.skip();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchByInputText(word);

        searchPageObject.checkIsResultsPresents();

        firstArticleName = searchPageObject.getArticleNameByIndex(1);
        searchPageObject.chooseArticleByIndex(1);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.addToNewList(listName);

        NavigationObject navigationObject = NavigationObjectFactory.get(driver);
        navigationObject.back();

        secondArticleName = searchPageObject.getArticleNameByIndex(2);
        searchPageObject.chooseArticleByIndex(2);
        articlePageObject.addToList(listName);

        ListPageObject listPageObject = ListPageObjectFactory.get(driver);

        if (Platform.getInstance().isIOS()) {
            navigationObject.back();
            searchPageObject.cancelSearch();
        }

        listPageObject.viewList();

        listPageObject.removeFromList(firstArticleName);

        if (Platform.getInstance().isAndroid()) {
            listPageObject.checkArticleInList(secondArticleName);

            titleArticleFromList = listPageObject.getArticleNameInList(secondArticleName);

            Assert.assertEquals("The name of the article is not the same is expected!", secondArticleName, titleArticleFromList);
        } else {
            listPageObject.clickOnArticleInList(secondArticleName);

            articlePageObject.removeFromList();
        }
    }
}
