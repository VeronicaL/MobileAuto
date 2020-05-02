package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        //XCUIElementTypeStaticText[@name="Java (programming language)"]

        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
