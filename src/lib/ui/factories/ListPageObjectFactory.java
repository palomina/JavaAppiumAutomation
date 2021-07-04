package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.ListPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidListPageObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSListPageObject;

public class ListPageObjectFactory {
    public static ListPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidListPageObject(driver);
        } else {
            return new IOSListPageObject(driver);
        }
    }
}
