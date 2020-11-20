package profile.event;

import jdbc.EventsRepository;
import jdbc.UserInfoRepository;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static pages.base.Helper.isElementPresent;

public class AddEventTest extends AddEventBaseTest{

    @DataProvider
    public Object[][] providerSaveCorrect(){
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctPhoto"),getDataByKey("correctTitle"),
                getDataByKey("correctParticipants"), getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),getDataByKey("correctDescription"),
                hashtags,getDataByKey("correctCountry"),getDataByKey("correctCity")}};
    }

    /**
     * Verify that "Save" works correctly with valid data, verify appearing message about
     * saving and clearing after that all fields
     * @param photo
     * @param title
     * @param participants
     * @param dateFrom
     * @param dateTo
     * @param description
     * @param hashtagsToEnter is a list of hashtags
     * @param country
     * @param city
     */

    @Test(dataProvider = "providerSaveCorrect")
    public void testSaveCorrect(String photo,String title, String participants, String dateFrom, String dateTo,
                                     String description,List<String> hashtagsToEnter,String country,String city){
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.initCity();
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.clickSave();
        assertTrue(addEventPage.isAppearCreatedEventMessage(),"Message don't appear");
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

    /**
     * Verify that event isn't save without photo, verify that message about saving doesn't appear,
     * fields don't clear and a red inscription "Required" appear under file uploader
     * @param title
     * @param participants
     * @param dateFrom
     * @param dateTo
     * @param description
     * @param hashtagsToEnter is a list of hashtags
     * @param country
     * @param city
     */

    @Test(dataProvider = "providerSaveWithoutImage")
    public void negativeSaveWithoutImage(String title, String participants, String dateFrom, String dateTo,
                                    String description,List<String> hashtagsToEnter,String country,String city){
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.initCity();
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.clickSave();
        assertTrue(isElementPresent(addEventPage.getRequiredImage()));
        assertFalse(isElementPresent(addEventPage.getCreatedEventMessage()));
        assertFalse(addEventPage.isPageEmpty());
    }

    @DataProvider
    public Object[][] providerSaveWithoutTitle(){
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctPhoto"),
                getDataByKey("tooManyParticipants"), getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),getDataByKey("correctDescription"), hashtags,
                getDataByKey("correctCountry"),getDataByKey("correctCity")}};
    }

    /**
     * Verify that event isn't save without title, verify that message about saving doesn't appear,
     * fields don't clear and a red inscription "Required" appear under field "Title"
     * @param photo
     * @param participants
     * @param dateFrom
     * @param dateTo
     * @param description
     * @param hashtagsToEnter is a list of hashtags
     * @param country
     * @param city
     */

    @Test(dataProvider = "providerSaveWithoutTitle")
    public void negativeSaveWithoutTitle(String photo, String participants, String dateFrom, String dateTo,
                                String description,List<String> hashtagsToEnter,String country,String city){
        addEventPage.loadImage(photo);
        addEventPage.inputParticipants(participants);
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.initCity();
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.clickSave();
        assertTrue(isElementPresent(addEventPage.getRequiredTitle()));
        assertFalse(isElementPresent(addEventPage.getCreatedEventMessage()));
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

    /**
     * Verify that event isn't save without description, verify that message about saving doesn't appear,
     * fields don't clear and a red inscription "Required" appear under field "Description"
     * @param photo
     * @param title
     * @param participants
     * @param dateFrom
     * @param dateTo
     * @param hashtagsToEnter is a list of hashtags
     * @param country
     * @param city
     */

    @Test(dataProvider = "providerSaveWithoutDescription")
    public void negativeSaveWithoutDescription(String photo,String title, String participants, String dateFrom, String dateTo,
                                List<String> hashtagsToEnter,String country,String city){
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputCountry(country);
        addEventPage.initCity();
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.clickSave();
        assertTrue(isElementPresent(addEventPage.getRequiredDescription()));
        assertFalse(isElementPresent(addEventPage.getCreatedEventMessage()));
        assertFalse(addEventPage.isPageEmpty());
    }

    @DataProvider
    public Object[][] providerSaveWithoutHashtags(){
        return new Object[][]{{getDataByKey("correctPhoto"),getDataByKey("correctTitle"),
                getDataByKey("correctParticipants"),getDataByKey("correctDateFrom"),
                getDataByKey("correctDateTo"),getDataByKey("correctDescription"),
                getDataByKey("correctCountry"),getDataByKey("correctCity")}};
    }

    /**
     * Verify that event isn't save without hashtags, verify that message about saving doesn't appear,
     * fields don't clear and a red inscription "Required" appear under field "Hashtags"
     * @param photo
     * @param title
     * @param participants
     * @param dateFrom
     * @param dateTo
     * @param description
     * @param country
     * @param city
     */

    @Test(dataProvider = "providerSaveWithoutHashtags")
    public void negativeSaveWithoutHashtags(String photo,String title, String participants, String dateFrom, String dateTo,
                                String description,String country,String city){
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.initCity();
        addEventPage.inputCity(city);
        addEventPage.clickSave();
        assertTrue(isElementPresent(addEventPage.getRequiredHashtags()));
        assertFalse(isElementPresent(addEventPage.getCreatedEventMessage()));
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

    /**
     * Verify that event isn't save without country, verify that message about saving doesn't appear,
     * fields don't clear and a red inscription "Required" appear under field "Country"
     * @param photo
     * @param title
     * @param participants
     * @param dateFrom
     * @param dateTo
     * @param description
     * @param hashtagsToEnter is a list of hashtags
     */

    @Test(dataProvider = "providerSaveWithoutCountry")
    public void negativeSaveWithoutCountry(String photo,String title, String participants, String dateFrom, String dateTo,
                                String description,List<String> hashtagsToEnter){
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.clickSave();
        assertTrue(isElementPresent(addEventPage.getRequiredCountry()));
        assertFalse(isElementPresent(addEventPage.getCreatedEventMessage()));
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

    /**
     * Verify that event isn't save without city, verify that message about saving doesn't appear,
     * fields don't clear and a red inscription "Required" appear under field "City"
     * @param photo
     * @param title
     * @param participants
     * @param dateFrom
     * @param dateTo
     * @param description
     * @param hashtagsToEnter is a list of hashtags
     * @param country
     */

    @Test(dataProvider = "providerSaveWithoutCity")
    public void negativeSaveWithoutCity(String photo,String title, String participants, String dateFrom, String dateTo,
                                           String description,List<String> hashtagsToEnter,String country){
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.clickSave();
        assertTrue(isElementPresent(addEventPage.getRequiredCity()));
        assertFalse(isElementPresent(addEventPage.getCreatedEventMessage()));
        assertFalse(addEventPage.isPageEmpty());
    }

    /**
     * Verify that all fields are empty when user only click on "ADD EVENT"
     */

    @Test
    public void testIsPageEmpty() {
        assertTrue(addEventPage.isPageEmpty());
    }

    /**
     * Verify that all fields are full after input
     */

    @Test(dataProvider = "providerSaveCorrect")
    public void testIsPageFull(String photo,String title, String participants, String dateFrom, String dateTo,
                               String description,List<String> hashtagsToEnter,String country,String city) {
        addEventPage.loadImage(photo);
        addEventPage.inputTitle(title);
        addEventPage.inputParticipants(participants);
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputCountry(country);
        addEventPage.initCity();
        addEventPage.inputCity(city);
        addEventPage.inputHashtags(hashtagsToEnter);
        assertTrue(addEventPage.isPageFull());
    }

    /**
     * delete all event create by during tests
     */

    @AfterClass
    public void clearEnteredData(){
        EventsRepository.deleteEvents(getDataByKey("correctTitle"),getDataByKey("correctDescription"),
                UserInfoRepository.getColumnByEmail(getDataByKey("email"),"Id"));
    }


}
