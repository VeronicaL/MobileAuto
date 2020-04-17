package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticle(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String articleTitle = articlePageObject.getArticleTitle();
        assertEquals("We see unexpected title","Java (programming language)'",articleTitle);
    }

    @Test
    public void testSwipeArticle(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    /*
    @Test
    public void checkManipulationWithTwoArticlesTest(){
        String searchString = "Java";
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchString,"Search input is absent",10);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' article", 5);

        By articleTitleBy = By.id("org.wikipedia:id/view_page_title_text");
        mainPageObject.waitForElementPresent(articleTitleBy, "Cannot find article title", 15);
        String articleFirstTitle = driver.findElement(articleTitleBy).getText();

        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options", 5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list", 5);

        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay", 5);

        mainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder", 5);

        String nameOfFolder = "Learning programming";

        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), nameOfFolder, "Cannot put text into articles folder input", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"),"Cannot press OK button", 5);

        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),"Cannot close article, cannot find X link", 5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchString,"Search input is absent",10);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "Cannot find 'Object-oriented programming language' article", 5);

        mainPageObject.waitForElementPresent(articleTitleBy, "Cannot find article title", 15);
        String articleSecondTitle = driver.findElement(articleTitleBy).getText();

        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options", 5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + nameOfFolder + "']"), "No folder " + nameOfFolder,5);

        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),"Cannot close article, cannot find X link", 5);

        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),"Cannot find navigation button to My list", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + nameOfFolder + "']"),"Cannot find created folder", 5);
        mainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Cannot find saved article");
        mainPageObject.waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),"Cannot delete saved article", 5);

        mainPageObject.assertElementPresent(By.xpath("//*[@text='" + articleSecondTitle + "']"), "Second added article is absent");

        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + articleSecondTitle + "']"), "Second added article is absent", 5);

        String articleTitle = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article", 15);
        assertTrue("Title of second saved article and opened are differ: "+
                articleSecondTitle +" and " + articleTitle, articleTitle.equals(articleSecondTitle));
    }

    @Test
    public void testCheckArticleTitle(){
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium","Search input is absent",10);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Appium']"),
                "Cannot find needed title", 15);

        assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
    }

    private void assertElementPresent(By by){
        try {
            WebElement element = driver.findElement(by);
            assertTrue("Required title is not displayed", element.isDisplayed());
        } catch (NoSuchElementException ex){
            assertTrue("Element title is not found for opened article", false);
        }
    }*/

}
