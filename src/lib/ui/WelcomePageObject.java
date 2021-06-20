package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private By buttonSkip = By.id("org.wikipedia:id/fragment_onboarding_skip_button");

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void skip() {
        this.waitElementAndClick(
                buttonSkip,
                "Button `Skip` not found",
                20
        );
    }
}
