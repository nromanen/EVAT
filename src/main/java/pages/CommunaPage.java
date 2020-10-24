package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CommunaPage {
    private WebDriver driver;
    public CommunaPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(css = ".row")
    List<WebElement> numberOfChats;

    @FindBy(css = "div.w-100:nth-child(1) > a:nth-child(1)")
    WebElement chatWithUser;

    public ChatWithUserPage goToChat(){
        chatWithUser.click();
        return new ChatWithUserPage(driver);
    }
    public int getNumberOfUsersChats(){
        return numberOfChats.size();
    }
}