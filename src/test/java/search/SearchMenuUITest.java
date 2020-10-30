package search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.homePageSearch.HomePageSearchMenu;
import pages.homePageSearch.SearchResultPage;
import java.time.LocalDate;

public class SearchMenuUITest {
    WebDriver driver;
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        searchResultPage = new SearchResultPage(driver);
    }
//    @Test
//    public void testClickMoreFiltersButton() {
//
//    }
//
//    @Test
//    public void testClickSearchButton() {
//    }
//
//    @Test
//    public void testClickLessButton() {
//    }
//
//    @Test
//    public void testClickResetButton() {
//    }
//
//    @Test
//    public void testClickHashtagButton() {
//    }
//
//    @Test
//    public void testClickHashtagCloseButton() {
//    }

    @Test
    public void testTypeKeyword() {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        String keyword = "yes";
        homePageSearchMenu.typeKeyword(keyword);
        Assert.assertEquals(homePageSearchMenu.getKeywordFieldText(), keyword);
    }

    @Test
    public void testTypeDateFrom() {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        LocalDate date = LocalDate.of(2020, 12,12);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(date);
        Assert.assertEquals(homePageSearchMenu.getDateFromPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    @Test
    public void testTypeDateTo() {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        LocalDate date = LocalDate.of(2020, 12,12);
        homePageSearchMenu.clickMoreFiltersButton().clearDateTo().typeDateTo(date);
        Assert.assertEquals(homePageSearchMenu.getDateToPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    @Test
    public void testTypeHashtag() {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        String hashtag = "Summer";
        homePageSearchMenu.clickMoreFiltersButton().typeHashtag(hashtag);
        Assert.assertEquals(homePageSearchMenu.getChosenHashtagText(), hashtag);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
