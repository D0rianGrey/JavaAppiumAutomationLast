import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

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
        WebElement element1 = driver.findElementByXPath("//android.widget.HorizontalScrollView[@content-desc=\"Page 1 of 4\"]/android.widget.LinearLayout/android.widget.LinearLayout[4]");
        element1.click();
        WebElement element2 = driver.findElementByClassName("android.widget.Button");
        element2.click();
        WebElement element3 = driver.findElementByClassName("androidx.cardview.widget.CardView");
        Thread.sleep(3000);
        element3.click();
        Thread.sleep(10000);
        WebElement element4 = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        element4.sendKeys("google");
        Thread.sleep(3000);
        WebElement element5 = driver.findElementByXPath("//*[contains(@text,'American technology company')]");
        element5.click();
        //element3.sendKeys("google");
//        TouchAction touchAction = new TouchAction(driver);
//        touchAction.tap(2000,0).perform();


        //System.out.println("First test run");
    }
}
