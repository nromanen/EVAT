package comuna;
import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.navBar.ContactUsPage;

public class ContactUsTest extends BaseTest {
    ContactUsPage contactUsPage;
    String email = "zlotech@rambler.ru";
    String pass = "1234event";
    String textForDescription = "I have a problem";
    String problem = "Bad Event";
    String textForEmptyDescription = "";

    @Test
    public void verifySendingMessage(){
        SignInUpMenu signInUpMenu = new SignInUpMenu(BaseTest.getDriver());
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(BaseTest.getDriver());
        homePageNavBar.clickContactUsButton();
        contactUsPage = new ContactUsPage(BaseTest.getDriver());
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
    public void verifyClearingDescriptionField(){
        SignInUpMenu signInUpMenu = new SignInUpMenu(BaseTest.getDriver());
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(BaseTest.getDriver());
        homePageNavBar.clickContactUsButton();
        contactUsPage = new ContactUsPage(BaseTest.getDriver());
        contactUsPage.enterDescription(textForDescription).clickClearButton();
        Assert.assertEquals(contactUsPage.getTextFromDescriptionField(), "");
    }

    @Test
    public void verifyEmptyDescriptionErrorTest(){
        SignInUpMenu signInUpMenu = new SignInUpMenu(BaseTest.getDriver());
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(BaseTest.getDriver());
        homePageNavBar.clickContactUsButton();
        contactUsPage = new ContactUsPage(BaseTest.getDriver());
        contactUsPage.enterDescription(textForEmptyDescription).selectProblemType("Bad User");
        Assert.assertEquals(contactUsPage.getEmptyDescriptionError(), "Required");
    }

}
