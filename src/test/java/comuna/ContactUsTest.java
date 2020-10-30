package comuna;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.homePageSearch.HomePageSearchMenu;
import pages.navBar.ContactUsPage;
import pages.navBar.SearchUserPage;

public class ContactUsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        String email = "zlotech@rambler.ru";
        String pass = "1234event";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        homePageNavBar.clickContactUsButton();
    }
    @Test
    public void selectProblemTypeTest(){
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String problem = "Bad Event";
        contactUsPage.selectProblemType(problem);
        Assert.assertEquals(contactUsPage.getChosenProblemTypeValue(), "badEvent");
    }
    @Test
    public void enterDescriptionTest(){
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String text = "I have a problem";
        contactUsPage.enterDescription(text);
        Assert.assertEquals(contactUsPage.getTextFromDescriptionField(), text);
    }
    @Test
    public void clearButtonTest(){
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String text = "I have a problem";
        contactUsPage.enterDescription(text).clickClearButton();
        Assert.assertEquals(contactUsPage.getTextFromDescriptionField(), "");
    }
    @Test
    public void sendMessageTest(){
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String text = "I have a problem";
        contactUsPage.enterDescription(text).clickSubmitButton();
        Assert.assertEquals(contactUsPage.getConfirmedMessageText(), "Message was succesfully sended");
    }
    @Test
    public void emptyDescriptionErrorTest(){
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        String text = "";
        contactUsPage.enterDescription(text).selectProblemType("Bad User");
        Assert.assertEquals(contactUsPage.getEmptyDescriptionError(), "Required");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}
