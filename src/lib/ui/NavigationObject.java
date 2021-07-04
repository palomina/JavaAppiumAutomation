package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public abstract class NavigationObject extends MainPageObject {

    protected static String buttonBack;

    public NavigationObject(AppiumDriver driver) {
        super(driver);
    }

    public void back() {
        this.waitElementAndClick(
                getLocator(buttonBack),
                "Can't click to the button `Back`",
                20
        );
    }
}
