package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static String SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
    static String SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
    static String SEARCH_CANCEL_BUTTON = "id:Close";
    static String SEARCH_CLOSE_BUTTON = "id:Close";
    static String SEARCH_RESULT_BY_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
    static String SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
    static String SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    //static String SEARCH_RESULT_TITLE_AND_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_{title_desc}'][@text='{TITLE_DESC}']";

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
