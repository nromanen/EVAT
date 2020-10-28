package search;
import jdbc.SearchRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.homePageSearch.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class HomePageSearchTest {
    WebDriver driver;
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;
    WebDriverWait wait;


    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
    }

//    @Test
//    public void searchByKeywordWithOneWordTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        String[] keywords = new String[]{"гриби", "Бали", "Ба", "Kuta"};
//        SoftAssert softAssert = new SoftAssert();
//        for (String keyword: keywords) {
//            homePageSearchMenu.searchByKeyword(keyword);
//            softAssert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
//            homePageSearchMenu.clickResetButtonForClearResults();
//        }
//        softAssert.assertAll();
//    }
//    @Test
//    public void checkResetButtonTest() {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        SoftAssert softAssert = new SoftAssert();
//        String keyword = "Bali";
//        homePageSearchMenu.clickInKeywordField().typeKeyword(keyword);
//        homePageSearchMenu.clickResetButton();
//        softAssert.assertEquals(homePageSearchMenu.getKeywordFieldText(), "");
//        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(LocalDate.of(2020, 11, 30)).clickResetButton();
//        softAssert.assertEquals(SearchRepository.getLocalDateNow(), homePageSearchMenu.getDateFromPickerText());
//        homePageSearchMenu.typeHashtag("Summer").clickResetButton();
//        softAssert.assertEquals(homePageSearchMenu.getHashtagFieldText(), "");
//        softAssert.assertAll();
//    }
    @Test
    public void clearResultsTest() throws SQLException {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        searchResultPage = new SearchResultPage(driver);
        String keyword = "Bali";
        homePageSearchMenu.searchByKeyword(keyword);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
        homePageSearchMenu.clickResetButton();
        softAssert.assertNotEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
        softAssert.assertAll();
    }
    @Test
    public void searchByTwoCorrectDatesTest() throws SQLException {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        searchResultPage = new SearchResultPage(driver);
        LocalDate date1 = LocalDate.of(2020, 11, 14);
        LocalDate date2 = LocalDate.of(2020, 11, 28);
        homePageSearchMenu.searchByTwoDates(date1, date2);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2));
    }
//    @Test
//    public void searchByOneDateFromTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        LocalDate date = LocalDate.of(2020, 11, 5);
//        homePageSearchMenu.searchByDateFrom(date);
//        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithDateFrom(date));
//    }
    @Test
    public void searchByOneDateToTest() throws SQLException {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        searchResultPage = new SearchResultPage(driver);
        LocalDate date = LocalDate.of(2020, 11, 30);
        homePageSearchMenu.searchByDateTo(date);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsFromNowTillDateTo(date));
    }
//    @Test
//    public void searchByOneHashtagTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        String hashtag = "Sea";
//        homePageSearchMenu.searchByOneHashtag(hashtag);
//        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultPage.numberOfEvents));
//        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByOneHashtag(hashtag));
//    }
//    @Test
//    public void searchByTwoHashtagsTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        String hashtag1 = "Sea";
//        String hashtag2 = "Summer";
//        homePageSearchMenu.searchByTwoHashtags(hashtag1, hashtag2);
//        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultPage.numberOfEvents));
//        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByTwoHashtags(hashtag1, hashtag2));
//    }
//    @Test
//    public void searchByKeywordWithManyWordsTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        String[] keywords = new String[]{"Qatar Airways", "восени карпатські ліси радують", "замечательный отдых"};
//        SoftAssert softAssert = new SoftAssert();
//        for (String keyword: keywords) {
//            homePageSearchMenu.searchByKeyword(keyword);
//            wait.until(ExpectedConditions.visibilityOfAllElements(searchResultPage.numberOfEvents));
//            softAssert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
//            homePageSearchMenu.clickResetButton();
//            wait.until(ExpectedConditions.visibilityOfAllElements(searchResultPage.numberOfEvents));
//        }
//        softAssert.assertAll();
//    }
//    @Test
//    public void searchByKeywordWithManyWordsInWrongOrderTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        String keyword = "отдых замечательный";
//        homePageSearchMenu.searchByKeyword(keyword);
//        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
//    }
    @Test
    public void searchByDatesForEventsLastingSeveralDaysTest() throws SQLException {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        searchResultPage = new SearchResultPage(driver);
        LocalDate date1 = LocalDate.of(2020, 12, 5);
        LocalDate date2 = LocalDate.of(2020, 12, 10);
        homePageSearchMenu.searchByTwoDates(date1, date2);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2));
    }
