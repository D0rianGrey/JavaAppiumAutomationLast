import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
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

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 3 API 29");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/evhenyivakeryn/IdeaProjects/JavaAppiumAutomation/apks/Wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @After
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void firstTest() throws InterruptedException {
        WebElement fourthDot = waitForElementPresentByXpath("//android.widget.HorizontalScrollView//android.widget.LinearLayout[4]", "Cannot find fourthDot");
        fourthDot.click();

        WebElement getStartedButton = waitForElementPresentByResourceId("android.widget.Button", "Cannot find getStartedButton");
        getStartedButton.click();

        WebElement searchInputField = waitForElementPresentByResourceId("androidx.cardview.widget.CardView", "Cannot find searchInputField");
        searchInputField.click();

        WebElement openedSearchInputField = waitForElementPresentByXpath("//*[contains(@text,'Search Wikipedia')]", "Cannot find search input");
        openedSearchInputField.sendKeys("google");

        //WebElement firstFoundItem = waitForElementPresentByXpath("//org.wikipedia:id/search_results_list//android.view.ViewGroup[0]", "Cannot find search input", 10);

        WebElement element5 = waitForElementPresentByXpath("//*[contains(@text,'American technology company')]", "Cannot find search input");
        element5.click();
        //firstFoundItem.click();
    }

    private WebElement waitForElementPresentByXpath(String xpath, String errorMessage, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresentByXpath(String xpath, String errorMessage) {
        return waitForElementPresentByXpath(xpath, errorMessage, 5);
    }

    private WebElement waitForElementPresentByResourceId(String resourceId, String errorMessage, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        By by = By.className(resourceId);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresentByResourceId(String resourceId, String errorMessage) {
        return waitForElementPresentByResourceId(resourceId, errorMessage, 5);
    }

}
