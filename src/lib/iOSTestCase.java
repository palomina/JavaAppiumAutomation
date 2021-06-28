package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestCase extends TestCase {

    protected AppiumDriver driver;

    private final String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    public void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE (2nd generation)");
        capabilities.setCapability("platformVersion", "13.7");
        capabilities.setCapability("app", "/Users/morozovaolha/Downloads/Wikipedia.app");

        driver = new IOSDriver(new URL(AppiumUrl), capabilities);

        this.rotateToPORTRAIT();
    }

    @Override
    public void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    protected void rotateToLANDSCAPE(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateToPORTRAIT(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
}
