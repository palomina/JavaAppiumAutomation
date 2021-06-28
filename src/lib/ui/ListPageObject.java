package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ListPageObject extends MainPageObject {

    private String buttonViewList = "xpath##//*[@text='VIEW LIST']";

    private final String itemArticle_TPL = "xpath##//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{ARTICLE_NAME}']";

    public ListPageObject(AppiumDriver driver) {
        super(driver);
    }

    /** TEMPLATES **/
    private String getArticleItem(String name) {
        return itemArticle_TPL.replace("{ARTICLE_NAME}", String.valueOf(name));
    }

    /** TEMPLATES **/

    public void viewList() {
        this.waitElementAndClick(
                getLocator(buttonViewList),
                "Can't click to button `View List`",
                20
        );
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
    }

    public void checkArticleInList(String articleName) {
        this.waitElementPresent(
                getLocator(getArticleItem(articleName)),
                "Article `"+articleName+"` is not found in the list",
                10
        );
    }

    public String getArticleNameInList(String articleName){
        return this.waitElementAndGetAttribute(
                getLocator(getArticleItem(articleName)),
                "text",
                "The title of second article in the list is not present",
                10
        );
    }
}
