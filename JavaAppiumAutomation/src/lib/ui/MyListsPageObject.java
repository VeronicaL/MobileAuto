package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String FOLDER_BY_NAME_TPL;
    protected static String ARTICLE_BY_TITLE_TPL;

    private static String getFolderXpathByName(String nameOfFolder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder){
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(folderNameXpath,
                "Cannot find folder by name " + nameOfFolder, 5);
    }

    private void waitForArticleToAppearByTitle(String articleTitle){
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        System.out.println("articleXpath" + articleXpath);
        this.waitForElementPresent(articleXpath,"Cannot find saved article by title " + articleTitle, 15);
    }
    private void waitForArticleToDisappearByTitle(String articleTitle){
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(articleXpath,"Saved article still present with title " + articleTitle, 15);
    }

    public void swipeByArticleToDelete(String articleTitle){
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        if(Platform.getInstance().isAndroid()){
            this.swipeElementToLeft(articleXpath, "Cannot find saved article");
        }
        if(Platform.getInstance().isIOS()){
            this.swipeElementToLeft(articleXpath + "/..", "Cannot find saved article");
            this.clickElementToTheRightUpperCorner(articleXpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void assertArticleIsPresent(String articleTitle){
        this.assertElementPresent(getSavedArticleXpathByTitle(articleTitle), "Needed article title " + articleTitle + " is absent");
    }
}