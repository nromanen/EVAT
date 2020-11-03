package signInOut;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.SignInUpMenu;

public class AuthorizationTests {
    final String WEBSITE_URL = "https://eventsexpress-test.azurewebsites.net/home/events?page=1";
    final String DRIVER_PATH = "/Users/illyashulman/EventExpressTest/src/test/resources/geckodriver";
    WebDriver driver;
    SignInUpMenu signInUpMenu;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.gecko.driver", DRIVER_PATH);
        driver = new FirefoxDriver();
    }
    @BeforeMethod
    public void loadPage(){
        driver.get(WEBSITE_URL);
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

        signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.authoriseAndWaitUser(login,password,signInUpMenu.userHeader);
        Assert.assertEquals(signInUpMenu.getUserName(),userName);
    }

    @Test(dataProvider = "signInData", priority = 3)
    public void testErrorSignInMessages(String email, String password, String correctMessage){
        signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.authoriseAndWaitUser(email,password,signInUpMenu.errorMessage);
        Assert.assertTrue(signInUpMenu.getLoginError().contains(correctMessage));
    }

    @Test(priority = 1)
    public void testClearButton(){
        String login = "carat98@icloud.com";
        String password = "12345678";

        signInUpMenu = new SignInUpMenu(driver);
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

        signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.clickSignInOut();
        signInUpMenu.clickEmail();
        signInUpMenu.setEmail(login);
        signInUpMenu.clickPassword();
        signInUpMenu.setPassword(password);
        signInUpMenu.clickCancel();

        Assert.assertTrue(signInUpMenu.signInOut.isDisplayed());
    }


    @AfterSuite
    public void closeBrowser(){
        driver.quit();
    }

}
