package comuna;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.homePageSearch.HomePageSearchMenu;
import pages.navBar.ContactUsPage;
import pages.navBar.SearchUserPage;
import utility.SetUpDriver;

public class ContactUsTest {
    ContactUsPage contactUsPage;
    SetUpDriver setUpDriver;

    @BeforeMethod
    public void setUp(){
        setUpDriver = new SetUpDriver();
        WebDriver driver = setUpDriver.getDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
        String email = "zlotech@rambler.ru";
        String pass = "1234event";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = PageFactory.initElements(driver, HomePageNavBar.class);
        homePageNavBar.clickContactUsButton();
        contactUsPage = PageFactory.initElements(driver, ContactUsPage.class);
    }
    @Test(description = "CHIS-152")
    public void selectProblemTypeTest(){
        String problem = "Bad Event";
        contactUsPage.selectProblemType(problem);
        Assert.assertEquals(contactUsPage.getChosenProblemTypeValue(), "badEvent");
    }
    @Test(description = "CHIS-153")
    public void enterDescriptionTest(){
        String text = "I have a problem";
        contactUsPage.enterDescription(text);
        Assert.assertEquals(contactUsPage.getTextFromDescriptionField(), text);
    }
    @Test(description = "CHIS-154")
    public void clearButtonTest(){
        String text = "I have a problem";
        contactUsPage.enterDescription(text).clickClearButton();
        Assert.assertEquals(contactUsPage.getTextFromDescriptionField(), "");
    }
    @Test(description = "CHIS-155")
    public void sendMessageTest(){
        String text = "I have a problem";
        contactUsPage.enterDescription(text).clickSubmitButton();
        Assert.assertEquals(contactUsPage.getConfirmedMessageText(), "Message was succesfully sended");
    }
    @Test(description = "CHIS-156")
    public void emptyDescriptionErrorTest(){
        String text = "";
        contactUsPage.enterDescription(text).selectProblemType("Bad User");
        Assert.assertEquals(contactUsPage.getEmptyDescriptionError(), "Required");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        setUpDriver.driverQuit();
    }

}
