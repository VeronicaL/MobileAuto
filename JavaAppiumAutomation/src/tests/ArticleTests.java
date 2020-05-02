package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();
        assertEquals("We see unexpected title","Java (programming language)",articleTitle);
    }

    @Test
    public void testSwipeArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testCheckManipulationWithTwoArticles(){
        String searchString = "Java";
        String firstArticleLongTitle = "Object-oriented programming language";
        String secondArticleLongTitle = "Programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchString);
        searchPageObject.clickByArticleWithSubstring(firstArticleLongTitle);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        String firstArticleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        articlePageObject.addArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchString);
        searchPageObject.clickByArticleWithSubstring(secondArticleLongTitle);

        String secondArticleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addNotFirstArticleToMyList();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.openFolderByName(nameOfFolder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        myListsPageObject.openFolderByName(nameOfFolder);
        myListsPageObject.swipeByArticleToDelete(firstArticleTitle);

        myListsPageObject.assertArticleIsPresent(secondArticleTitle);

        searchPageObject.clickByArticleWithSubstring(secondArticleTitle);

        String articleTitle = articlePageObject.getArticleTitle();
        assertTrue("Title of second saved article and opened are differ: " +
                secondArticleTitle + " and " + articleTitle, articleTitle.equals(secondArticleTitle));
    }


    @Test
    public void testCheckArticleTitle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Appium";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring(searchLine);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertTitleIsPresent();
    }
}
