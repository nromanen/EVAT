package pages.base;

import org.openqa.selenium.WebElement;

public class Helper {
    public static boolean isElementPresent(WebElement element){
        try{
            element.isEnabled();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isElementPresentWait(WebElement element){
        try{
            BasePage.conditionFactory.await().until(element::isEnabled);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
