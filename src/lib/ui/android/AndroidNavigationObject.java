package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationObject;

public class AndroidNavigationObject extends NavigationObject {

    static {
        buttonBack = "xpath##//android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidNavigationObject(AppiumDriver driver) {
        super(driver);
    }
}
