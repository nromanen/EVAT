package comuna;
import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.homePageSearch.HomePageSearchMenu;
import pages.navBar.ContactUsPage;

public class ContactUsTest extends BaseTest {
    ContactUsPage contactUsPage;
    String email = "zlotech@rambler.ru";
    String pass = "1234event";
    String textForDescription = "I have a problem";
    String problem = "Bad Event";
    String textForEmptyDescription = "";

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        homePageNavBar.clickContactUsButton();
        contactUsPage = new ContactUsPage(driver);
    }
    @Test
    public void verifySendingMessageTest(){
        SoftAssert softAssert = new SoftAssert();
        contactUsPage.selectProblemType(problem);
        softAssert.assertEquals(contactUsPage.getChosenProblemTypeValue(), "badEvent");
        contactUsPage.enterDescription(textForDescription);
        softAssert.assertEquals(contactUsPage.getTextFromDescriptionField(), textForDescription);
        contactUsPage.clickSubmitButton();
        softAssert.assertEquals(contactUsPage.getConfirmedMessageText(), "Message was succesfully sended");
        softAssert.assertAll();
    }

    @Test
    public void verifyClearingDescriptionFieldTest(){
        contactUsPage.enterDescription(textForDescription).clickClearButton();
        Assert.assertEquals(contactUsPage.getTextFromDescriptionField(), "");
    }

    @Test
    public void verifyEmptyDescriptionErrorTest(){
        contactUsPage.enterDescription(textForEmptyDescription).selectProblemType("Bad User");
        Assert.assertEquals(contactUsPage.getEmptyDescriptionError(), "Required");
    }
    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }

}
