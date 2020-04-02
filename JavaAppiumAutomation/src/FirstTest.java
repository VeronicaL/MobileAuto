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
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/veronica/!Тренинги/mobileAuto/git/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchFieldTest() {
        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        Assert.assertTrue("'Search…' string is not found", isTextPresentInField("Search…", By.id("org.wikipedia:id/search_src_text")));
    }

    @Test
    public void getResultsTest() {
        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium","Search input is absent",10);
        Assert.assertTrue("No articles were found", articlesAmount() > 0);
        waitForElemenAndClick(By.id("org.wikipedia:id/search_close_btn"),"No 'x' sign", 5);
        Assert.assertTrue("Articles were found", articlesAmount() == 0);
    }

    @Test
    public void checkSearchWordInResults(){
        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "JAVA","Search input is absent",10);
        waitForElementPresent(By.id("org.wikipedia:id/page_list_item_container"),"No results via search", 10);
        Assert.assertTrue("No articles were found", articlesAmount() > 0);
        Assert.assertTrue("Some of articles doesn't contain JAVA in any register", isAllArticleTitlesContainsText("JAVA"));
    }

    private boolean isAllArticleTitlesContainsText(String text){
        List<WebElement> el = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        int resSize = el.size();
        for(int i = 0; i < resSize; i++){
            if (!el.get(i).getAttribute("text").toLowerCase().contains(text.toLowerCase())){
                return false;
            }
        }
        return true;
    }

    private int articlesAmount(){
        return driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).size();
    }

    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElemenAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private boolean isTextPresentInField(String text, By by){
        WebElement searchField = waitForElementPresent(by,"Search field is absent");
        return text.equals(searchField.getAttribute("text"));
    }

}
