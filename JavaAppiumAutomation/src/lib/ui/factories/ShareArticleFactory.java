package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ShareArticlePageObject;
import lib.ui.ios.iOSShareArticlePageObject;

public class ShareArticleFactory {

    public static ShareArticlePageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return null;//no such page
        } else {
            return new iOSShareArticlePageObject(driver);
        }
    }
}
