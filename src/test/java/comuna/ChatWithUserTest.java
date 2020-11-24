package comuna;
import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.homePage.HomePageNavBar;
import pages.homePage.SignInUpMenu;
import pages.comuna.ChatWithUserPage;
import pages.comuna.ComunaPage;
import pages.search.HomePageSearchMenu;
import utility.EventElement;

public class ChatWithUserTest extends BaseTest {
    ChatWithUserPage chatWithUserPage;
    String email = "zlotech@rambler.ru";
    String pass = "1234event";
    String message = "Hello";

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        openBrowser();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        new EventElement(driver, homePageNavBar.getComunaButton()).click();
        ComunaPage comunaPage = new ComunaPage(driver);
        chatWithUserPage = new ChatWithUserPage(driver);
        homePageNavBar.clickComunaButton();
        comunaPage.goToFirstChat();
    }

    /**
     * Test to verify ability to send messages to users
     */
    @Test(description = "CHIS-148")
    @Description(useJavaDoc = true)
    public  void verifySendingMessageToUserTest(){
        chatWithUserPage.enterMessage(message);
        Assert.assertEquals(chatWithUserPage.getTextFromMessageField(), message);
        chatWithUserPage.sendMessage();
        Assert.assertEquals(chatWithUserPage.getNumberOfMessages(), 1);
        String messageText = chatWithUserPage.getTextSentMessage().substring(0, 5);
        Assert.assertEquals(messageText, "Hello");
    }

    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }
}
