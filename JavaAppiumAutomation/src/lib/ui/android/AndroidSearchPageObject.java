package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static String SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
    static String SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
    static String SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    static String SEARCH_CLOSE_BUTTON = "id:org.wikipedia:id/search_close_btn";
    static String SEARCH_RESULT_BY_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    static String SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    static String SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
    static String SEARCH_RESULT_TITLE_AND_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_{title_desc}'][@text='{TITLE_DESC}']";


    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }


}
