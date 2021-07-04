package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        buttonSaveToList = "id##Save for later";
        buttonAddToList = "id##Save for later";
        buttonRemoveFromList = "id##Saved. Activate to unsave.";
        articleTitle_TPL = "xpath##//*[@name='{ARTICLE_NAME}']";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
