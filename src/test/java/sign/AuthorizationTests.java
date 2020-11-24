package sign;
import io.qameta.allure.Description;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.homePage.SignInUpMenu;
import pages.search.HomePageSearchMenu;
import utility.EventElement;
import utility.SetUpDriver;
import base.BaseTest;

public class AuthorizationTests extends BaseTest {
    SignInUpMenu signInUpMenu;
    SetUpDriver setUpDriver;

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        openBrowser();
        driver.get(HomePageSearchMenu.URL);
        signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
    }

    @DataProvider(name = "signInData")
    public Object[][] provideSignInData(){
        return new Object[][]{{"carat98@icloud.com","1234567","\"Invalid password\""},
                {"i@gmail.com","1234567","\"User not found\""}};
    }
    /*
    Test to check that registered user
    can login into account
     */

    @Test
    @Description(useJavaDoc = true)
    public void testLogin(){
        String login = "carat98@icloud.com";
        String password = "12345678";
        String userName = "carat98";
        signInUpMenu.authoriseAndWaitUser(login,password,signInUpMenu.userHeader);
        new EventElement(super.getDriver(), signInUpMenu.userName).waitUntilDisplayed();
        Assert.assertEquals(signInUpMenu.getUserName(),userName);
    }

    /*
    Test to check that error messages during
    login are correct
     */

    @Test(dataProvider = "signInData")
    @Description(useJavaDoc = true)
    public void testErrorSignInMessages(String email, String password, String correctMessage){
        signInUpMenu.authoriseAndWaitUser(email,password,signInUpMenu.errorMessage);
        new EventElement(super.getDriver(), signInUpMenu.errorMessage).waitUntilDisplayed();
        Assert.assertTrue(signInUpMenu.getLoginError().equals(correctMessage));
    }

    /*
    Test 'Clear' button
     */

    @Test
    @Description(useJavaDoc = true)
    public void testClearButton(){
        String login = "carat98@icloud.com";
        String password = "12345678";
        signInUpMenu.clickSignInOut();
        signInUpMenu.clickEmail();
        signInUpMenu.setEmail(login);
        signInUpMenu.clickPassword();
        signInUpMenu.setPassword(password);
        signInUpMenu.clickClearButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(signInUpMenu.email.getText().isEmpty(),true);
        softAssert.assertEquals(signInUpMenu.password.getText().isEmpty(),true);
        softAssert.assertAll();
    }

    /*
    Test 'Cancel' button
     */

    @Test
    @Description(useJavaDoc = true)
    public void testCancelButton(){
        String login = "carat98@icloud.com";
        String password = "12345678";
        signInUpMenu.clickSignInOut();
        signInUpMenu.clickEmail();
        signInUpMenu.setEmail(login);
        signInUpMenu.clickPassword();
        signInUpMenu.setPassword(password);
        signInUpMenu.clickCancel();
        Assert.assertTrue(signInUpMenu.signInOut.isDisplayed());
    }

    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }

}
