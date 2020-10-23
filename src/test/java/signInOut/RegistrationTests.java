package signInOut;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SignInUpMenu;


public class RegistrationTests {
    final String WEBSITE_URL = "https://eventsexpress-test.azurewebsites.net/home/events?page=1";
    final String DRIVER_PATH = "/Users/illyashulman/EventExpress/geckodriver";
    WebDriver driver;
    SignInUpMenu signInUpMenu;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.gecko.driver", DRIVER_PATH);
        driver = new FirefoxDriver();

    }

    @DataProvider(name = "registerData")
    public Object[][] provideRegisterData(){
        return new Object[][]{{"carat98@icloud.com","0909","0909","Must be 6 characters or more"},
                {"carat98@icloud.com","1234567890123456","1234567890123456","Must be 15 characters or less"}};
    }

    @DataProvider(name = "emailData")
    public Object[][] provideEmailData(){
        return new Object[][]{{"12345","09090909","Invalid email address"},{"carat98","09090909","Invalid email address"},
                {"carat98@","09090909","Invalid email address"},{"@gmail.com","09090909","Invalid email address"}};
    }
    @Test(priority=3)
    public void testRegistrationWithExistingEmail() {
        driver.get(WEBSITE_URL);
        String email = "carat98@icloud.com";
        String password = "090909iS";
        signInUpMenu = new SignInUpMenu(driver);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.registerUser(email, password,password);
        Assert.assertEquals(signInUpMenu.getRegistrationErrorMessage(), "Email already exists in database");
    }

    @Test(priority = 2)
    public void testRegistrationWithEmptyMandatoryFields() {

        driver.get(WEBSITE_URL);
        String correctMessage = "Required";

        signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.clickSignInOut();
        signInUpMenu.clickRegisterButton();
        signInUpMenu.clickEmail();
        signInUpMenu.clickPassword();
        signInUpMenu.clickConfirmPassword();
        signInUpMenu.clickSignUp();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(signInUpMenu.getEmailErrorMessage(), correctMessage);
        softAssert.assertEquals(signInUpMenu.getPasswordErrorMessage(), correctMessage);
        softAssert.assertEquals(signInUpMenu.getConfirmPasswordErrorMessage(), correctMessage);
        softAssert.assertAll();
    }

    @Test(dataProvider = "registerData",priority = 1)
    public void testPasswordField(String email, String password, String confirmPassword, String correctError){
        driver.get(WEBSITE_URL);
        signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.registerUser(email,password,confirmPassword);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(signInUpMenu.getPasswordErrorMessage(),correctError);
        softAssert.assertEquals(signInUpMenu.getConfirmPasswordErrorMessage(),correctError);
        softAssert.assertAll();
    }

    @Test(dataProvider = "emailData",priority = 0)
    public void testEmailField(String email,String password, String correctMessage){
        driver.get(WEBSITE_URL);
        signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.registerUser(email,password,password);
        Assert.assertEquals(signInUpMenu.getEmailErrorMessage(),correctMessage);
    }

    @AfterSuite
    public void closeBrowser(){
        driver.quit();
    }
}
