package pages.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoPage extends BasePage {

    private static final String USER_NAME="User Name:";
    private static final String AGE="Age:";
    private static final String GENDER="Gender:";
    private static final String EMAIL="Email:";
    private static final String INTERESTS="Interests:";

    HashMap<WebElement,WebElement> tableUserInfo;

    public UserInfoPage(WebDriver driver) {
        super(driver);
        waitForElementToAppear(By.cssSelector(".info > .col-md-6 > div > div"));
        List<WebElement> elementsOnPage =driver.findElements(By.cssSelector(".info > .col-md-6 > div > div"));
        tableUserInfo=new HashMap<>();
        for(int i=0;i<elementsOnPage.size();i+=2){
            tableUserInfo.put(elementsOnPage.get(i),elementsOnPage.get(i+1));
        }
    }

    private String getTextOfWebElementByName(String name){
        WebElement element=null;
        for(Map.Entry<WebElement, WebElement> row: tableUserInfo.entrySet()){
            if(row.getKey().getText().equals(name)){
                element=row.getValue();
                break;
            }
        }
        if(element!=null) return element.getText();
        else throw new NoSuchElementException("Element "+name+" didn't found");
    }

    public String getValueUserName(){
        return getTextOfWebElementByName(USER_NAME);
    }

    public String getValueAge(){
        return getTextOfWebElementByName(AGE);
    }

    public String getValueGender(){
        return getTextOfWebElementByName(GENDER);
    }

    public String getValueEmail(){
        return getTextOfWebElementByName(EMAIL);
    }

    public String getValueInterests(){
        return getTextOfWebElementByName(INTERESTS);
    }

}
