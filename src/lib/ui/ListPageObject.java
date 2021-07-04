package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.ArticlePageObjectFactory;
import org.openqa.selenium.By;

public abstract class ListPageObject extends MainPageObject {

    protected static String
            buttonViewList,
            buttonClosePopup,
            buttonDeleteArticle,
            itemArticle_TPL;

    public ListPageObject(AppiumDriver driver) {
        super(driver);
    }

    /** TEMPLATES **/
    protected String getArticleItem(String name) {
        return itemArticle_TPL.replace("{ARTICLE_NAME}", String.valueOf(name));
    }

    /** TEMPLATES **/

    public void viewList() {
        this.waitElementAndClick(
                getLocator(buttonViewList),
                "Can't click to button `View List`",
                20
        );

        if (Platform.getInstance().isIOS()) {
            this.closePopup();
        }
    }

    public void removeFromList(String articleName) {
        this.waitElementPresent(
                getLocator(getArticleItem(articleName)),
                "First article is not found in list",
                10
        );

        this.swipeElementToLeft(
                getLocator(getArticleItem(articleName)),
                "Can't swipe"
        );

        if (Platform.getInstance().isIOS()) {
            this.waitElementAndClick(
                    getLocator(buttonDeleteArticle),
                    "Button of remove article from saved is not found",
                    10
            );
        }
    }

    public void checkArticleInList(String articleName) {
        this.waitElementPresent(
                getLocator(getArticleItem(articleName)),
                "Article `" + articleName + "` is not found in the list",
                10
        );
    }

    public void clickOnArticleInList(String articleName) {
        this.waitElementAndClick(
                getLocator(getArticleItem(articleName)),
                "Article `" + articleName + "` is not found in the list",
                10
        );
    }

    public String getArticleNameInList(String articleName){
        return this.waitElementAndGetText(
                getLocator(getArticleItem(articleName)),
                "The title of second article in the list is not present",
                10
        );
    }

    public void closePopup() {
        this.waitElementAndClick(
                getLocator(buttonClosePopup),
                "Can't click to button `View List`",
                20
        );
    }
}
