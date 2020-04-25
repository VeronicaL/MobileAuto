package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String TITLE = "id:org.wikipedia:id/view_page_title_text";
    private static final String FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
    private static final String OPTIONS_BUTTON= "xpath://android.widget.ImageView[@content-desc='More options']";
    private static final String OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
    private static final String ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
    private static final String MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
    private static final String MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
    private static final String CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page.", 30);
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 35);
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
