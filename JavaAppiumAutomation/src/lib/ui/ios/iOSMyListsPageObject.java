package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        SHARE_ARTICLE = "id:swipe action share";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
