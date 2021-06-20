package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

public class ChangeAppConditionTest extends CoreTestCase {

    @Test
    public void testOrientation1() {
        String word = "Java";

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.skip();


        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();

        this.rotateToLANDSCAPE();

        searchPageObject.searchByInputText(word);
    }

    @Test
    public void testOrientation2() {
        String word = "Java";

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.skip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchByInputText(word);

        assertEquals("Orientation is not default", ScreenOrientation.PORTRAIT, driver.getOrientation());
    }
}
