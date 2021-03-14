import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class FirstTest extends Methods {

    //    String fourthDot = "//android.widget.HorizontalScrollView[@content-desc=\"Шаг 1 из 4\"]/android.widget.LinearLayout/android.widget.LinearLayout[4]";
//    String getStartedButton = "android.widget.Button";
//    String searchInputField = "androidx.cardview.widget.CardView";
//    String getSearchInputField = "android.widget.EditText";
//    String arrow = "android.widget.ImageButton";
//    String java = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text='Java']";
    String javaTitle = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]";
    //String javaTitle = "pcs-edit-section-title-description";
    //String downOfPage = "pcs-footer-container-menu-heading";
    String back_xpath = "//android.widget.ImageButton[@content-desc='Перейти вверх']";
    String myList_xpath = "//android.widget.FrameLayout[@content-desc='Мои списки']";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 4 API 30");
        capabilities.setCapability("platformVersion", "11.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/evhenyivakeryn/IdeaProjects/JavaAppiumAutomation/apks/Wikipedia.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/OR.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            OR.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Ignore
    @Test()
    public void navigateToJavaArticle() {
        //1
        untilSearchField();

        //2
        waitElementAndSendKeys(By.className(OR.getProperty("getSearchInputField")),
                "Java", "Cannot find search input",
                10);

        //3
        waitElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text='Java']"),
                "Cannot find search input",
                10);
    }

    @Ignore
    @Test
    public void testComeBack() {
        //1
        untilSearchField();

        //2
        waitElementAndSendKeys(By.className(OR.getProperty("getSearchInputField")),
                "Java", "Cannot find search input",
                5);

        //3
        waitForElementAndClear(By.className(OR.getProperty("getSearchInputField")),
                "Cannot find search input",
                5);

        //4
        waitElementAndClick(By.className(OR.getProperty("arrow")),
                "Cannot find X to cancel search",
                5);

        //5
        waitForElementNotPresent(By.className(OR.getProperty("arrow")),
                "Cannot come back via arrow to cancel search",
                5);
    }

    @Ignore
    @Test
    public void testCompareArticleTitle() {
        //1
        untilSearchField();

        //2
        waitElementAndSendKeys(By.className(OR.getProperty("getSearchInputField")),
                "Java", "Cannot find search input",
                5);

        //3
        waitElementAndClick(By.xpath(OR.getProperty("java")),
                "Cannot find search input",
                20);

//        //4
//        WebElement title_article = waitForElementPresent(By.id(javaTitle),
//                "Cannot find article title",
//                20);
//
//        String articleTitle = title_article.getAttribute("text");
//        System.out.println(articleTitle);
//        Assert.assertEquals("We see unexpected title!", "язык программирования", articleTitle);

        swipeUpToFindElement(By.xpath("//*[@text='Просмотреть статью в браузере']"),
                "Cannot find the end of the article",
                100);

    }

    @Test
    public void saveFirstArticleToMyList() throws InterruptedException {
        navigateToJavaArticle();
        waitElementAndClick(By.xpath(OR.getProperty("moreOptions_id")),
                "Cannot find button to open article options",
                10);
        waitElementAndClick(By.xpath(back_xpath),
                "Cannot click on back button",
                10);
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        waitElementAndClick(By.xpath(myList_xpath),
                "Cannot click on my list button",
                10);
        waitElementAndClick(By.xpath(OR.getProperty("saved_article_xpath")),
                "Cannot click on last saved article",
                10);
        waitElementAndClick(By.xpath(OR.getProperty("saved_article_prepare_for_swipe_xpath")),
                "Cannot click on last saved article",
                10);

//        Thread.sleep(3000);
//        swipeElementToLeft(By.id(OR.getProperty("saved_article_prepare_for_swipe_xpath")),
//                "Cannot find saved article");
//        waitForElementPresent(By.xpath(OR.getProperty("saved_article_prepare_for_swipe_xpath")),
//                "Cannot delete saved article",
//                10);

    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}
