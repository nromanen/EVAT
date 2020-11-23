package search;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import pages.navigation.SearchUserPage;
import pages.search.HomePageSearchMenu;

public class SearchUserTest extends BaseTest {
    SearchUserPage searchUserPage;
    String email = "zlotech@rambler.ru";
    String pass = "1234event";
    String nameOfUser = "Admin";

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        openBrowser();
        driver.get(HomePageSearchMenu.URL);
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.authoriseUser(email,pass);
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        homePageNavBar.clickContactUsButton();
        searchUserPage = new SearchUserPage(driver);
        homePageNavBar.clickSearchUsersButton();
    }

    /**
     * Test to verify search of user
     */
    @Test(description = "CHIS-146")
    @Description(useJavaDoc = true)
    public void verifySearchUserTest(){
        searchUserPage.typeInSearchField(nameOfUser);
        Assert.assertEquals(searchUserPage.getSearchFieldValue(), nameOfUser);
        searchUserPage.clickSearchButton();
        Assert.assertEquals(searchUserPage.getNumberOfFoundedUsers(), 1);

    }

    /**
     * Test to verify ability to clear written in the search user field text
     */
    @Test(description = "CHIS-145")
    @Description(useJavaDoc = true)
    public void verifyClearingSearchFieldTest(){
        searchUserPage.typeInSearchField(nameOfUser).clickClearButton();
        Assert.assertEquals(searchUserPage.getSearchFieldValue(), "");
    }

    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }

}
