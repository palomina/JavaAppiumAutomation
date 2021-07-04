package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidNavigationObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSNavigationObject;

public class NavigationObjectFactory {
    public static NavigationObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationObject(driver);
        } else {
            return new IOSNavigationObject(driver);
        }
    }
}
