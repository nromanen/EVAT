package base;

import org.openqa.selenium.WebElement;

public class Helper {
    public static boolean checkActionElement(WebElement element){
        try{
            element.isEnabled();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
