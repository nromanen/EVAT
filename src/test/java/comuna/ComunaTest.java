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
import pages.navBar.ContactUsPage;

public class ComunaTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        String email = "zlotech@rambler.ru";
        String pass = "123456";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        homePageNavBar.clickComunaButton();
    }
    @Test //add -Dfile.encoding=UTF-8 in VM options
    public void goToTheFirstChatTest(){
        ComunaPage comunaPage = new ComunaPage(driver);
        ChatWithUserPage chatWithUserPage = new ChatWithUserPage(driver);
        comunaPage.goToFirstChat();
        Assert.assertEquals(chatWithUserPage.getChatTitleText(), "Chat with Марина Маринівна Гульпак\n" +
                "3 Messages");
    }
    @Test
    public void getNumberOfChatsTest(){
        ComunaPage comunaPage = new ComunaPage(driver);
        Assert.assertEquals(comunaPage.getNumberOfUsersChats(), 2);
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
