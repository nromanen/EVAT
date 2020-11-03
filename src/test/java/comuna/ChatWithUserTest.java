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
import pages.comuna.ChatWithUserPage;
import pages.comuna.ComunaPage;
import pages.homePageSearch.HomePageSearchMenu;

public class ChatWithUserTest {
    WebDriver driver;
    ChatWithUserPage chatWithUserPage;

    @BeforeMethod //add -Dfile.encoding=UTF-8 in VM options
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
        String email = "zlotech@rambler.ru";
        String pass = "1234event";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = PageFactory.initElements(driver, HomePageNavBar.class);
        ComunaPage comunaPage = PageFactory.initElements(driver, ComunaPage.class);
        chatWithUserPage = PageFactory.initElements(driver, ChatWithUserPage.class);
        homePageNavBar.clickComunaButton();
        comunaPage.goToFirstChat();
    }
    @Test
    public void enterMessageTest(){
        String message = "Hello";
        chatWithUserPage.enterMessage(message);
        Assert.assertEquals(chatWithUserPage.getTextFromMessageField(), message);
    }
    @Test
    public void sendMessageTest() throws InterruptedException {
        String message = "Hello";
        int numberOfMessages = chatWithUserPage.getNumberOfMessages();
        chatWithUserPage.enterMessage(message).sendMessage();
        Thread.sleep(3000);
        Assert.assertEquals(chatWithUserPage.getNumberOfMessages(), numberOfMessages + 1);
    }

    @Test
    public void getTextSentMessageTest(){
        String messageText = chatWithUserPage.getTextSentMessage().substring(0, 6);
        Assert.assertEquals(messageText, "привіт");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
