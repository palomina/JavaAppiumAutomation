package tests.iOS;

import lib.CoreTestCase;
import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void testPassThroughWelcome(){
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
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
