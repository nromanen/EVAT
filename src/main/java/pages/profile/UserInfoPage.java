package pages.profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UserInfoPage{

    WebDriver driver;

    @FindBy(how = How.CSS, using = "#main > div.info > div.col-sm-12.col-md-6 > div:nth-child(1) > div.col-8")
    private WebElement userName;

    @FindBy(how = How.CSS, using = "#main > div.info > div.col-sm-12.col-md-6 > div:nth-child(2) > div.col-8")
    private WebElement age;

    @FindBy(how = How.CSS, using = "#main > div.info > div.col-sm-12.col-md-6 > div:nth-child(3) > div.col-8")
    private WebElement gender;

    @FindBy(how = How.CSS, using = "#main > div.info > div.col-sm-12.col-md-6 > div:nth-child(4) > div.col-8")
    private WebElement email;

    @FindBy(how = How.CSS, using = "#main > div.info > div.col-sm-12.col-md-6 > div:nth-child(5) > div.col-8 ")
    private WebElement interests;

    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getValueUserName(){
        return userName.getText();
    }

    public String getValueAge(){
        return age.getText();
    }

    public String getValueGender(){
        return gender.getText();
    }

    public String getValueEmail(){
        return email.getText();
    }

    public String getValueInterests(){
        return interests.getText();
    }

    public WebElement getUserName() {
        return userName;
    }

    public void setUserName(WebElement userName) {
        this.userName = userName;
    }

    public WebElement getAge() {
        return age;
    }

    public void setAge(WebElement age) {
        this.age = age;
    }

    public WebElement getGender() {
        return gender;
    }

    public void setGender(WebElement gender) {
        this.gender = gender;
    }

    public WebElement getEmail() {
        return email;
    }

    public void setEmail(WebElement email) {
        this.email = email;
    }

    public WebElement getInterests() {
        return interests;
    }

    public void setInterests(WebElement interests) {
        this.interests = interests;
    }

}
