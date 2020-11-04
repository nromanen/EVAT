package profile.add_event;

import jdbc.EventsRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.profile.EventMenu;
import pages.profile.events_menu_pages.AddEventPage;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import profile.SetUpProfile;

import java.util.Properties;

import static org.testng.Assert.*;

public class AddEventTest {

    private static AddEventPage addEventPage;
    private static EventMenu eventMenu;
    private static Properties prop;
    private static WebDriverWait webDriverWait;

    @BeforeClass
    public static void beforeClass() {
        new SetUpProfile();
        SetUpProfile.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#full-width-tab-4")));
        eventMenu=new EventMenu(SetUpProfile.getDriver());
        prop=SetUpProfile.getProp();
        webDriverWait=SetUpProfile.getWebDriverWait();
        try (InputStream input = new FileInputStream("src\\test\\resources\\forProfile\\testDataAddEvent.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void clearPageAddEvent(){
        eventMenu.clickFutureEvents();
        eventMenu.clickAddEvent();
        addEventPage=new AddEventPage(SetUpProfile.getDriver(),webDriverWait);
    }


    @DataProvider
    public Object[][] providerSaveCorrect(){
        List<String> hashtags= Arrays.asList(prop.getProperty("correctHashtags").split(","));
        return new Object[][]{{prop.getProperty("correctPhoto"),prop.getProperty("correctTitle"),
                prop.getProperty("correctParticipants"), prop.getProperty("correctDateFrom"),
                prop.getProperty("correctDateTo"),prop.getProperty("correctDescription"),
                hashtags,prop.getProperty("correctCountry"),prop.getProperty("correctCity")}};
    }


    @Test(dataProvider = "providerSaveCorrect")
    public void testSaveCorrect(String photo,String title, String participants, String dateFrom, String dateTo,
                                     String description,List<String> hashtagsToEnter,String country,String city)throws NoSuchElementException {
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.clickSave();
        System.out.println("Hello");
        assertTrue(addEventPage.getCreatedEventMessage().isDisplayed());
        assertEquals(EventsRepository.getAmountOfEvents(),amountOfEventsBefore+1);
    }

    @Test(dependsOnMethods = "testSaveCorrect")
    public void testEmptyPage(){
        assertTrue(addEventPage.isPageEmpty());
    }


    @DataProvider
    public Object[][] providerSaveWithoutImage(){
        List<String> hashtags= Arrays.asList(prop.getProperty("correctHashtags").split(","));
        return new Object[][]{{prop.getProperty("correctTitle"),prop.getProperty("correctParticipants"),
                prop.getProperty("correctDateFrom"), prop.getProperty("correctDateTo"),
                prop.getProperty("correctDescription"), hashtags,
                prop.getProperty("correctCountry"),prop.getProperty("correctCity")}};
    }


    @Test(dataProvider = "providerSaveWithoutImage")
    public void negativeSaveWithoutImage(String title, String participants, String dateFrom, String dateTo,
                                    String description,List<String> hashtagsToEnter,String country,String city){
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.clickSave();
        assertTrue(addEventPage.getRequiredImage().isDisplayed());
        assertEquals(EventsRepository.getAmountOfEvents(),amountOfEventsBefore);
    }

    @Test(expectedExceptions=NoSuchElementException.class, dependsOnMethods = "negativeSaveWithoutImage")
    public void notAppearCreatedEventMessageImage(){
        assertFalse(addEventPage.getCreatedEventMessage().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeSaveWithoutImage")
    public void testNotEmptyPageImage(){
        assertFalse(addEventPage.isPageEmpty());
    }

    @DataProvider
    public Object[][] providerSaveWithoutTitle(){
        List<String> hashtags= Arrays.asList(prop.getProperty("correctHashtags").split(","));
        return new Object[][]{{prop.getProperty("correctPhoto"),
                prop.getProperty("tooManyParticipants"), prop.getProperty("correctDateFrom"),
                prop.getProperty("correctDateTo"),prop.getProperty("correctDescription"),
                hashtags,prop.getProperty("correctCountry"),prop.getProperty("correctCity")}};
    }


    @Test(dataProvider = "providerSaveWithoutTitle")
    public void negativeSaveWithoutTitle(String photo, String participants, String dateFrom, String dateTo,
                                String description,List<String> hashtagsToEnter,String country,String city)throws NoSuchElementException {
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.clickSave();
        assertTrue(addEventPage.getRequiredTitle().isDisplayed());
        assertEquals(EventsRepository.getAmountOfEvents(),amountOfEventsBefore);
    }

    @Test(expectedExceptions=NoSuchElementException.class, dependsOnMethods = "negativeSaveWithoutTitle")
    public void notAppearCreatedEventMessageTitle(){
        assertFalse(addEventPage.getCreatedEventMessage().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeSaveWithoutTitle")
    public void testNotEmptyPageTitle(){
        assertFalse(addEventPage.isPageEmpty());
    }

    @DataProvider
    public Object[][] providerSaveWithoutDescription(){
        List<String> hashtags= Arrays.asList(prop.getProperty("correctHashtags").split(","));
        return new Object[][]{{prop.getProperty("correctPhoto"),prop.getProperty("correctTitle"),
                prop.getProperty("correctParticipants"), prop.getProperty("correctDateFrom"),
                prop.getProperty("correctDateTo"),
                hashtags,prop.getProperty("correctCountry"),prop.getProperty("correctCity")}};
    }


    @Test(dataProvider = "providerSaveWithoutDescription")
    public void negativeSaveWithoutDescription(String photo,String title, String participants, String dateFrom, String dateTo,
                                List<String> hashtagsToEnter,String country,String city)throws NoSuchElementException {
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.clickSave();
        assertTrue(addEventPage.getRequiredDescription().isDisplayed());
        assertEquals(EventsRepository.getAmountOfEvents(),amountOfEventsBefore);
    }

    @Test(expectedExceptions=NoSuchElementException.class, dependsOnMethods = "negativeSaveWithoutDescription")
    public void notAppearCreatedEventMessageDescription(){
        assertFalse(addEventPage.getCreatedEventMessage().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeSaveWithoutDescription")
    public void testNotEmptyPageDescription(){
        assertFalse(addEventPage.isPageEmpty());
    }

    @DataProvider
    public Object[][] providerSaveWithoutHashtags(){
        return new Object[][]{{prop.getProperty("correctPhoto"),prop.getProperty("correctTitle"),
                prop.getProperty("correctParticipants"), prop.getProperty("correctDateFrom"),
                prop.getProperty("correctDateTo"),prop.getProperty("correctDescription"),
                prop.getProperty("correctCountry"),prop.getProperty("correctCity")}};
    }


    @Test(dataProvider = "providerSaveWithoutHashtags")
    public void negativeSaveWithoutHashtags(String photo,String title, String participants, String dateFrom, String dateTo,
                                String description,String country,String city)throws NoSuchElementException {
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.clickSave();
        assertTrue(addEventPage.getRequiredHashtags().isDisplayed());
        assertEquals(EventsRepository.getAmountOfEvents(),amountOfEventsBefore);
    }

    @Test(expectedExceptions=NoSuchElementException.class, dependsOnMethods = "negativeSaveWithoutHashtags")
    public void notAppearCreatedEventMessageHashtags(){
        assertFalse(addEventPage.getCreatedEventMessage().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeSaveWithoutHashtags")
    public void testNotEmptyPageHashtags(){
        assertFalse(addEventPage.isPageEmpty());
    }

    @DataProvider
    public Object[][] providerSaveWithoutCountry(){
        List<String> hashtags= Arrays.asList(prop.getProperty("correctHashtags").split(","));
        return new Object[][]{{prop.getProperty("correctPhoto"),prop.getProperty("correctTitle"),
                prop.getProperty("correctParticipants"), prop.getProperty("correctDateFrom"),
                prop.getProperty("correctDateTo"),prop.getProperty("correctDescription"),
                hashtags}};
    }

    @Test(dataProvider = "providerSaveWithoutCountry")
    public void negativeSaveWithoutCountry(String photo,String title, String participants, String dateFrom, String dateTo,
                                String description,List<String> hashtagsToEnter)throws NoSuchElementException {
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.clickSave();
        assertTrue(addEventPage.getRequiredCountry().isDisplayed());
        assertEquals(EventsRepository.getAmountOfEvents(),amountOfEventsBefore);
    }

    @Test(expectedExceptions=NoSuchElementException.class, dependsOnMethods = "negativeSaveWithoutCountry")
    public void notAppearCreatedEventMessageCountry(){
        assertFalse(addEventPage.getCreatedEventMessage().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeSaveWithoutCountry")
    public void testNotEmptyPageCountry(){
        assertFalse(addEventPage.isPageEmpty());
    }

    @DataProvider
    public Object[][] providerSaveWithoutCity(){
        List<String> hashtags= Arrays.asList(prop.getProperty("correctHashtags").split(","));
        return new Object[][]{{prop.getProperty("correctPhoto"),prop.getProperty("correctTitle"),
                prop.getProperty("correctParticipants"), prop.getProperty("correctDateFrom"),
                prop.getProperty("correctDateTo"),prop.getProperty("correctDescription"),
                hashtags,prop.getProperty("correctCountry")}};
    }

    @Test(dataProvider = "providerSaveWithoutCity")
    public void negativeSaveWithoutCity(String photo,String title, String participants, String dateFrom, String dateTo,
                                           String description,List<String> hashtagsToEnter,String country)throws NoSuchElementException {
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.inputCountry(country);
        addEventPage.clickSave();
        assertTrue(addEventPage.getRequiredCity().isDisplayed());
        assertEquals(EventsRepository.getAmountOfEvents(),amountOfEventsBefore);
    }

    @Test(expectedExceptions=NoSuchElementException.class, dependsOnMethods = "negativeSaveWithoutCity")
    public void notAppearCreatedEventMessageCity(){
        assertFalse(addEventPage.getCreatedEventMessage().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeSaveWithoutCity")
    public void testNotEmptyPageCity(){
        assertFalse(addEventPage.isPageEmpty());
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    public void testIsPageEmpty() {
        clearPageAddEvent();
        assertTrue(addEventPage.isPageEmpty());
    }


    @Test(dataProvider = "providerSaveCorrect")
    public void testIsPageFull(String photo,String title, String participants, String dateFrom, String dateTo,
                               String description,List<String> hashtagsToEnter,String country,String city) {
        clearPageAddEvent();
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        assertTrue(addEventPage.isPageFull());
    }


}
