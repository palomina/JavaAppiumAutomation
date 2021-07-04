package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationObject;

public class IOSNavigationObject extends NavigationObject {

    static {
        buttonBack = "xpath##//XCUIElementTypeButton[@name='Back']";
    }

    public IOSNavigationObject(AppiumDriver driver) {
        super(driver);
    }
}
