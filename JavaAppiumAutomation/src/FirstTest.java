import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {

    /* --some previous version
    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchField() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        assertTrue("'Search…' string is not found", mainPageObject.isTextPresentInField("Search…", By.id("org.wikipedia:id/search_src_text")));
    }

    @Test
    public void testGetResults() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium","Search input is absent",10);
        assertTrue("No articles were found", mainPageObject.articlesAmount() > 0);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"No 'x' sign", 5);
        assertTrue("Articles were found", mainPageObject.articlesAmount() == 0);
    }

    @Test
    public void testCheckSearchWordInResults(){
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input",10);
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "JAVA","Search input is absent",10);
        mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/page_list_item_container"),"No results via search", 10);
        assertTrue("No articles were found", mainPageObject.articlesAmount() > 0);
        assertTrue("Some of articles doesn't contain JAVA in any register", mainPageObject.isAllArticleTitlesContainsText("JAVA"));
    }*/
}
