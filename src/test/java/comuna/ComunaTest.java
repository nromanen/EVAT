package comuna;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.comuna.ChatWithUserPage;
import pages.comuna.ComunaPage;
import pages.search.HomePageSearchMenu;
import utility.EventElement;

public class ComunaTest extends BaseTest {
    ComunaPage comunaPage;
    ChatWithUserPage chatWithUserPage;
    String email = "zlotech@rambler.ru";
    String pass = "1234event";

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
        comunaPage = new ComunaPage(driver);
        chatWithUserPage = new ChatWithUserPage(driver);
    }

    /**
     * Test to verify transition to the first chat
     */
    @Test(description = "CHIS-150")
    @Description(useJavaDoc = true)
    public void verifyGoToTheFirstChatTest(){
        comunaPage.goToFirstChat();
        String title = chatWithUserPage.getChatTitleText().substring(0,25);
        Assert.assertEquals(title, "Chat with Katty Ihnatyeva");
    }

    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }
}
