package signInOut;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.SignInUpMenu;
import pages.homePageSearch.HomePageSearchMenu;
import utility.EventElement;
import utility.SetUpDriver;

public class AuthorizationTests {
    SignInUpMenu signInUpMenu;
    SetUpDriver setUpDriver;


    @BeforeMethod
    public void setup(){
        setUpDriver = new SetUpDriver();
        WebDriver driver = setUpDriver.getDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
    }
    @DataProvider(name = "signInData")
    public Object[][] provideSignInData(){
        return new Object[][]{{"carat98@icloud.com","1234567","\"Invalid password\""},
                {"i@gmail.com","1234567","\"User not found\""}};
    }

    @Test(priority = 4)
    public void testLogin(){
        String login = "carat98@icloud.com";
        String password = "12345678";
        String userName = "carat98";
        signInUpMenu.authoriseAndWaitUser(login,password,signInUpMenu.userHeader);
        new EventElement(setUpDriver.getDriver(), signInUpMenu.userName).waitUntilDisplayed();
        Assert.assertEquals(signInUpMenu.getUserName(),userName);
    }

    @Test(dataProvider = "signInData", priority = 3)
    public void testErrorSignInMessages(String email, String password, String correctMessage){
        signInUpMenu.authoriseAndWaitUser(email,password,signInUpMenu.errorMessage);
        new EventElement(setUpDriver.getDriver(), signInUpMenu.errorMessage).waitUntilDisplayed();
        Assert.assertTrue(signInUpMenu.getLoginError().equals(correctMessage));
    }

    @Test(priority = 1)
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

    @Test(priority = 2)
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

    @AfterMethod
    public void closeBrowser(){
        setUpDriver.driverQuit();
    }

}
