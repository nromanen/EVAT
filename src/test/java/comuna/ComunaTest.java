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
import utility.SetUpDriver;

public class ComunaTest {
    ComunaPage comunaPage;
    SetUpDriver setUpDriver;
    ChatWithUserPage chatWithUserPage;

    @BeforeMethod
    public void setUp(){
        setUpDriver = new SetUpDriver();
        WebDriver driver = setUpDriver.getDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
        String email = "zlotech@rambler.ru";
        String pass = "1234event";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = PageFactory.initElements(driver, HomePageNavBar.class);
        chatWithUserPage = PageFactory.initElements(driver, ChatWithUserPage.class);
        comunaPage = PageFactory.initElements(driver, ComunaPage.class);
        homePageNavBar.clickComunaButton();
    }
    @Test
    public void goToTheFirstChatTest(){
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
        setUpDriver.driverQuit();
    }
}
