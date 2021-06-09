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

public class FirstTest {

    private AppiumDriver driver;

    private By buttonSkip = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private By fieldsSearch = By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//android.widget.TextView");
    private By inputSearch = By.id("org.wikipedia:id/search_src_text");

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

    public void assertElementHasText(By by, String text, String errorMessage) {
        WebElement element = driver.findElement(by);
        Assert.assertTrue(errorMessage, element.getText().contains(text));
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



}
