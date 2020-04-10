import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class SwipeTest {

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


    protected void swipeUp(int timeOfSwipe){

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width /2; //width - of screen
        int startY = (int)(size.height * 0.8);
        int endY = (int)(size.height * 0.2);

        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
        //perform - sends all chain of actions to perform
    }

    @Test
    public void testSwipeArticle(){
        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium","Search input is absent",10);

        waitForElemenAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Appium']"),
                "Cannot find needed title", 15);

        waitForElementPresent( By.id("org.wikipedia:id/view_page_title_text"), "Cannot find article title", 15);

        //1
//        swipeUp(2000);
//        swipeUp(2000);
//        swipeUp(2000);
//        swipeUp(2000);

        //2
        swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"),"Test error message", 35);
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        while(driver.findElements(by).size() == 0){
            if(alreadySwiped > maxSwipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage);
                return;
            }
            swipeUpQuick();
            alreadySwiped++;
            System.out.println("Swiped: " + alreadySwiped + " times");
        }
    }

    @Test
    public void saveFirstArticleToMyList(){
        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java","Search input is absent",10);

        waitForElemenAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Wiki input", 5);

        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Cannot find article title", 15);

        //android.widget.ImageView[@content-desc="More options"]
        //android.widget.ImageView[@content_desc='More options']
        waitForElemenAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options", 5);

        waitForElemenAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list", 5);

        waitForElemenAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay", 5);

        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder", 5);

        String nameOfFolder = "Learning programming";

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), nameOfFolder, "Cannot put text into articles folder input", 5);
        waitForElemenAndClick(By.xpath("//*[@text='OK']"),"Cannot press OK button", 5);

        waitForElemenAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),"Cannot close article, cannot find X link", 5);
        waitForElemenAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),"Cannot find navigation button to My list", 5);
        waitForElemenAndClick(By.xpath("//*[@text='" + nameOfFolder + "']"),"Cannot find created folder", 5);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Cannot find saved article");
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),"Cannot delete saved article", 5);

    }


    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        String searchLine = "Linkin park discography";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine,"Search input is absent",10);
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(By.xpath(searchResultLocator), "Cannot find anuthing by the request " + searchLine, 15);
        int amountOfSearchResults = getAmountOfElements(By.xpath(searchResultLocator));
        Assert.assertTrue("We founf too few results!", amountOfSearchResults > 0);

    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        String searchLine = "seyerurtu";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine,"Search input is absent",10);
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String emptyResultLabel = "//*[@text='No results found']";
        waitForElementPresent(By.xpath(emptyResultLabel), "Cannot find empty result label by the request" + searchLine, 15);
        assertElementNotPresent(By.xpath(searchResultLocator), "We've found some results by request " + searchLine);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults(){
        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        String searchLine = "Java";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine,"Search input is absent",10);
        waitForElemenAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchLine, 5);

        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article", 15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article", 15);

        Assert.assertEquals("Article title have been changed after screen rotation", titleBeforeRotation, titleAfterRotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article", 15);

        Assert.assertEquals("Article title have been changed after screen rotation", titleBeforeRotation, titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {

        waitForElemenAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java","Search input is absent",10);

        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Wiki input", 5);

        driver.runAppInBackground(2);

        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from background", 5);

    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    private void assertElementNotPresent(By by, String errorMessage) {

        int amountOfElements = getAmountOfElements(by);

        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }


    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 10);
        int leftX = element.getLocation().getX();//the most left x point of element
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();

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

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
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
