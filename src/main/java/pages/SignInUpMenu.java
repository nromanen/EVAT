package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class SignInUpMenu {
    WebDriver driver;
    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div/div[2]/div/div/div[2]")
    WebElement loginErrorMessage;

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

    @FindBy(css = "body > div.MuiDialog-root > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div.MuiTypography-root.MuiTypography-body1 > div > div > form > div:nth-child(3) > div > button:nth-child(1)")
    WebElement clearButton;

    @FindBy(css = "body > div.MuiDialog-root > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > button")
    WebElement cancel;

    @FindBy(css = "body > div.MuiDialog-root > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div.MuiTypography-root.MuiTypography-body1 > p")
    WebElement registrationErrorMessage;

    @FindBy(css = ".register > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > p:nth-child(3)")
    WebElement emailErrorMessage;

    @FindBy(css = ".register > form:nth-child(1) > div:nth-child(2) > div:nth-child(1) > p:nth-child(3)")
    WebElement passwordErrorMessage;

    @FindBy(css = ".register > form:nth-child(1) > div:nth-child(3) > div:nth-child(1) > p:nth-child(3)")
    WebElement confirmPasswordErrorMessage;

    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/div/div/h4")
   public WebElement userName;

    @FindBy(css = "body > div.MuiDialog-root > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div.MuiTypography-root.MuiTypography-body1 > div > div > div.d-flex.justify-content-around.mb-3 > div:nth-child(3) > button")
    public WebElement googleButton;

    @FindBy(css = "#view_container > div > div > div.pwWryf.bxPAYd > div > div.WEQkZc > div > form > span > section > div > div > div > div > ul > li:nth-child(1)")
    public WebElement userGoogleIcon;


    public SignInUpMenu(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver,this);
    }
    public void clickUserGoogleIcon(){
        userGoogleIcon.click();
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
        signUp.click();
    }

    public void clickCancel(){
        cancel.click();
    }

    public void clickSignInOut(){
        signInOut.click();
    }

    public void clickRegisterButton(){
        registerButton.click();
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
        signIn.click();
    }

    public String getLoginError(){
        return loginErrorMessage.getText();
    }

    public void clickGoogleButton() {googleButton.click();}

    public void authoriseUser(String email, String password){
        clickSignInOut();
        clickEmail();
        setEmail(email);
        clickPassword();
        setPassword(password);
        clickSignIn();
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
