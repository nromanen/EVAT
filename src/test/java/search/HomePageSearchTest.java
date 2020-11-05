package search;
import jdbc.SearchRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.homePageSearch.*;
import utility.SetUpDriver;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;


public class HomePageSearchTest {
    WebDriver driver;
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;
    WebDriverWait wait;

    @BeforeClass
    public void createHashtagForTests(){
        String hashtagId = "38F785E3-B43E-46FF-3788-08D85413B488";
        String hashtagName = "Zoo";
        SearchRepository.addNewHashtag(hashtagId, hashtagName);
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        wait = new WebDriverWait(driver, 30);
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(SearchResultPage.numberOfEvents));
        homePageSearchMenu = PageFactory.initElements(driver, HomePageSearchMenu.class);
    }

    @Test
    public void searchByKeywordWithOneWordTest() throws SQLException {
        String[] keywords = new String[]{"гриби", "Бали", "Ба", "Kuta"};
        SoftAssert softAssert = new SoftAssert();
        for (String keyword: keywords) {
            homePageSearchMenu.searchByKeyword(keyword);
            softAssert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
            homePageSearchMenu.clickResetButtonForClearResults();
        }
        softAssert.assertAll();
    }
    @Test
    public void checkResetButtonTest() {
        SoftAssert softAssert = new SoftAssert();
        String keyword = "Bali";
        homePageSearchMenu.clickInKeywordField().typeKeyword(keyword);
        homePageSearchMenu.clickResetButton();
        softAssert.assertEquals(homePageSearchMenu.getKeywordFieldText(), "");
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(LocalDate.of(2020, 11, 30)).clickResetButton();
        softAssert.assertEquals(SearchRepository.getLocalDateNow(), homePageSearchMenu.getDateFromPickerText());
        homePageSearchMenu.typeHashtag("Summer").clickResetButton();
        softAssert.assertEquals(homePageSearchMenu.getHashtagFieldText(), "");
        softAssert.assertAll();
    }
    @Test
    public void clearResultsTest() throws SQLException {
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
        LocalDate date1 = LocalDate.of(2020, 11, 14);
        LocalDate date2 = LocalDate.of(2020, 11, 28);
        homePageSearchMenu.searchByTwoDates(date1, date2);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2));
    }
    @Test
    public void searchByOneDateFromTest() throws SQLException {
        LocalDate date = LocalDate.of(2020, 11, 5);
        homePageSearchMenu.searchByDateFrom(date);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithDateFrom(date));
    }
    @Test
    public void searchByOneDateToTest() throws SQLException {
        LocalDate date = LocalDate.of(2020, 11, 30);
        homePageSearchMenu.searchByDateTo(date);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsFromNowTillDateTo(date));
    }
    @Test
    public void searchByOneHashtagTest() throws SQLException {
        String hashtag = "Travel";
        homePageSearchMenu.searchByOneHashtag(hashtag);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByOneHashtag(hashtag));
    }
    @Test
    public void searchByTwoHashtagsTest() throws SQLException {
        String hashtag1 = "Swimming";
        String hashtag2 = "Travel";
        homePageSearchMenu.searchByTwoHashtags(hashtag1, hashtag2);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByTwoHashtags(hashtag1, hashtag2));
    }
    @Test
    public void searchByKeywordWithManyWordsTest() throws SQLException {
        String[] keywords = new String[]{"Qatar Airways", "восени карпатські ліси радують", "замечательный отдых"};
        SoftAssert softAssert = new SoftAssert();
        for (String keyword: keywords) {
            homePageSearchMenu.searchByKeyword(keyword);
            softAssert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
            homePageSearchMenu.clickResetButtonForClearResults();
        }
        softAssert.assertAll();
    }
    @Test
    public void searchByKeywordWithManyWordsInWrongOrderTest() throws SQLException {
        String keyword = "отдых замечательный";
        homePageSearchMenu.typeKeyword(keyword).clickSearchButton();
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
    }
    @Test
    public void searchByDatesForEventsLastingSeveralDaysTest() throws SQLException {
        LocalDate date1 = LocalDate.of(2020, 12, 5);
        LocalDate date2 = LocalDate.of(2020, 12, 10);
        homePageSearchMenu.searchByTwoDates(date1, date2);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2));
    }
    @Test
    public void searchByDatesForEventsLastingSeveralDaysWithLessDaysThenLastsEventTest() throws SQLException {
        LocalDate date1 = LocalDate.of(2020, 12, 5);
        LocalDate date2 = LocalDate.of(2020, 12, 9);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(date1)
                .clearDateTo().typeDateTo(date2).clickSearchButton();
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2));
    }
    @Test
    public void searchByKeywordWithLessThan2SymbolsTest() {
        homePageSearchMenu.typeKeyword("c");
        Assert.assertEquals("Keyword length is too small. Please use keywords at least 3 symbols long!", homePageSearchMenu.getIncorrectKeywordTextTooSmall());
    }
    @Test
    public void searchByKeywordWithMoreThan30SymbolsTest() {
        homePageSearchMenu.typeKeyword("Пропоную разом поїхати позбирати гриби");
        Assert.assertEquals("Keyword length is too long. Please use keyword less than 30 symbols long!", homePageSearchMenu.getIncorrectKeywordTextTooLong());
    }
    @Test
    public void searchByKeywordAndDateTest() throws SQLException {
        String keyword = "Mexico";
        LocalDate date = LocalDate.of(2020, 11, 10);
        homePageSearchMenu.searchByKeywordAndDateFrom(keyword, date);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordWithNInQueryAndDateFrom(keyword, date));
    }
    @Test
    public void searchByKeywordAndHashtagTest() throws SQLException {
        String keyword = "гриби";
        String hashtag = "Mount";
        homePageSearchMenu.searchByKeywordAndHashtag(keyword, hashtag);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByKeywordAndHashtag(keyword, hashtag));
    }
    @Test
    public void searchByDatesAndHashtagTest() throws SQLException {
        LocalDate date1 = LocalDate.of(2020, 12, 1);
        LocalDate date2 = LocalDate.of(2020, 12, 20);
        String hashtag = "Travel";
        homePageSearchMenu.searchByDatesAndHashtag(date1, date2, hashtag);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByDatesAndHashtag(date1, date2, hashtag));
    }
    @Test
    public void searchByKeywordAndDateAndHashtagTest() throws SQLException {
        String keyword = "гриби";
        LocalDate date = LocalDate.of(2020, 11, 1);
        String hashtag = "Mount";
        homePageSearchMenu.searchByKeywordAndDateAndHashtag(keyword, date, hashtag);
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByKeywordWithNInQueryAndDateFromAndHashtag(keyword, date, hashtag));
    }
    @Test
    public void searchByHashtagWithIncorrectValueTest() {
        homePageSearchMenu.clickMoreFiltersButton().typeIncorrectHashtag("1");
        Assert.assertEquals(homePageSearchMenu.getIncorrectHashtagText(), "The filter returned no results");
    }
    @Test
    public void searchByDateWithIncorrectValueTest() {
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(LocalDate.of(2020, 5, 5));
        Assert.assertEquals(homePageSearchMenu.getDateFromPickerText(), SearchRepository.getLocalDateNow());
    }
    @Test
    public void searchByDateWithAnotherFormatTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFromWithAnotherFormat("12/10/20");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "10");
        homePageSearchMenu.clearDateFrom().typeDateFromWithAnotherFormat("12.14.2020");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "14");
        homePageSearchMenu.clearDateFrom().typeDateFromWithAnotherFormat("November 28 2020");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "28");
        softAssert.assertAll();
    }
    @Test
    public void searchByHashtagWithNoEventsTest() throws InterruptedException {
        homePageSearchMenu.clickMoreFiltersButton().typeHashtag("Zoo").clickSearchButton();
        wait.until(ExpectedConditions.visibilityOf(searchResultPage.noResultText));
        Assert.assertEquals(searchResultPage.getNoResultText(), "No Results");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
    @AfterClass
    public void deleteHashtagForTest(){
        String hashtagId = "38F785E3-B43E-46FF-3788-08D85413B488";
        SearchRepository.deleteHashtag(hashtagId);
    }
}
