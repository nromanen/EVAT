package comuna;
import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.homePage.HomePageNavBar;
import pages.homePage.SignInUpMenu;
import pages.navigation.ContactUsPage;
import pages.search.HomePageSearchMenu;;

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
        openBrowser();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        homePageNavBar.clickContactUsButton();
        contactUsPage = new ContactUsPage(driver);
    }

    /**
     * Test to verify ability of sending messages to tech support
     */
    @Test(description = "CHIS-155")
    @Description(useJavaDoc = true)
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

    /**
     * Test to verify ability to clear written in the description field text
     */
    @Test(description = "CHIS-154")
    @Description(useJavaDoc = true)
    public void verifyClearingDescriptionFieldTest(){
        contactUsPage.enterDescription(textForDescription).clickClearButton();
        Assert.assertEquals(contactUsPage.getTextFromDescriptionField(), "");
    }

    /**
     * Test to verify appearance of error message when user leave description field empty
     */
    @Test(description = "CHIS-156")
    @Description(useJavaDoc = true)
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
