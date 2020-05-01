package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlepageObject extends ArticlePageObject {

    static String TITLE = "id:Java (programming language)";
    static String FOOTER_ELEMENT = "id:View article in browser";
    static String OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
    static String CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    public iOSArticlepageObject(AppiumDriver driver) {
        super(driver);
    }
}
