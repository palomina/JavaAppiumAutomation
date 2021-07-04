package lib.ui;

import io.appium.java_client.AppiumDriver;

public abstract class WelcomePageObject extends MainPageObject {

    protected static String
        buttonSkip,
        buttonNext,
        linkLearnMore,
        linkAddOrEditPreferredLanguages,
        linkLearnMoreAboutDataCollected,
        buttonGetStarted,
        textNewWaysToExplore;

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
