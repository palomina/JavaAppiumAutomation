import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
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
    private By itemSearchFirstResult = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup[1]//*[@resource-id='org.wikipedia:id/page_list_item_title']");
    private By itemSearchSecondResult = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup[2]//*[@resource-id='org.wikipedia:id/page_list_item_title']");
    private By panelSearchEmptyResult = By.id("org.wikipedia:id/search_empty_container");
    private By buttonBack = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

    private By buttonSaveToList = By.id("org.wikipedia:id/article_menu_bookmark");
    private By buttonAddToList = By.id("org.wikipedia:id/snackbar_action");
    private By inputListName = By.id("org.wikipedia:id/text_input");
    private By buttonOk = By.xpath("//*[@text='OK']");
    private By buttonViewList = By.xpath("//*[@text='VIEW LIST']");
    private By nameOfArticleInArticle = By.xpath("//*[@text='VIEW LIST']");

    private ScreenOrientation defaultOrientation = ScreenOrientation.PORTRAIT;


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

        if (driver.getOrientation() != defaultOrientation) {
            driver.rotate(defaultOrientation);
        }
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

    public WebElement waitElementPresent(By by, String errorMessage, int timeout) {
       return new WebDriverWait(driver, timeout)
                .withMessage(errorMessage)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public String waitElementAndGetAttribute(By by, String attributeName, String errorMessage, int timeout) {
       return new WebDriverWait(driver, timeout)
                .withMessage(errorMessage)
                .until(ExpectedConditions.presenceOfElementLocated(by))
               .getAttribute(attributeName);
    }

    public void assertElementHasText(By by, String text, String errorMessage) {
        WebElement element = driver.findElement(by);
        Assert.assertTrue(errorMessage, element.getText().contains(text));
    }

    public void assertCountElements(By by, String errorMessage) {
        List<WebElement> elements = driver.findElements(by);
        Assert.assertTrue(errorMessage, elements.size() > 0);
    }


    public void assertElementsHasText(By by, String text, String errorMessage) {
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element : elements) {
            Assert.assertTrue(errorMessage, element.getText().contains(text));
        }
    }

    public void assertElementPresent(By by, String errorMessage) {
        List elements = driver.findElements(by);
        if (elements.size()==0) {
            throw new AssertionError(errorMessage + " with element: '" + by.toString() + "'");
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitElementPresent(by, errorMessage, 10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();
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

        waitElementPresent(
                panelSearchResult,
                "Panel of search result is not found",
                20
        );

        waitElementPresent(
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

        waitElementPresent(
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

        waitElementPresent(
                panelSearchResult,
                "Panel of search result is not found",
                20
        );

        waitElementPresent(
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

        waitElementPresent(
                panelSearchEmptyResult,
                "Panel of empty result is not found",
                20
        );

    }


    @Test
    public void saveTwoArticle() {
        String word = "Java";
        String listName = "List1";
        String firstArticleName;
        String secondArticleName;
        String titleArticleFromList;


        waitElementAndClick(
                buttonSkip,
                "Button `Skip` not found",
                20
        );

        waitElementAndClick(
                fieldsSearch,
                "Can't click to the search panel",
                20
        );

        waitElementAndSendKeys(
                inputSearch,
                word,
                "Can't set the search word",
                20
        );

        waitElementPresent(
                panelSearchResult,
                "Panel of search result is not found",
                20
        );

        firstArticleName = waitElementAndGetAttribute(
                itemSearchFirstResult,
                "text",
                "The first item of search result is not found",
                20
        );

        waitElementAndClick(
                itemSearchFirstResult,
                "Can't click to the first article",
                20
        );

        waitElementAndClick(
                buttonSaveToList,
                "Can't click to the button `Save to List`",
                20
        );

        waitElementAndClick(
                buttonAddToList,
                "Can't click to the button `Add to List`",
                20
        );

        waitElementAndSendKeys(
                inputListName,
                listName,
                "Can't set the list's name",
                20
        );

        waitElementAndClick(
                buttonOk,
                "Can't click to the button `Ok`",
                20
        );

        waitElementAndClick(
                buttonBack,
                "Can't click to the button `Back`",
                20
        );

        secondArticleName = waitElementAndGetAttribute(
                itemSearchSecondResult,
                "text",
                "The second item of search result is not found",
                20
        );

        waitElementAndClick(
                itemSearchSecondResult,
                "Can't click to the second article",
                20
        );

        waitElementAndClick(
                buttonSaveToList,
                "Can't click to button `Save to List`",
                20
        );

        waitElementAndClick(
                buttonAddToList,
                "Can't click to button `Add to List`",
                20
        );

        waitElementAndClick(
                By.xpath("//android.view.ViewGroup//android.widget.TextView[@text='"+listName+"']"),
                "Can't click to button `Add to List`",
                20
        );

        waitElementAndClick(
                buttonViewList,
                "Can't click to button `View List`",
                20
        );

        waitElementPresent(
                By.xpath("//*[@text='"+firstArticleName+"']"),
                "First article is not found in list",
                10
        );

        swipeElementToLeft(
                By.xpath("//*[@text='"+firstArticleName+"']"),
                "Can't swipe"
        );
        waitElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='"+secondArticleName+"']"),
                "Second article is not found in the list",
                10
        );

        titleArticleFromList = waitElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='"+secondArticleName+"']"),
                "text",
                "The title of second article in the list is not present",
                10
        );

        Assert.assertEquals("The name of the article is not the same is expected!", secondArticleName, titleArticleFromList);
    }

    @Test
    public void checkArticleNamePresent() {
        String word = "Java";
        String firstArticleName;


        waitElementAndClick(
                buttonSkip,
                "Button `Skip` not found",
                20
        );

        waitElementAndClick(
                fieldsSearch,
                "Can't click to the search panel",
                20
        );

        waitElementAndSendKeys(
                inputSearch,
                word,
                "Can't set the search word",
                20
        );

        waitElementPresent(
                panelSearchResult,
                "Panel of search result is not found",
                20
        );

        firstArticleName = waitElementAndGetAttribute(
                itemSearchFirstResult,
                "text",
                "The first item of search result is not found",
                20
        );

        waitElementAndClick(
                itemSearchFirstResult,
                "Can't click to the first article",
                20
        );

//        waitElementPresent(
//                By.xpath("//*[@text='"+firstArticleName+"']"),
//                "Can't click to the first article",
//                20
//        );

        assertElementPresent(
                By.xpath("//*[@text='"+firstArticleName+"']"),
                "Can't show the article's title"
        );

    }

    @Test
    public void testOrientation1() {
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

        driver.rotate(ScreenOrientation.LANDSCAPE);

        waitElementAndSendKeys(
                inputSearch,
                word,
                "Can't set the search word",
                20
        );

    }


    @Test
    public void testOrientation2() {
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

        Assert.assertEquals("Orientation is not default", defaultOrientation, driver.getOrientation());
    }
}
