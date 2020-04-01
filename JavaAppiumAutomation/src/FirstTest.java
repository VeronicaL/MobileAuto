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

    private WebElement waitForElementByXpathAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
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
