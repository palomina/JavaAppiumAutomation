package tests;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome(){
        WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.nextClick();

        welcomePageObject.waitForNewWaysToExplore();
        welcomePageObject.nextClick();

        welcomePageObject.waitForAddOrEditPreferredLanguages();
        welcomePageObject.nextClick();

        welcomePageObject.waitForLearnMoreAboutDataCollected();
        welcomePageObject.getStartedClick();
    }
}
