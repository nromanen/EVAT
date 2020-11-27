package search;
import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.search.HomePageSearchMenu;
import pages.search.SearchResultPage;

import java.time.LocalDate;

public class SearchMenuUITest extends BaseTest {
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        openBrowser();
        driver.get(HomePageSearchMenu.URL);
        searchResultPage = new SearchResultPage(driver);
        homePageSearchMenu = new HomePageSearchMenu(driver);
    }

    /**
     * Test to verify ability to type text in keyword field
     */
    @Test
    @Description("Test to verify ability to type text in keyword field")
    public void testTypeKeyword() {
        String keyword = "yes";
        homePageSearchMenu.typeKeyword(keyword);
        Assert.assertEquals(homePageSearchMenu.getKeywordFieldText(), keyword);
    }

    /**
     * Test to verify ability to type date in date from field
     */
    @Test
    @Description("Test to verify ability to type date in date from field")
    public void testTypeDateFrom() {
        LocalDate date = LocalDate.of(2020, 12,25);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(date);
        Assert.assertEquals(homePageSearchMenu.getDateFromPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    /**
     * Test to verify ability to type date in date to field
     */
    @Test
    @Description("Test to verify ability to type date in date to field")
    public void testTypeDateTo() {
        LocalDate date = LocalDate.of(2020, 12, 26);
        homePageSearchMenu.clickMoreFiltersButton().clearDateTo().typeDateTo(date);
        Assert.assertEquals(homePageSearchMenu.getDateToPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    /**
     * Test to verify ability to type text in hashtag field
     */
    @Test
    @Description("Test to verify ability to type text in hashtag field")
    public void testTypeHashtag() {
        String hashtag = "Mount";
        homePageSearchMenu.clickMoreFiltersButton();
        homePageSearchMenu.typeHashtag(hashtag);
        Assert.assertEquals(homePageSearchMenu.getChosenHashtagText(), hashtag);
    }

    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }
}
