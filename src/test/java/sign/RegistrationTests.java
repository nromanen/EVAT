package sign;

import base.BaseTest;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.homePage.SignInUpMenu;
import pages.search.HomePageSearchMenu;
import utility.EventElement;
import utility.SetUpDriver;


public class RegistrationTests extends BaseTest {
    SignInUpMenu signInUpMenu;



    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        openBrowser();
        driver.get(HomePageSearchMenu.URL);
        signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
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

    /*
    Test to verify that User cannot register
    with email that had been already used for
    registration
     */
    @Test
    @Description(useJavaDoc = true)
    public void testRegistrationWithExistingEmail() {
        String email = "carat98@icloud.com";
        String password = "090909iS";
        signInUpMenu.registerUser(email, password,password);
        new EventElement(super.getDriver(), signInUpMenu.registrationErrorMessage).waitUntilDisplayed();
        Assert.assertEquals(signInUpMenu.getRegistrationErrorMessage(), "Email already exists in database");
    }


    /*
    Test to verify that error messages are correct
    if mandatory fields are empty during registration
     */

    @Test
    @Description(useJavaDoc = true)
    public void testRegistrationWithEmptyMandatoryFields() {
        String correctMessage = "Required";
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

    /*
    Test to check validation of 'password' field
     */

    @Test(dataProvider = "registerData")
    @Description(useJavaDoc = true)
    public void testPasswordField(String email, String password, String confirmPassword, String correctError){
        signInUpMenu.registerUser(email,password,confirmPassword);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(signInUpMenu.getPasswordErrorMessage(),correctError);
        softAssert.assertEquals(signInUpMenu.getConfirmPasswordErrorMessage(),correctError);
        softAssert.assertAll();
    }
    /*
    Test to check validation for 'email' field
     */

    @Test(dataProvider = "emailData")
    @Description(useJavaDoc = true)
    public void testEmailField(String email,String password, String correctMessage){
        signInUpMenu.registerUser(email,password,password);
        Assert.assertEquals(signInUpMenu.getEmailErrorMessage(),correctMessage);
    }

    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }

}
