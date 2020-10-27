package pages.communa;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChatWithUserPage {
    private WebDriver driver;
    public ChatWithUserPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(css = ".MuiInputBase-input")
    WebElement fieldForMessage;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(2)")
    WebElement sendButton;

    @FindBy(css = ".msg_cotainer_send")
    WebElement sentMessage;

    @FindBy(css = ".user_info > p:nth-child(2)")
    WebElement numberOfMessages;

    public ChatWithUserPage enterMessage(String text) {
        fieldForMessage.sendKeys(text);
        return this;
    }
    public ChatWithUserPage sendMessage(){
        sendButton.click();
        return this;
    }
    public String getTextFromMessageField(){
        return fieldForMessage.getText();
    }
    public String getTextSentMessage(){
        return sentMessage.getText();
    }
    public String getNumberOfMessages(){
        return numberOfMessages.getText();
    }
}

