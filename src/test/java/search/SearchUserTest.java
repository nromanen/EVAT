package search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.homePageSearch.HomePageSearchMenu;
import pages.homePageSearch.SearchResultPage;
import pages.navBar.SearchUserPage;

public class SearchUserTest {
    WebDriver driver;
    HomePageSearchMenu homePageSearchMenu;
    SearchUserPage searchUserPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        homePageSearchMenu = new HomePageSearchMenu(driver);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        String email = "zlotech@rambler.ru";
        String pass = "123456";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        homePageNavBar.clickSearchUsersButton();
    }
    @Test
    public void typeInSearchFieldTest(){
        searchUserPage = new SearchUserPage(driver);
        String text = "Admin";
        searchUserPage.typeInSearchField(text);
        Assert.assertEquals(searchUserPage.getSearchFieldValue(), text);
    }
    @Test
    public void clearSearchFieldTest(){
        searchUserPage = new SearchUserPage(driver);
        String text = "Admin";
        searchUserPage.typeInSearchField(text).clickClearButton();
        Assert.assertEquals(searchUserPage.getSearchFieldValue(), "");
    }
    @Test
    public void searchUserTest(){
        searchUserPage = new SearchUserPage(driver);
        String name = "Admin";
        searchUserPage.searchUser(name);
        Assert.assertEquals(searchUserPage.getNumberOfFoundedUsers(), 1);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
