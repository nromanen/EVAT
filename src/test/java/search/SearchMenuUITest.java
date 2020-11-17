package search;
import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.homePageSearch.HomePageSearchMenu;
import pages.homePageSearch.SearchResultPage;
import java.time.LocalDate;

public class SearchMenuUITest extends BaseTest {
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        driver.get(HomePageSearchMenu.URL);
        searchResultPage = new SearchResultPage(driver);
        homePageSearchMenu = new HomePageSearchMenu(driver);
    }

    @Test(description = "CHIS-142")
    public void testTypeKeyword() {
        String keyword = "yes";
        homePageSearchMenu.typeKeyword(keyword);
        Assert.assertEquals(homePageSearchMenu.getKeywordFieldText(), keyword);
    }

    @Test(description = "CHIS-142")
    public void testTypeDateFrom() {
        LocalDate date = LocalDate.of(2020, 12,25);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(date);
        Assert.assertEquals(homePageSearchMenu.getDateFromPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    @Test(description = "CHIS-142")
    public void testTypeDateTo() {
        LocalDate date = LocalDate.of(2020, 12, 26);
        homePageSearchMenu.clickMoreFiltersButton().clearDateTo().typeDateTo(date);
        Assert.assertEquals(homePageSearchMenu.getDateToPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    @Test(description = "CHIS-142")
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