//    @Test
//    public void searchByDatesForEventsLastingSeveralDaysWithLessDaysThenLastsEventTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        LocalDate date1 = LocalDate.of(2020, 12, 5);
//        LocalDate date3 = LocalDate.of(2020, 12, 9);
//        homePageSearchMenu.searchByTwoDates(date1, date3);
//        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date3));
//    }
    @Test
    public void searchByKeywordWithLessThan2SymbolsTest() {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        homePageSearchMenu.typeKeyword("c");
        Assert.assertEquals("Keyword length is too small. Please use keywords at least 3 symbols long!", homePageSearchMenu.getIncorrectKeywordTextTooSmall());
    }
    @Test
    public void searchByKeywordWithMoreThan30SymbolsTest() {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        homePageSearchMenu.typeKeyword("Пропоную разом поїхати позбирати гриби");
        Assert.assertEquals("Keyword length is too long. Please use keyword less than 30 symbols long!", homePageSearchMenu.getIncorrectKeywordTextTooLong());
    }
    @Test
    public void searchByKeywordAndDateTest() throws SQLException {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        searchResultPage = new SearchResultPage(driver);
        String keyword = "Mexico";
        LocalDate date = LocalDate.of(2020, 11, 10);
        homePageSearchMenu.searchByKeywordAndDateFrom(keyword, date);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordWithNInQueryAndDateFrom(keyword, date));
    }
//    @Test
//    public void searchByKeywordAndHashtagTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        String keyword = "гриби";
//        String hashtag = "Mount";
//        homePageSearchMenu.searchByKeywordAndHashtag(keyword, hashtag);
//        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultPage.numberOfEvents));
//        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByKeywordAndHashtag(keyword, hashtag));
//    }
//    @Test
//    public void searchByDatesAndHashtagTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        LocalDate date1 = LocalDate.of(2020, 12, 1);
//        LocalDate date2 = LocalDate.of(2020, 12, 20);
//        String hashtag = "Sea";
//        homePageSearchMenu.searchByDatesAndHashtag(date1, date2, hashtag);
//        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultPage.numberOfEvents));
//        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByDatesAndHashtag(date1, date2, hashtag));
//    }
//    @Test
//    public void searchByKeywordAndAndDateAndHashtagTest() throws SQLException {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        String keyword = "гриби";
//        LocalDate date = LocalDate.of(2020, 11, 1);
//        String hashtag = "Mount";
//        homePageSearchMenu.searchByKeywordAndDateAndHashtag(keyword, date, hashtag);
//        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultPage.numberOfEvents));
//        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByKeywordWithNInQueryAndDateFromAndHashtag(keyword, date, hashtag));
//    }
//    @Test
//    public void searchByHashtagWithIncorrectValueTest() {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        homePageSearchMenu.clickMoreFiltersButton().typeIncorrectHashtag("1");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rw-list-empty")));
//        Assert.assertEquals("The filter returned no results", homePageSearchMenu.getIncorrectHashtagText());
//    }
    @Test
    public void searchByDateWithIncorrectValueTest() {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(LocalDate.of(2020, 5, 5));
        Assert.assertEquals(SearchRepository.getLocalDateNow(), homePageSearchMenu.getDateFromPickerText());
    }
    @Test
    public void searchByDateWithAnotherFormatTest() throws InterruptedException {
        homePageSearchMenu = new HomePageSearchMenu(driver);
        SoftAssert softAssert = new SoftAssert();
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFromWithAnotherFormat("12/10/20");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "10");
        homePageSearchMenu.clearDateFrom().typeDateFromWithAnotherFormat("12.14.2020");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "14");
        homePageSearchMenu.clearDateFrom().typeDateFromWithAnotherFormat("November 28 2020");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "28");
        softAssert.assertAll();
    }
//    @Test
//    public void searchByHashtagWithNoEventsTest() {
//        homePageSearchMenu = new HomePageSearchMenu(driver);
//        searchResultPage = new SearchResultPage(driver);
//        homePageSearchMenu.searchByOneHashtag("Golf");
//        Assert.assertEquals(searchResultPage.getNoResultText(), "No Results");
//    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
