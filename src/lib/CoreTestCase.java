package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;

    private final String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    public void setUp() throws Exception {
        super.setUp();

         this.setDriverByPlatformEnv();

        this.rotateToPORTRAIT();
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    private void setDriverByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/morozovaolha/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia_50359_apps.evozi.com.apk");
            driver = new AndroidDriver(new URL(AppiumUrl), capabilities);

        } else if(platform.equals(PLATFORM_IOS)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE (2nd generation)");
            capabilities.setCapability("platformVersion", "13.7");
            capabilities.setCapability("app", "/Users/morozovaolha/Downloads/Wikipedia.app");
            driver = new IOSDriver(new URL(AppiumUrl), capabilities);

        } else
        {
            throw new Exception("Cannot get run platrom from env variable. Platform value " + platform);
        }

    }

    protected void rotateToLANDSCAPE(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateToPORTRAIT(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
}
