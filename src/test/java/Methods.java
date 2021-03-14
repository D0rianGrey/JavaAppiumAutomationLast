import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;

public class Methods {

    protected AppiumDriver driver;
    protected FileInputStream fileInputStream;
    protected Properties OR = new Properties();

    protected WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

//    protected WebElement waitForElementPresentByResourceId(String resourceId, String errorMessage, long timeoutInSecond) {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
//        wait.withMessage(errorMessage + "\n");
//        By by = By.className(resourceId);
//        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
//    }
//
//    protected WebElement waitForElementPresentByResourceId(String resourceId, String errorMessage) {
//        return waitForElementPresentByResourceId(resourceId, errorMessage, 5);
//    }

    protected WebElement waitElementAndClick(By by, String error_message, long timeOutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutSeconds);
        element.click();
        return element;
    }

    protected WebElement waitElementAndSendKeys(By by, String value, String error_message, long timeOutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutSeconds);
        element.sendKeys(value);
        return element;
    }

//    protected WebElement waitElemenByResourceIdAndClick(String resourceId, String error_message, long timeOutSeconds) {
//        WebElement element = waitForElementPresentByResourceId(resourceId, error_message, timeOutSeconds);
//        element.click();
//        return element;
//    }
//
//    protected WebElement waitElementByResourceIdAndSendKeys(String resourceId, String value, String error_message, long timeOutSeconds) {
//        WebElement element = waitForElementPresentByResourceId(resourceId, error_message, timeOutSeconds);
//        element.sendKeys(value);
//        return element;
//    }
//
//    protected WebElement waitForElementPresentById(String id, String errorMessage, long timeoutInSecond) {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
//        wait.withMessage(errorMessage + "\n");
//        By by = By.id(id);
//        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
//    }
//
//    protected WebElement waitElementByIdAndClick(String id, String error_message, long timeOutSeconds) {
//        WebElement element = waitForElementPresentById(id, error_message, timeOutSeconds);
//        element.click();
//        return element;
//    }

    protected boolean waitForElementNotPresent(By by, String error_message, long timeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected WebElement waitForElementAndClear(By by, String error_message, long timeOutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swipping up \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    protected void untilSearchField() {
        waitElementAndClick(By.xpath(OR.getProperty("fourthDot")),
                "Cannot find fourthDot",
                10);

        waitElementAndClick(By.className(OR.getProperty("getStartedButton")),
                "Cannot find getStartedButton",
                10);

        waitElementAndClick(By.className(OR.getProperty("searchInputField")),
                "Cannot find searchInputField",
                10);
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}
