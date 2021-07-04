package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ListPageObject;

public class AndroidListPageObject extends ListPageObject {

    static {
        buttonViewList = "xpath##//*[@text='VIEW LIST']";
        itemArticle_TPL = "xpath##//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{ARTICLE_NAME}']";
    }

    public AndroidListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
