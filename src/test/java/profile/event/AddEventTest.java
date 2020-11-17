package profile.event;

import jdbc.EventsRepository;
import jdbc.UserInfoRepository;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.profile.EventMenu;
import pages.profile.AddEventPage;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import profile.SetUpProfile;

import java.util.Properties;

import static org.testng.Assert.*;

public class AddEventTest extends AddEventBaseTest{



    private void clearPageAddEvent(){
        eventMenu.clickFutureEvents();
        eventMenu.clickAddEvent();
        addEventPage=new AddEventPage(driver);
    }


    @DataProvider
    public Object[][] providerSaveCorrect(){
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctPhoto"),getDataByKey("correctTitle"),
                getDataByKey("correctParticipants"), getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),getDataByKey("correctDescription"),
                hashtags,getDataByKey("correctCountry"),getDataByKey("correctCity")}};
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
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.clickSave();
        assertTrue(addEventPage.isAppearCreatedEventMessage(),"Message don't appear");
        assertEquals(EventsRepository.getAmountOfEvents(),amountOfEventsBefore+1);
    }

    @Test(dependsOnMethods = "testSaveCorrect")
    public void testEmptyPage(){
        assertTrue(addEventPage.isPageEmpty());
    }


    @DataProvider
    public Object[][] providerSaveWithoutImage(){
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctTitle"),getDataByKey("correctParticipants"),
                getDataByKey("correctDateFrom"), getDataByKey("correctDateTo"),
                getDataByKey("correctDescription"), hashtags,
                getDataByKey("correctCountry"),getDataByKey("correctCity")}};
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
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
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
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctPhoto"),
                getDataByKey("tooManyParticipants"), getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),getDataByKey("correctDescription"),
                getDataByKey("correctCountry"),getDataByKey("correctCity")}};
    }


    @Test(dataProvider = "providerSaveWithoutTitle")
    public void negativeSaveWithoutTitle(String photo, String participants, String dateFrom, String dateTo,
                                String description,List<String> hashtagsToEnter,String country,String city){
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
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
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctPhoto"),getDataByKey("correctTitle"),
                getDataByKey("correctParticipants"), getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),
                hashtags,getDataByKey("correctCountry"),getDataByKey("correctCity")}};
    }


    @Test(dataProvider = "providerSaveWithoutDescription")
    public void negativeSaveWithoutDescription(String photo,String title, String participants, String dateFrom, String dateTo,
                                List<String> hashtagsToEnter,String country,String city){
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
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
        return new Object[][]{{getDataByKey("correctPhoto"),getDataByKey("correctTitle"),
                getDataByKey("correctParticipants"),getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),getDataByKey("correctDescription"),
                getDataByKey("correctCountry"),getDataByKey("correctCity")}};
    }


    @Test(dataProvider = "providerSaveWithoutHashtags")
    public void negativeSaveWithoutHashtags(String photo,String title, String participants, String dateFrom, String dateTo,
                                String description,String country,String city){
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
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctPhoto"),getDataByKey("correctTitle"),
                getDataByKey("correctParticipants"), getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),getDataByKey("correctDescription"),
                hashtags}};
    }

    @Test(dataProvider = "providerSaveWithoutCountry")
    public void negativeSaveWithoutCountry(String photo,String title, String participants, String dateFrom, String dateTo,
                                String description,List<String> hashtagsToEnter){
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
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctPhoto"),getDataByKey("correctTitle"),
                getDataByKey("correctParticipants"), getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),getDataByKey("correctDescription"),
                hashtags,getDataByKey("correctCountry")}};
    }

    @Test(dataProvider = "providerSaveWithoutCity")
    public void negativeSaveWithoutCity(String photo,String title, String participants, String dateFrom, String dateTo,
                                           String description,List<String> hashtagsToEnter,String country){
        clearPageAddEvent();
        int amountOfEventsBefore= EventsRepository.getAmountOfEvents();
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.inputHashtags(hashtagsToEnter);
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
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
        assertTrue(addEventPage.isPageFull());
    }


    @AfterClass
    public void clearEnteredData(){
        EventsRepository.deleteEvents(getDataByKey("correctTitle"),getDataByKey("correctDescription"),
                UserInfoRepository.getColumnByEmail(getDataByKey("email"),"Id"));
    }


}
