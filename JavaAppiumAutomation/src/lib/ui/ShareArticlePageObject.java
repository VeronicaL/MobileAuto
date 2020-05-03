package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class ShareArticlePageObject extends MainPageObject {

    protected static String SHARE_ARTICLE;
    protected static String SHARE_ARTICLE_BY_STR;

    public ShareArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getSharedTitleName(){

        return this.waitForElementAndGetAttribute(SHARE_ARTICLE_BY_STR, "name", "Unable to get name of the element", 10);
    }

}
