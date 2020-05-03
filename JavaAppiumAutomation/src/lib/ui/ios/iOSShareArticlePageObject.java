package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ShareArticlePageObject;

public class iOSShareArticlePageObject extends ShareArticlePageObject {

    static {
        SHARE_ARTICLE = "id:swipe action share";
        SHARE_ARTICLE_BY_STR = "xpath://XCUIElementTypeOther[contains(@name,'on @Wikipedia')]";
    }

    public iOSShareArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


}
