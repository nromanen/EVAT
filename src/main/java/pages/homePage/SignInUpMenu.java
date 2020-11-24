package pages.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.EventElement;


public class SignInUpMenu {

    WebDriver driver;

    @FindBy(css = ".auth p")
    public WebElement errorMessage;

    @FindBy(css = ".text-danger")
    WebElement loginErrorMessage;

    @FindBy(css = ".d-inline-block")
    public WebElement userHeader;

    @FindBy(css = ".MuiDialogActions-root > button:nth-child(2)")
    WebElement signIn;

    @FindBy(css = "button.MuiTab-root:nth-child(2)")
    WebElement registerButton;

    @FindBy(css = ".MuiButton-outlined")
    public WebElement signInOut;

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(name = "RepeatPassword")
    public WebElement confirmPassword;

    @FindBy(css = ".MuiDialogActions-root > button:nth-child(2)")
    WebElement signUp;

    @FindBy(xpath = "//span[contains(text(),'CLEAR')]")
    WebElement clearButton;

    @FindBy(xpath = "//span[contains(text(),'Cancel')]")
    WebElement cancel;

    @FindBy(css = ".text-danger")
    public WebElement registrationErrorMessage;

    @FindBy(css = ".register > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > p:nth-child(3)")
    WebElement emailErrorMessage;

    @FindBy(css = ".register > form:nth-child(1) > div:nth-child(2) > div:nth-child(1) > p:nth-child(3)")
    WebElement passwordErrorMessage;

    @FindBy(css = ".register > form:nth-child(1) > div:nth-child(3) > div:nth-child(1) > p:nth-child(3)")
    WebElement confirmPasswordErrorMessage;

    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/div/div/h4")
    public WebElement userName;


    public SignInUpMenu(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver,this);
    }

    public void clickClearButton(){
        clearButton.click();
    }
    public void clickEmail(){
        email.click();
    }

    public void setEmail(String value){
        email.sendKeys(value);
    }

    public void clickPassword(){
        password.click();
    }

    public void setPassword(String value){
        password.sendKeys(value);
    }

    public void clickConfirmPassword(){
        confirmPassword.click();
    }

    public void setConfirmPassword(String value){
        confirmPassword.sendKeys(value);
    }

    public void clickSignUp(){
        EventElement element = new EventElement(driver,signUp);
        element.click();
        //signUp.click();
    }

    public void clickCancel(){
        EventElement element = new EventElement(driver,cancel);
        element.click();
        //cancel.click();
    }

    public void clickSignInOut(){
        EventElement element = new EventElement(driver,signInOut);
        element.click();
        //signInOut.click();
    }

    public void clickRegisterButton(){
        EventElement element = new EventElement(driver,registerButton);
        element.click();

        //registerButton.click();
    }

    public String getRegistrationErrorMessage(){
       return registrationErrorMessage.getText();
    }

    public String getEmailErrorMessage(){
        return emailErrorMessage.getText();
    }

    public String getPasswordErrorMessage(){
        return passwordErrorMessage.getText();
    }

    public String getConfirmPasswordErrorMessage(){
        return confirmPasswordErrorMessage.getText();
    }

    public String getUserName(){
        return userName.getText();
    }

    public void clickSignIn(){
        EventElement eventElement = new EventElement(driver,signIn);
        eventElement.click();
    }

    public void clickSignInAndWait(WebElement element){
        new EventElement(driver,signIn).clickAndWait(element);
    }

    public String getLoginError(){
        return loginErrorMessage.getText();
    }


    public void authoriseUser(String email, String password){
        clickSignInOut();
        clickEmail();
        setEmail(email);
        clickPassword();
        setPassword(password);
        clickSignIn();
    }

    public void authoriseAndWaitUser(String email,String password,WebElement element){
        clickSignInOut();
        clickEmail();
        setEmail(email);
        clickPassword();
        setPassword(password);
        clickSignInAndWait(element);
    }

    public void registerUser(String email, String password, String confirmPassword){
        clickSignInOut();
        clickRegisterButton();
        clickEmail();
        setEmail(email);
        clickPassword();
        setPassword(password);
        clickConfirmPassword();
        setConfirmPassword(confirmPassword);
        clickSignUp();
    }
}

