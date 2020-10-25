package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {
    private WebDriver driver;
    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(css = ".form-control")
    Select problemType;

    @FindBy(css = ".MuiInputBase-input")
    WebElement descriptionField;

    @FindBy(css = ".MuiFormHelperText-root")
    WebElement emptyDescriptionError;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(4)")
    WebElement clearButton;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(3)")
    WebElement submitButton;

    public ContactUsPage selectProblemType(int index){
        problemType.selectByIndex(index);
        return this;
    }
    public ContactUsPage enterDescription(String text){
        descriptionField.sendKeys(text);
        return this;
    }
    public ContactUsPage clickSubmitButton(){
        submitButton.click();
        return this;
    }
    public ContactUsPage clickClearButton(){
        clearButton.click();
        return this;
    }
    public String getEmptyDescriptionError(){
        return emptyDescriptionError.getText();
    }
    public String getTextFromDescriptionField(){
        return descriptionField.getText();
    }
}

