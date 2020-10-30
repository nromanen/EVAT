package comuna;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

    @BeforeMethod //add -Dfile.encoding=UTF-8 in VM options
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        String email = "zlotech@rambler.ru";
        String pass = "1234event";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        ComunaPage comunaPage = new ComunaPage(driver);
        ChatWithUserPage chatWithUserPage = new ChatWithUserPage(driver);
        homePageNavBar.clickComunaButton();
        comunaPage.goToFirstChat();
    }
    @Test
    public void enterMessageTest(){
        ChatWithUserPage chatWithUserPage = new ChatWithUserPage(driver);
        String message = "Hello";
        chatWithUserPage.enterMessage(message);
        Assert.assertEquals(chatWithUserPage.getTextFromMessageField(), message);
    }
    @Test
    public void sendMessageTest() throws InterruptedException {
        ChatWithUserPage chatWithUserPage = new ChatWithUserPage(driver);
        String message = "Hello";
        int numberOfMessages = chatWithUserPage.getNumberOfMessages();
        chatWithUserPage.enterMessage(message).sendMessage();
        Thread.sleep(3000);
        Assert.assertEquals(chatWithUserPage.getNumberOfMessages(), numberOfMessages + 1);
    }

    @Test
    public void getTextSentMessageTest(){
        ChatWithUserPage chatWithUserPage = new ChatWithUserPage(driver);
        Assert.assertEquals(chatWithUserPage.getTextSentMessage(), "привіт\n" +
                "6 days ago");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
