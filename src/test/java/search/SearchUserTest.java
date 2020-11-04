package search;

import jdbc.SearchRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.homePageSearch.HomePageSearchMenu;
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
        SignInUpMenu signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
        String email = "zlotech@rambler.ru";
        String pass = "1234event";
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = PageFactory.initElements(driver, HomePageNavBar.class);
        searchUserPage = PageFactory.initElements(driver, SearchUserPage.class);
        homePageNavBar.clickSearchUsersButton();
    }
    @Test
    public void typeInSearchFieldTest(){
        String text = "Admin";
        searchUserPage.typeInSearchField(text);
        Assert.assertEquals(searchUserPage.getSearchFieldValue(), text);
    }
    @Test
    public void clearSearchFieldTest(){
        String text = "Admin";
        searchUserPage.typeInSearchField(text).clickClearButton();
        Assert.assertEquals(searchUserPage.getSearchFieldValue(), "");
    }
    @Test
    public void searchUserTest(){
        String name = "Admin";
        searchUserPage.searchUser(name);
        Assert.assertEquals(searchUserPage.getNumberOfFoundedUsers(), SearchRepository.getNumberOfUsersByNameWithNInQuery(name));
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
