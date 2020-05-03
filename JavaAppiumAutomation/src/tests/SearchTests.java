package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "seyerurtu";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin park discography";
        searchPageObject.typeSearchLine(searchLine);

        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results!", amountOfSearchResults > 0);
    }

     @Test
    public void testGetResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Appium";
        searchPageObject.typeSearchLine(searchLine);
        assertTrue("No articles were found", searchPageObject.getAmountOfFoundArticles() > 0);
        searchPageObject.clickOnCloseButton();
        assertTrue("Articles were found", searchPageObject.articlesAmount() == 0);
    }

    //todo
    @Test
    public void testCheckTitleAndDecription(){
        String searchLine = "Java";
        String firstTitle = "Java";
        String firstDesc = "Island of Indonesia";

        String secondTitle = "JavaScript";
        String secondDesc = "Programming language";

        String thirdTitle = "Java (programming language)";
        String thirdDesc = "Object-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);

        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results!", amountOfSearchResults >= 3);

        searchPageObject.waitForElementByTitleAndDescription(firstTitle, firstDesc);
        searchPageObject.waitForElementByTitleAndDescription(secondTitle, secondDesc);
        searchPageObject.waitForElementByTitleAndDescription(thirdTitle, thirdDesc);
    }

}
