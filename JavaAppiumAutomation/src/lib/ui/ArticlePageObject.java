package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{

    protected static  String TITLE ;
    protected static String FOOTER_ELEMENT;
    protected static String OPTIONS_BUTTON;
    protected static String OPTIONS_ADD_TO_MY_LIST_BUTTON;
    protected static String ADD_TO_MY_LIST_OVERLAY;
    protected static String MY_LIST_NAME_INPUT;
    protected static String MY_LIST_OK_BUTTON;
    protected static String CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page.", 30);
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }

    }

    public void swipeToFooter(){
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,"Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyList(String nameOfFolder){
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options", 10);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list", 10);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay", 10);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, nameOfFolder, "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,"Cannot press OK button", 5);
    }

    public void addArticlesToMySaved(){
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list", 5);
    }

    public void addNotFirstArticleToMyList(){
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options", 10);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list", 10);
    }

    public void closeArticle(){
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,"Cannot close article, cannot find X link", 5);
    }

    public void assertTitleIsPresent(){
        this.assertElementPresent(TITLE);
    }

}
