package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String TITLE = "org.wikipedia:id/view_page_title_text";
    private static final String FOOTER_ELEMENT = "//*[@text='View page in browser']";
    private static final String OPTIONS_BUTTON= "//android.widget.ImageView[@content-desc='More options']";
    private static final String OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']";
    private static final String ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button";
    private static final String MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";
    private static final String MY_LIST_OK_BUTTON = "//*[@text='OK']";
    private static final String CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page.", 30);
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 35);
    }

    public void addArticleToMyList(String nameOfFolder){
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options", 10);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list", 10);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay", 10);
        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of articles folder", 5);
        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT), nameOfFolder, "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),"Cannot press OK button", 5);
    }

    public void addNotFirstArticleToMyList(){
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options", 10);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list", 10);
    }

    public void closeArticle(){
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),"Cannot close article, cannot find X link", 5);
    }

    public void assertTitleIsPresent(){
        this.assertElementPresent(By.id(TITLE));
    }

}
