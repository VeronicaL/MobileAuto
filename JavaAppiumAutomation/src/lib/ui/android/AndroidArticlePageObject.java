package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static String TITLE = "id:org.wikipedia:id/view_page_title_text";
    static String FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
    static String OPTIONS_BUTTON= "xpath://android.widget.ImageView[@content-desc='More options']";
    static String OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
    static String ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
    static String MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
    static String MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
    static String CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
