package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]";
    private static final String SEARCH_INPUT = "//*[contains(@text,'Search…')]";
    private static final String SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn";
    private static final String SEARCH_CLOSE_BUTTON = "org.wikipedia:id/search_close_btn";
    private static final String SEARCH_RESULT_BY_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    private static final String SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    private static final String SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";
    private static final String SEARCH_RESULT_TITLE_AND_DESC_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_{title_desc}'][@text='{TITLE_DESC}']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getDescSearchElement(String substring){
        return SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_DESC}",substring).replace("{title_desc}","description");
    }

    private static String getTitleSearchElement(String substring){
        return SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_DESC}",substring).replace("{title_desc}","title");
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void clickOnCloseButton(){
        this.waitForElementAndClick(By.id(SEARCH_CLOSE_BUTTON),"No 'x' sign", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is present", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,
                "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String subString){
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementPresent(By.xpath(searchResultXpath), "Cannot find search result with substring " + subString);
    }

    public void clickByArticleWithSubstring(String subString){
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementAndClick(By.xpath(searchResultXpath), "Cannot find and click search result with substring " + subString, 15);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request", 15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public int articlesAmount(){
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),"Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        System.out.println("We are searching for title: " + title + " and desc: " + description);
        this.assertElementPresent(By.xpath(getTitleSearchElement(title)),
                "Here is no article with title '" + title + "' among search results.");
        this.assertElementPresent(By.xpath(getDescSearchElement(description)),
                "Here is no article with description '" + description + "' among search results.");
    }
}
