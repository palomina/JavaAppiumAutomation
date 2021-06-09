import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    private By buttonSkip = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private By buttonClearSearch = By.id("org.wikipedia:id/search_close_btn");
    private By fieldsSearch = By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//android.widget.TextView");
    private By inputSearch = By.id("org.wikipedia:id/search_src_text");
    private By panelSearchResult = By.id("org.wikipedia:id/search_results_list");
    private By itemSearchResult = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup//*[@resource-id='org.wikipedia:id/page_list_item_title']");
    private By panelSearchEmptyResult = By.id("org.wikipedia:id/search_empty_container");

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\p_alo\\IdeaProjects\\JavaAppiumAutomation\\apks\\org.wikipedia_50359_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        System.out.println("First test run");
    }

    public void waitElementAndClick(By by, String errorMessage, int timeout) {
        new WebDriverWait(driver, timeout)
                .withMessage(errorMessage)
                .until(ExpectedConditions.presenceOfElementLocated(by))
                .click();
    }

    public void waitElementAndSendKeys(By by, String text, String errorMessage, int timeout) {
        new WebDriverWait(driver, timeout)
                .withMessage(errorMessage)
                .until(ExpectedConditions.presenceOfElementLocated(by))
                .sendKeys(text);
    }

    public void assertElementHasText(By by, String text, String errorMessage) {
        WebElement element = driver.findElement(by);
        Assert.assertTrue(errorMessage, element.getText().contains(text));
    }

    public void assertCountElements(By by, String errorMessage) {
        List<WebElement> elements = driver.findElements(by);
        Assert.assertTrue(errorMessage, elements.size() > 0);
    }

    public void assertElementPresent(By by, String errorMessage, int timeout) {
        new WebDriverWait(driver, timeout)
                .withMessage(errorMessage)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void assertElementsHasText(By by, String text, String errorMessage) {
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element : elements) {
            Assert.assertTrue(errorMessage, element.getText().contains(text));
        }
    }


    @Test
    public void checkText() {
        waitElementAndClick(
                buttonSkip,
                "Button `Skip` not found",
                20
        );

        waitElementAndClick(
                fieldsSearch,
                "Can't click on search panel",
                20
        );

        assertElementHasText(
                inputSearch,
                "Search Wikipedia",
                "Unexpected text"
        );
    }


    @Test
    public void searchWordAndCleanResult() {
        String word = "Java";

        waitElementAndClick(
                buttonSkip,
                "Button `Skip` is not found",
                20
        );

        waitElementAndClick(
                fieldsSearch,
                "Can't click on search panel",
                20
        );

        waitElementAndSendKeys(
                inputSearch,
                word,
                "Can't set the search word",
                20
        );

        assertElementPresent(
                panelSearchResult,
                "Panel of search result is not found",
                20
        );

        assertElementPresent(
                itemSearchResult,
                "Items of search result are not found",
                20
        );

        assertCountElements(
                itemSearchResult,
                "Unexpected text"
        );

        waitElementAndClick(
                buttonClearSearch,
                "Button `Clear` is not found",
                20
        );

        assertElementPresent(
                panelSearchEmptyResult,
                "Panel of empty result is not found",
                20
        );

    }


    @Test
    public void searchWordAndCheckResult() {
        String word = "Java";

        waitElementAndClick(
                buttonSkip,
                "Button `Skip` not found",
                20
        );

        waitElementAndClick(
                fieldsSearch,
                "Can't click to search panel",
                20
        );

        waitElementAndSendKeys(
                inputSearch,
                word,
                "Can't set the search word",
                20
        );

        assertElementPresent(
                panelSearchResult,
                "Panel of search result is not found",
                20
        );

        assertElementPresent(
                itemSearchResult,
                "Items of search result are not found",
                20
        );

        assertElementsHasText(
                itemSearchResult,
                word,
                "Unexpected text"
        );

        waitElementAndClick(
                buttonClearSearch,
                "Button `Clear` is not found",
                20
        );

        assertElementPresent(
                panelSearchEmptyResult,
                "Panel of empty result is not found",
                20
        );

    }
}
