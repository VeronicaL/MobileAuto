package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public boolean isAllArticleTitlesContainsText(String text){
        List<WebElement> el = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        int resSize = el.size();
        for(int i = 0; i < resSize; i++){
            if (!el.get(i).getAttribute("text").toLowerCase().contains(text.toLowerCase())){
                return false;
            }
        }
        return true;
    }

    public WebElement waitForElementPresent(String locator, String error_message){
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean isTextPresentInField(String text, String locator){
        WebElement searchField = waitForElementPresent(locator,"Search field is absent");
        return text.equals(searchField.getAttribute("text"));
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width /2; //width - of screen
        int startY = (int)(size.height * 0.8);
        int endY = (int)(size.height * 0.2);

        action
                .press(PointOption.point(x, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, endY))
                .release()
                .perform();
        //perform - sends all chain of actions to perform
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes){
        By by = this.getLocatorByString(locator);
        int alreadySwiped = 0;
        while(driver.findElements(by).size() == 0){
            if(alreadySwiped > maxSwipes){
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage);
                return;
            }
            swipeUpQuick();
            alreadySwiped++;
            System.out.println("Swiped: " + alreadySwiped + " times");
        }
    }

    public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)){
            if (alreadySwiped > maxSwipes){
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            alreadySwiped++;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator){
        int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element by locator" ,1).getLocation().getY();
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public void clickElementToTheRightUpperCorner(String locator, String errorMessage){
        WebElement element = this.waitForElementPresent(locator + "/..", errorMessage);
        int rightX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY)/2;
        int width = element.getSize().getWidth();

        int pointToClickX = (rightX +width) - 3;
        int pointToClickY = middleY;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();

    }


    public void swipeElementToLeft(String locator, String error_message){
        WebElement element = waitForElementPresent(locator, error_message, 10);
        int leftX = element.getLocation().getX();//the most left x point of element
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY)/2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(rightX, middleY));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
        if(Platform.getInstance().isAndroid()){
            action.moveTo(PointOption.point(leftX, middleY));
        } else {
            int offsetX = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offsetX, 0));
        }

        action.release();
        action.perform();
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements == 0) {
            String defaultMessage = "An element '" + locator + "' supposed to be present\n";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertElementPresent(String locator){
        By by = this.getLocatorByString(locator);
        try {
            WebElement element = driver.findElement(by);
            assertTrue("Required title is not displayed", element.isDisplayed());
        } catch (NoSuchElementException ex){
            assertTrue("Element title is not found for opened article", false);
        }
    }

    private By getLocatorByString(String locatorWithType) {
        String [] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")){
            return By.xpath(locator);
        } else if (byType.equals("id")){
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
        }
    }

}
