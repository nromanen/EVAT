package search;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.homePageSearch.HomePageSearchMenu;
import pages.homePageSearch.SearchResultPage;
import utility.SetUpDriver;
import java.time.LocalDate;

public class SearchMenuUITest {
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;
    SetUpDriver setUpDriver;


    @BeforeMethod
    public void setUp(){
        setUpDriver = new SetUpDriver();
        WebDriver driver = setUpDriver.getDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        homePageSearchMenu = PageFactory.initElements(driver, HomePageSearchMenu.class);
    }

    @Test(description = "CHIS-142")
    public void testTypeKeyword() {
        String keyword = "yes";
        homePageSearchMenu.typeKeyword(keyword);
        Assert.assertEquals(homePageSearchMenu.getKeywordFieldText(), keyword);
    }

    @Test(description = "CHIS-142")
    public void testTypeDateFrom() {
        LocalDate date = LocalDate.of(2020, 12,12);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(date);
        Assert.assertEquals(homePageSearchMenu.getDateFromPickerText(), date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
    }

    @Test(description = "CHIS-142")
    public void testTypeDateTo() {
        LocalDate date = LocalDate.of(2020, 12, 13);
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

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        setUpDriver.driverQuit();
    }
}
