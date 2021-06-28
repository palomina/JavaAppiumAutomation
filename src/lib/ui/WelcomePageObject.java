package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private String buttonSkip = "id##org.wikipedia:id/fragment_onboarding_skip_button";
    private String buttonNext = "xpath##//XCUIElementTypeButton[@name='Next']";
    private String linkLearnMore = "xpath##//XCUIElementTypeStaticText[@name='Learn more about Wikipedia']";
    private String linkAddOrEditPreferredLanguages = "xpath##//XCUIElementTypeStaticText[@name='Add or edit preferred languages']";
    private String linkLearnMoreAboutDataCollected = "xpath##//XCUIElementTypeButton[@name='Learn more about data collected']";
    private String buttonGetStarted = "xpath##//XCUIElementTypeButton[@name='Get started']";
    private String textNewWaysToExplore = "id##New ways to explore";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void skip() {
        this.waitElementPresent(
                getLocator(buttonSkip),
                "Button `Skip` not found",
                20
        );

        this.waitElementAndClick(
                getLocator(buttonSkip),
                "Button `Skip` not found",
                20
        );
    }

    public void nextClick() {
        this.waitElementAndClick(
                getLocator(buttonNext),
                "Button `Next` not found",
                20
        );
    }

    public void waitForLearnMoreLink()
    {
        this.waitElementPresent(
                getLocator(linkLearnMore),
                "No link Learn More is presented",
                20
        );
    }

    public void waitForNewWaysToExplore()
    {
        this.waitElementPresent(
                getLocator(textNewWaysToExplore),
                "No text 'New ways to Explore' is presented",
                20
        );
    }

    public void waitForAddOrEditPreferredLanguages()
    {
        this.waitElementPresent(
                getLocator(linkAddOrEditPreferredLanguages),
                "No link Learn More is presented",
                20
        );
    }
    public void waitForLearnMoreAboutDataCollected()
    {
        this.waitElementPresent(
                getLocator(linkLearnMoreAboutDataCollected),
                "No link Learn More is presented",
                20
        );
    }
    public void getStartedClick() {
        this.waitElementAndClick(
                getLocator(buttonGetStarted),
                "Button `Get Started` not found",
                20
        );
    }
}
