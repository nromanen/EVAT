package search;
import jdbc.SearchRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


public class HomePageSearchTest {
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;
    SetUpDriver setUpDriver;
    WebDriverWait wait;
    WebDriver driver;

    @BeforeClass
    public void createHashtagForTests(){
        String hashtagId = "38F785E3-B43E-46FF-3788-08D85413B488";
        String hashtagName = "Zoo";
        SearchRepository.addNewHashtag(hashtagId, hashtagName);
    }

    @BeforeMethod
    public void setUp() throws SQLException {
        setUpDriver = new SetUpDriver();
        driver = setUpDriver.getDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
        wait = setUpDriver.getWebDriverWait();
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#notfound > div > div > div > div"))));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfAllEvents()));
        homePageSearchMenu = PageFactory.initElements(driver, HomePageSearchMenu.class);
    }

    @Test(description = "CHIS-36")
    public void searchByKeywordWithOneWordTest() throws SQLException {
        String[] keywords = new String[]{"гриби", "Бали", "Ба", "Kuta"};
        SoftAssert softAssert = new SoftAssert();
        for (String keyword: keywords) {
            homePageSearchMenu.searchByKeyword(keyword);
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword)));
            softAssert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
            homePageSearchMenu.clickResetButton();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfAllEvents()));
        }
        softAssert.assertAll();
    }
    @Test(description = "CHIS-37")
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
    @Test(description = "CHIS-38")
    public void clearResultsTest() throws SQLException {
        String keyword = "Bali";
        homePageSearchMenu.searchByKeyword(keyword);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword)));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
        homePageSearchMenu.clickResetButton();
        softAssert.assertNotEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
        softAssert.assertAll();
    }
    @Test(description = "CHIS-53")
    public void searchByTwoCorrectDatesTest() throws SQLException {
        LocalDate date1 = LocalDate.of(2020, 11, 14);
        LocalDate date2 = LocalDate.of(2020, 11, 28);
        homePageSearchMenu.searchByTwoDates(date1, date2);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2));
    }
    @Test(description = "CHIS-55")
    public void searchByOneDateFromTest() throws SQLException {
        LocalDate date = LocalDate.of(2020, 11, 5);
        homePageSearchMenu.searchByDateFrom(date);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsWithDateFrom(date)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithDateFrom(date));
    }
    @Test(description = "CHIS-57")
    public void searchByOneDateToTest() throws SQLException {
        LocalDate date = LocalDate.of(2020, 11, 30);
        homePageSearchMenu.searchByDateTo(date);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsFromNowTillDateTo(date)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsFromNowTillDateTo(date));
    }
    @Test(description = "CHIS-58")
    public void searchByOneHashtagTest() throws SQLException {
        String hashtag = "Travel";
        homePageSearchMenu.searchByOneHashtag(hashtag);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsByOneHashtag(hashtag)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByOneHashtag(hashtag));
    }
    @Test(description = "CHIS-59")
    public void searchByTwoHashtagsTest() throws SQLException {
        String hashtag1 = "Swimming";
        String hashtag2 = "Travel";
        homePageSearchMenu.searchByTwoHashtags(hashtag1, hashtag2);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsByTwoHashtags(hashtag1, hashtag2)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByTwoHashtags(hashtag1, hashtag2));
    }
    @Test(description = "CHIS-95")
    public void searchByKeywordWithManyWordsTest() throws SQLException {
        String[] keywords = new String[]{"Qatar Airways", "восени карпатські ліси радують", "замечательный отдых"};
        SoftAssert softAssert = new SoftAssert();
        for (String keyword: keywords) {
            homePageSearchMenu.searchByKeyword(keyword);
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword)));
            softAssert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
            homePageSearchMenu.clickResetButton();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfAllEvents()));
        }
        softAssert.assertAll();
    }
    @Test(description = "CHIS-140")
    public void searchByKeywordWithManyWordsInWrongOrderTest() throws SQLException {
        String keyword = "отдых замечательный";
        homePageSearchMenu.typeKeyword(keyword).clickSearchButton();
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(keyword));
    }
    @Test(description = "CHIS-96")
    public void searchByDatesForEventsLastingSeveralDaysTest() throws SQLException {
        LocalDate date1 = LocalDate.of(2020, 12, 5);
        LocalDate date2 = LocalDate.of(2020, 12, 10);
        homePageSearchMenu.searchByTwoDates(date1, date2);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2));
    }
    @Test(description = "CHIS-141")
    public void searchByDatesForEventsLastingSeveralDaysWithLessDaysThenLastsEventTest() throws SQLException {
        LocalDate date1 = LocalDate.of(2020, 12, 5);
        LocalDate date2 = LocalDate.of(2020, 12, 9);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(date1)
                .clearDateTo().typeDateTo(date2).clickSearchButton();
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithTwoDates(date1, date2));
    }
    @Test(description = "CHIS-97")
    public void searchByKeywordWithLessThan2SymbolsTest() {
        homePageSearchMenu.typeKeyword("c");
        Assert.assertEquals("Keyword length is too small. Please use keywords at least 3 symbols long!", homePageSearchMenu.getIncorrectKeywordTextTooSmall());
    }
    @Test(description = "CHIS-98")
    public void searchByKeywordWithMoreThan30SymbolsTest() {
        homePageSearchMenu.typeKeyword("Пропоную разом поїхати позбирати гриби");
        Assert.assertEquals("Keyword length is too long. Please use keyword less than 30 symbols long!", homePageSearchMenu.getIncorrectKeywordTextTooLong());
    }
    @Test(description = "CHIS-105")
    public void searchByKeywordAndDateTest() throws SQLException {
        String keyword = "Mexico";
        LocalDate date = LocalDate.of(2020, 11, 10);
        homePageSearchMenu.searchByKeywordAndDateFrom(keyword, date);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsWithKeywordWithNInQueryAndDateFrom(keyword, date)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsWithKeywordWithNInQueryAndDateFrom(keyword, date));
    }
    @Test(description = "CHIS-108")
    public void searchByKeywordAndHashtagTest() throws SQLException {
        String keyword = "гриби";
        String hashtag = "Mount";
        homePageSearchMenu.searchByKeywordAndHashtag(keyword, hashtag);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsByKeywordAndHashtag(keyword, hashtag)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByKeywordAndHashtag(keyword, hashtag));
    }
    @Test(description = "CHIS-99")
    public void searchByDatesAndHashtagTest() throws SQLException {
        LocalDate date1 = LocalDate.of(2020, 12, 1);
        LocalDate date2 = LocalDate.of(2020, 12, 20);
        String hashtag = "Travel";
        homePageSearchMenu.searchByDatesAndHashtag(date1, date2, hashtag);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsByDatesAndHashtag(date1, date2, hashtag)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByDatesAndHashtag(date1, date2, hashtag));
    }
    @Test(description = "CHIS-109")
    public void searchByKeywordAndDateAndHashtagTest() throws SQLException {
        String keyword = "гриби";
        LocalDate date = LocalDate.of(2020, 11, 1);
        String hashtag = "Mount";
        homePageSearchMenu.searchByKeywordAndDateAndHashtag(keyword, date, hashtag);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), SearchRepository.getNumberOfEventsByKeywordWithNInQueryAndDateFromAndHashtag(keyword, date, hashtag)));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), SearchRepository.getNumberOfEventsByKeywordWithNInQueryAndDateFromAndHashtag(keyword, date, hashtag));
    }
    @Test(description = "CHIS-110")
    public void searchByHashtagWithIncorrectValueTest() {
        homePageSearchMenu.clickMoreFiltersButton().typeIncorrectHashtag("1");
        Assert.assertEquals(homePageSearchMenu.getIncorrectHashtagText(), "The filter returned no results");
    }
    @Test(description = "CHIS-111")
    public void searchByDateWithIncorrectValueTest() {
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(LocalDate.of(2020, 5, 5));
        Assert.assertEquals(homePageSearchMenu.getDateFromPickerText(), SearchRepository.getLocalDateNow());
    }
    @Test(description = "CHIS-112")
    public void searchByDateWithAnotherFormatTest(){
        SoftAssert softAssert = new SoftAssert();
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFromWithAnotherFormat("12/10/20");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "10");
        homePageSearchMenu.clearDateFrom().typeDateFromWithAnotherFormat("12.14.2020");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "14");
        homePageSearchMenu.clearDateFrom().typeDateFromWithAnotherFormat("November 28 2020");
        softAssert.assertEquals(homePageSearchMenu.getSelectedDayInCalendarValue(), "28");
        softAssert.assertAll();
    }
    @Test(description = "CHIS-113")
    public void searchByHashtagWithNoEventsTest() {
        homePageSearchMenu.clickMoreFiltersButton().typeHashtag("Zoo").clickSearchButton();
        WebDriverWait wait = setUpDriver.getWebDriverWait();
        wait.until(ExpectedConditions.visibilityOf(searchResultPage.noResultText));
        Assert.assertEquals(searchResultPage.getNoResultText(), "No Results");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        setUpDriver.driverQuit();
    }

    @AfterClass
    public void deleteHashtagForTest(){
        String hashtagId = "38F785E3-B43E-46FF-3788-08D85413B488";
        SearchRepository.deleteHashtag(hashtagId);
    }
}
