package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationObject extends MainPageObject {

    private By buttonBack = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

    public NavigationObject(AppiumDriver driver) {
        super(driver);
    }

    public void back() {
        this.waitElementAndClick(
                buttonBack,
                "Can't click to the button `Back`",
                20
        );
    }
}
