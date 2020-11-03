package pages.comuna;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.EventElement;

import java.util.List;

public class ComunaPage {
    private WebDriver driver;
    public ComunaPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "div.w-100")
    public static
    List<WebElement> numberOfChats;

    @FindBy(css = "div.w-100:nth-child(1) > a:nth-child(1)")
    public WebElement firstChatWithUser;

    public ChatWithUserPage goToFirstChat(){
        EventElement element = new EventElement(driver, firstChatWithUser);
        element.clickAndWait(ChatWithUserPage.chatTitle);
        return new ChatWithUserPage(driver);
    }
    public int getNumberOfUsersChats(){
        return numberOfChats.size();
    }
}