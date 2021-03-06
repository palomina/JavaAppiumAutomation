package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

abstract class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
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

    public String waitElementAndGetText(By by, String errorMessage, int timeout) {
        return new WebDriverWait(driver, timeout)
                .withMessage(errorMessage)
                .until(ExpectedConditions.presenceOfElementLocated(by))
                .getText();
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

    public void swipeElementToLeft(By by, String errorMessage) {
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

    public By getLocator(String locator) {
        String[] parts = locator.split("##");
        switch (parts[0]) {
            case "id":
                return By.id(parts[1]);
            case "xpath":
                return By.xpath(parts[1]);
            default:
                throw new IllegalArgumentException("Unknown type of locator: " + parts[0]);
        }
    }
}
