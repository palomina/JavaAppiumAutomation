package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {

    private String buttonSaveToList = "id##org.wikipedia:id/article_menu_bookmark";
    private String buttonAddToList = "id##org.wikipedia:id/snackbar_action";
    private String inputListName = "id##org.wikipedia:id/text_input";
    private String buttonOk = "xpath##//*[@text='OK']";


    private String itemList_TPL = "xpath##//android.view.ViewGroup//android.widget.TextView[@text='{LIST_NAME}']";
    private String articleTitle_TPL = "xpath##//*[@text='{ARTICLE_NAME}']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /** TEMPLATES **/
    private String getListItem(String listName) {
        return itemList_TPL.replace("{LIST_NAME}", String.valueOf(listName));
    }

    private String getArticleTitle(String articleName) {
        return articleTitle_TPL.replace("{ARTICLE_NAME}", String.valueOf(articleName));
    }

    /** TEMPLATES **/

    public void addToNewList(String listName) {
        this.waitElementAndClick(
                getLocator(buttonSaveToList),
                "Can't click to the button `Save to List`",
                20
        );

        this.waitElementAndClick(
                getLocator(buttonAddToList),
                "Can't click to the button `Add to List`",
                20
        );

        this.waitElementAndSendKeys(
                getLocator(inputListName),
                listName,
                "Can't set the list's name",
                20
        );

        this.waitElementAndClick(
                getLocator(buttonOk),
                "Can't click to the button `Ok`",
                20
        );


    }

    public void addToList(String listName) {

        this.waitElementAndClick(
                getLocator(buttonSaveToList),
                "Can't click to button `Save to List`",
                20
        );

        this.waitElementAndClick(
                getLocator(buttonAddToList),
                "Can't click to button `Add to List`",
                20
        );

        this.waitElementAndClick(
                getLocator(getListItem(listName)),
                "Can't add to list with name `"+listName+"`",
                20
        );
    }

    public void checkArticleTitle(String articleName){
        this.waitElementPresent(
                getLocator(getArticleTitle(articleName)),
                "Can't see to the article title " + getArticleTitle(articleName),
                20
        );

        this.assertElementPresent(
                getLocator(getArticleTitle(articleName)),
                "Can't show the article's title"
        );
    }

}
