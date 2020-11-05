package search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.homePageSearch.HomePageSearchMenu;
import pages.homePageSearch.SearchResultPage;
import utility.SetUpDriver;

import java.time.LocalDate;

public class SearchMenuUITest {
    WebDriver driver;
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        driver = SetUpDriver.getDriver();
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        wait = SetUpDriver.getWebDriverWait();
//        WebDriverWait wait = new WebDriverWait(driver, 30);
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        homePageSearchMenu = PageFactory.initElements(driver, HomePageSearchMenu.class);
    }

    @Test
    public void testTypeKeyword() {
        String keyword = "yes";
        homePageSearchMenu.typeKeyword(keyword);
        Assert.assertEquals(homePageSearchMenu.getKeywordFieldText(), keyword);
    }

    @Test
    public void testTypeDateFrom() {
        LocalDate date = LocalDate.of(2020, 12,12);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(date);
        Assert.assertEquals(homePageSearchMenu.getDateFromPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    @Test
    public void testTypeDateTo() {
        LocalDate date = LocalDate.of(2020, 12, 13);
        homePageSearchMenu.clickMoreFiltersButton().clearDateTo().typeDateTo(date);
        Assert.assertEquals(homePageSearchMenu.getDateToPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    @Test
    public void testTypeHashtag() {
        String hashtag = "Mount";
        homePageSearchMenu.clickMoreFiltersButton();
        homePageSearchMenu.typeHashtag(hashtag);
        Assert.assertEquals(homePageSearchMenu.getChosenHashtagText(), hashtag);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
