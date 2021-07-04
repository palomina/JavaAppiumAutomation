package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {


    protected AppiumDriver driver;
    protected Platform platform;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateToPORTRAIT();
    }

    @Override
    public void tearDown() throws Exception {
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
