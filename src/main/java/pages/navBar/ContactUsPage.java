package pages.navBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utility.EventElement;

public class ContactUsPage {
    private WebDriver driver;
    public ContactUsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".form-control")
    public WebElement problemTypeDropDownList;

    @FindBy(css = ".MuiInputBase-input")
    public WebElement descriptionField;

    @FindBy(css = ".MuiFormHelperText-root")
    public WebElement emptyDescriptionError;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(4)")
    public WebElement clearButton;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(3)")
    public WebElement submitButton;

    @FindBy(css = "#client-snackbar")
    public WebElement confirmedMessage;

    public ContactUsPage clickOnProblemTypeDropDownList(){
        problemTypeDropDownList.click();
        return this;
    }
    public ContactUsPage selectProblemType(String problem){
        String problemXpath = String.format("/html/body/div[1]/div[3]/div/div/form/div/select/option[contains(text(), '%s')]", problem);
        driver.findElement(By.xpath(problemXpath)).click();
        return this;
    }
    public ContactUsPage enterDescription(String text){
        descriptionField.sendKeys(text);
        return this;
    }
    public ContactUsPage clickSubmitButton(){
        EventElement element = new EventElement(driver, submitButton);
        element.clickAndWait(confirmedMessage);
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
    public String getChosenProblemTypeValue(){
        return problemTypeDropDownList.getAttribute("value");
    }
    public String getConfirmedMessageText(){
        return confirmedMessage.getText();
    }
}

