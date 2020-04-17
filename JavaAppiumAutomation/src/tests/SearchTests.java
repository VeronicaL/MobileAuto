package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchLine = "seyerurtu";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }


    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin park discography";
        searchPageObject.typeSearchLine(searchLine);

        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results!", amountOfSearchResults > 0);
    }

    /*
     @Test
    public void testGetResults() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium","Search input is absent",10);
        assertTrue("No articles were found", mainPageObject.articlesAmount() > 0);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"No 'x' sign", 5);
        assertTrue("Articles were found", mainPageObject.articlesAmount() == 0);
    }
     */
}
