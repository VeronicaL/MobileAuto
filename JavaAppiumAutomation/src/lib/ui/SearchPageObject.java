package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String SEARCH_INIT_ELEMENT;
    protected static String SEARCH_INPUT;
    protected static String SEARCH_CANCEL_BUTTON;
    protected static String SEARCH_CLOSE_BUTTON;
    protected static String SEARCH_RESULT_BY_TPL;
    protected static String SEARCH_RESULT_ELEMENT;
    protected static String SEARCH_EMPTY_RESULT_ELEMENT;
    protected static String SEARCH_RESULT_TITLE_AND_DESC_TPL;

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
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void clickOnCloseButton(){
        this.waitForElementAndClick(SEARCH_CLOSE_BUTTON,"No 'x' sign", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is present", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,
                "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String subString){
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementPresent(searchResultXpath, "Cannot find search result with substring " + subString);
    }

    public void clickByArticleWithSubstring(String subString){
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementAndClick(searchResultXpath, "Cannot find and click search result with substring " + subString, 15);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public int articlesAmount(){
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        System.out.println("We are searching for title: " + title + " and desc: " + description);
        this.assertElementPresent(getTitleSearchElement(title),
                "Here is no article with title '" + title + "' among search results.");
        this.assertElementPresent(getDescSearchElement(description),
                "Here is no article with description '" + description + "' among search results.");
    }
}
