package search;
import base.BaseTest;
import io.qameta.allure.Description;
import jdbc.SearchRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.search.HomePageSearchMenu;
import pages.search.SearchResultPage;

import java.time.LocalDate;


public class HomePageSearchTest extends BaseTest {
    HomePageSearchMenu homePageSearchMenu;
    SearchResultPage searchResultPage;
    WebDriverWait wait;

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        driver.get(HomePageSearchMenu.URL);
        searchResultPage = new SearchResultPage(driver);
        homePageSearchMenu = new HomePageSearchMenu(driver);
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Test to verify search by keyword with one word
     */
    @Test(description = "CHIS-36")
    @Description(useJavaDoc = true)
    public void searchByKeywordWithOneWordTest(){
        String[] keywords = new String[]{"гриби", "Бали", "Kuta"};
        SoftAssert softAssert = new SoftAssert();
        for (String keyword: keywords) {
            homePageSearchMenu.searchByKeyword(keyword);
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 1));
            softAssert.assertEquals(searchResultPage.getNumberOfEvents(), 1);
            homePageSearchMenu.clickResetButton();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 6));
        }
        softAssert.assertAll();
    }

    /**
     * Test to check reset button
     */
    @Test(description = "CHIS-37")
    @Description(useJavaDoc = true)
    public void checkResetButtonTest(){
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

    /**
     * Test to verify ability to clear search results
     */
    @Test(description = "CHIS-38")
    @Description(useJavaDoc = true)
    public void clearResultsTest(){
        String keyword = "Bali";
        homePageSearchMenu.searchByKeyword(keyword);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 1));
        homePageSearchMenu.clickResetButton();
        Assert.assertNotEquals(searchResultPage.getNumberOfEvents(), 6);
    }

    /**
     * Test to verify search by dates with two correct dates
     */
    @Test(description = "CHIS-53")
    @Description(useJavaDoc = true)
    public void searchByTwoCorrectDatesTest(){
        LocalDate date1 = LocalDate.of(2020, 12, 10);
        LocalDate date2 = LocalDate.of(2020, 12, 28);
        homePageSearchMenu.searchByTwoDates(date1, date2);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 1));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 1);
    }

    /**
     * Test to verify search by date with one date from
     */
    @Test(description = "CHIS-55")
    @Description(useJavaDoc = true)
    public void searchByOneDateFromTest(){
        LocalDate date = LocalDate.of(2020, 11, 30);
        homePageSearchMenu.searchByDateFrom(date);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 3));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 3);
    }

    /**
     * Test to verify search by date with one date to
     */
    @Test(description = "CHIS-57")
    @Description(useJavaDoc = true)
    public void searchByOneDateToTest(){
        LocalDate date = LocalDate.of(2020, 11, 30);
        homePageSearchMenu.searchByDateTo(date);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 3));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 3);
    }

    /**
     * Test to verify search by one hashtag
     */
    @Test(description = "CHIS-58")
    @Description(useJavaDoc = true)
    public void searchByOneHashtagTest(){
        String hashtag = "Travel";
        homePageSearchMenu.searchByOneHashtag(hashtag);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 3));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 3);
    }

    /**
     * Test to verify search by two hashtags
     */
    @Test(description = "CHIS-59")
    @Description(useJavaDoc = true)
    public void searchByTwoHashtagsTest(){
        String hashtag1 = "Swimming";
        String hashtag2 = "Travel";
        homePageSearchMenu.searchByTwoHashtags(hashtag1, hashtag2);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 3));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 3);
    }

    /**
     * Test to verify search by keyword with many words in correct order
     */
    @Test(description = "CHIS-95")
    @Description(useJavaDoc = true)
    public void searchByKeywordWithManyWordsTest(){
        String[] keywords = new String[]{"Qatar Airways", "восени карпатські ліси радують", "замечательный отдых"};
        SoftAssert softAssert = new SoftAssert();
        for (String keyword: keywords) {
            homePageSearchMenu.searchByKeyword(keyword);
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 1));
            softAssert.assertEquals(searchResultPage.getNumberOfEvents(), 1);
            homePageSearchMenu.clickResetButton();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 6));
        }
        softAssert.assertAll();
    }

    /**
     * Test to verify search by keyword with many words in incorrect order
     */
    @Test(description = "CHIS-140")
    @Description(useJavaDoc = true)
    public void searchByKeywordWithManyWordsInWrongOrderTest(){
        String keyword = "отдых замечательный";
        homePageSearchMenu.typeKeyword(keyword).clickSearchButton();
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 0);
    }

    /**
     * Test to verify search by dates for events lasting several days
     */
    @Test(description = "CHIS-96")
    @Description(useJavaDoc = true)
    public void searchByDatesForEventsLastingSeveralDaysTest(){
        LocalDate date1 = LocalDate.of(2020, 12, 5);
        LocalDate date2 = LocalDate.of(2020, 12, 10);
        homePageSearchMenu.searchByTwoDates(date1, date2);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 1));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 1);
    }

    /**
     * Test to verify search by dates for events lasting several days when search date interval less
     * then date interval of event
     */
    @Test(description = "CHIS-141")
    @Description(useJavaDoc = true)
    public void searchByDatesForEventsLastingSeveralDaysWithLessDaysThenLastsEventTest(){
        LocalDate date1 = LocalDate.of(2020, 12, 5);
        LocalDate date2 = LocalDate.of(2020, 12, 9);
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(date1)
                .clearDateTo().typeDateTo(date2).clickSearchButton();
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 0);
    }

    /**
     * Test to verify appearance of error message when user search event by keyword less than 2 symbols
     */
    @Test(description = "CHIS-97")
    @Description(useJavaDoc = true)
    public void searchByKeywordWithLessThan2SymbolsTest() {
        homePageSearchMenu.typeKeyword("c");
        Assert.assertEquals("Keyword length is too small. Please use keywords at least 3 symbols long!", homePageSearchMenu.getIncorrectKeywordTextTooSmall());
    }

    /**
     * Test to verify appearance of error message when user search event by keyword more than 30 symbols
     */
    @Test(description = "CHIS-98")
    @Description(useJavaDoc = true)
    public void searchByKeywordWithMoreThan30SymbolsTest() {
        homePageSearchMenu.typeKeyword("Пропоную разом поїхати позбирати гриби");
        Assert.assertEquals("Keyword length is too long. Please use keyword less than 30 symbols long!", homePageSearchMenu.getIncorrectKeywordTextTooLong());
    }

    /**
     * Test to verify search by keyword and date from
     */
    @Test(description = "CHIS-105")
    @Description(useJavaDoc = true)
    public void searchByKeywordAndDateTest(){
        String keyword = "Mexico";
        LocalDate date = LocalDate.of(2020, 11, 30);
        homePageSearchMenu.searchByKeywordAndDateFrom(keyword, date);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 1));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 1);
    }

    /**
     * Test to verify search by keyword and hashtag
     */
    @Test(description = "CHIS-108")
    @Description(useJavaDoc = true)
    public void searchByKeywordAndHashtagTest(){
        String keyword = "гриби";
        String hashtag = "Mount";
        homePageSearchMenu.searchByKeywordAndHashtag(keyword, hashtag);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 1));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 1);
    }

    /**
     * Test to verify search by dates and hashtag
     */
    @Test(description = "CHIS-99")
    @Description(useJavaDoc = true)
    public void searchByDatesAndHashtagTest(){
        LocalDate date1 = LocalDate.of(2020, 12, 1);
        LocalDate date2 = LocalDate.of(2020, 12, 20);
        String hashtag = "Travel";
        homePageSearchMenu.searchByDatesAndHashtag(date1, date2, hashtag);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 2));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 2);
    }

    /**
     * Test to verify search by keyword, date and hashtag
     */
    @Test(description = "CHIS-109")
    @Description(useJavaDoc = true)
    public void searchByKeywordAndDateAndHashtagTest(){
        String keyword = "гриби";
        LocalDate date = LocalDate.of(2020, 11, 27);
        String hashtag = "Mount";
        homePageSearchMenu.searchByKeywordAndDateAndHashtag(keyword, date, hashtag);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiCardHeader-root"), 1));
        Assert.assertEquals(searchResultPage.getNumberOfEvents(), 1);
    }

    /**
     * Test to verify search by hashtag with incorrect value
     */
    @Test(description = "CHIS-110")
    @Description(useJavaDoc = true)
    public void searchByHashtagWithIncorrectValueTest() {
        homePageSearchMenu.clickMoreFiltersButton().typeIncorrectHashtag("1");
        Assert.assertEquals(homePageSearchMenu.getIncorrectHashtagText(), "The filter returned no results");
    }

    /**
     * Test to verify search by date from with incorrect value
     */
    @Test(description = "CHIS-111")
    @Description(useJavaDoc = true)
    public void searchByDateWithIncorrectValueTest() {
        homePageSearchMenu.clickMoreFiltersButton().clearDateFrom().typeDateFrom(LocalDate.of(2020, 5, 5));
        Assert.assertEquals(homePageSearchMenu.getDateFromPickerText(), SearchRepository.getLocalDateNow());
    }

    /**
     * Test to verify search by date from with another format of date
     */
    @Test(description = "CHIS-112")
    @Description(useJavaDoc = true)
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

    /**
     * Test to verify search by hashtag when there are no events on this hashtag
     */
    @Test(description = "CHIS-113")
    @Description(useJavaDoc = true)
    public void searchByHashtagWithNoEventsTest() {
        homePageSearchMenu.clickMoreFiltersButton().typeHashtag("Gaming").clickSearchButton();
        wait.until(ExpectedConditions.visibilityOf(searchResultPage.noResultText));
        Assert.assertEquals(searchResultPage.getNoResultText(), "No Results");
    }

    @Override
    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }

}
