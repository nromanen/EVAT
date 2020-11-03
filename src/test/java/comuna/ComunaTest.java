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
import pages.navBar.ContactUsPage;

public class ComunaTest {
    WebDriver driver;
    ComunaPage comunaPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
        String email = "zlotech@rambler.ru";
        String pass = "1234event";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = PageFactory.initElements(driver, HomePageNavBar.class);
        comunaPage = PageFactory.initElements(driver, ComunaPage.class);
        homePageNavBar.clickComunaButton();
    }
    @Test //add -Dfile.encoding=UTF-8 in VM options
    public void goToTheFirstChatTest(){
        ChatWithUserPage chatWithUserPage = PageFactory.initElements(driver, ChatWithUserPage.class);
        comunaPage.goToFirstChat();
        String title = chatWithUserPage.getChatTitleText().substring(0,26);
        Assert.assertEquals(title, "Chat with Марина Маринівна");
    }
    @Test
    public void getNumberOfChatsTest(){
        Assert.assertEquals(comunaPage.getNumberOfUsersChats(), 2);
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
