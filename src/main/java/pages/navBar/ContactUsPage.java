package pages.navBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {
    private WebDriver driver;
    public ContactUsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".form-control")
    public WebElement problemType;

//  drop-down list

    @FindBy(css = ".MuiInputBase-input")
    public WebElement descriptionField;

    @FindBy(css = ".MuiFormHelperText-root")
    public WebElement emptyDescriptionError;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(4)")
    public WebElement clearButton;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(3)")
    public WebElement submitButton;

    public ContactUsPage clickOnProblemType(){
        problemType.click();
        return this;
    }
//    public ContactUsPage selectProblemType(){
//
//    }
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

