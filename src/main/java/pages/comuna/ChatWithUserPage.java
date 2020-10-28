package pages.comuna;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatWithUserPage {
    private WebDriver driver;
    public ChatWithUserPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".MuiInputBase-input")
    public WebElement fieldForMessage;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(2)")
    public WebElement sendButton;

    @FindBy(css = ".msg_cotainer_send")
    public WebElement firstSentMessage;

    @FindBy(css = ".user_info > p:nth-child(2)")
    public WebElement numberOfMessages;

    public ChatWithUserPage enterMessage(String text) {
        fieldForMessage.sendKeys(text);
        return this;
    }
    public ChatWithUserPage sendMessage(){
        sendButton.click();
        return this;
    }
    public String getTextFromMessageField(){
        return fieldForMessage.getAttribute("value");
    }
    public String getTextSentMessage(){
        return firstSentMessage.getText();
    }
    public String getNumberOfMessages(){
        return numberOfMessages.getText();
    }
}

