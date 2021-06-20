package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {

    private By buttonSaveToList = By.id("org.wikipedia:id/article_menu_bookmark");
    private By buttonAddToList = By.id("org.wikipedia:id/snackbar_action");
    private By inputListName = By.id("org.wikipedia:id/text_input");
    private By buttonOk = By.xpath("//*[@text='OK']");


    private String itemList_TPL = "//android.view.ViewGroup//android.widget.TextView[@text='{LIST_NAME}']";
    private String articleTitle_TPL = "//*[@text='{ARTICLE_NAME}']";


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
                buttonSaveToList,
                "Can't click to the button `Save to List`",
                20
        );

        this.waitElementAndClick(
                buttonAddToList,
                "Can't click to the button `Add to List`",
                20
        );

        this.waitElementAndSendKeys(
                inputListName,
                listName,
                "Can't set the list's name",
                20
        );

        this.waitElementAndClick(
                buttonOk,
                "Can't click to the button `Ok`",
                20
        );


    }

    public void addToList(String listName) {

        this.waitElementAndClick(
                buttonSaveToList,
                "Can't click to button `Save to List`",
                20
        );

        this.waitElementAndClick(
                buttonAddToList,
                "Can't click to button `Add to List`",
                20
        );

        this.waitElementAndClick(
                By.xpath(getListItem(listName)),
                "Can't add to list with name `"+listName+"`",
                20
        );
    }

    public void checkArticleTitle(String articleName){
        this.waitElementPresent(
                By.xpath(getArticleTitle(articleName)),
                "Can't see to the article title " + getArticleTitle(articleName),
                20
        );

        this.assertElementPresent(
                By.xpath(getArticleTitle(articleName)),
                "Can't show the article's title"
        );
    }

}
