package comuna;
import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.comuna.ChatWithUserPage;
import pages.comuna.ComunaPage;
import pages.homePageSearch.HomePageSearchMenu;

public class ChatWithUserTest extends BaseTest {
    ChatWithUserPage chatWithUserPage;
    String email = "zlotech@rambler.ru";
    String pass = "1234event";
    String message = "Hello";

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        homePageNavBar.clickComunaButton();
        ComunaPage comunaPage = new ComunaPage(driver);
        chatWithUserPage = new ChatWithUserPage(driver);
        homePageNavBar.clickComunaButton();
        comunaPage.goToFirstChat();
    }
    @Test
    public  void verifySendingMessageTest(){
        chatWithUserPage.enterMessage(message);
        Assert.assertEquals(chatWithUserPage.getTextFromMessageField(), message);
        int numberOfMessages = chatWithUserPage.getNumberOfMessages();
        chatWithUserPage.sendMessage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(chatWithUserPage.getNumberOfMessages(), numberOfMessages + 1);
        String messageText = chatWithUserPage.getTextSentMessage().substring(0, 5);
        Assert.assertEquals(messageText, "Hello");
    }

    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }
}
