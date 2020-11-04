package profile.add_event;

import jdbc.EventsRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.profile.EventMenu;
import pages.profile.events_menu_pages.AddEventPage;
import profile.SetUpProfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.*;

public class AEventDefaultBehaviorTest {

    AddEventPage addEventPage;
    Properties prop;

    @BeforeClass
    public void beforeClass() {
        new SetUpProfile();
        SetUpProfile.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#full-width-tab-4")));
        new EventMenu(SetUpProfile.getDriver()).clickAddEvent();
        addEventPage=new AddEventPage(SetUpProfile.getDriver(),SetUpProfile.getWebDriverWait());
        prop=SetUpProfile.getProp();
        try (InputStream input = new FileInputStream("src\\test\\resources\\forProfile\\testDataProfile.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderLoadImage(){
        return new Object[][]{{prop.getProperty("correctPhoto")}};//TODO: change absolute path in the file testData
    }

    @Test(dataProvider = "dataProviderLoadImage")
    public void testLoadImage(String nameFileImg) {
        assertTrue(addEventPage.loadImage(nameFileImg));
    }

    @Test(dataProvider = "dataProviderLoadImage",dependsOnMethods="testLoadImage")
    public void testGetValueImage(String nameFileImg){
        assertEquals(addEventPage.getValueImage(),nameFileImg.substring(nameFileImg.lastIndexOf('\\')+1));
    }

    @Test(dependsOnMethods="testGetValueImage")
    public void testClickClear(){
        addEventPage.clickClear();
        assertFalse(addEventPage.getFileUploader().isSelected());
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderInputTitle(){
        return new Object[][]{{prop.getProperty("correctTitle")}};
    }

    @Test(dataProvider = "dataProviderInputTitle")
    public void testInputTitle(String text) {
        addEventPage.getTitle().clear();
        addEventPage.inputTitle(text);
        assertEquals(addEventPage.getTitle().getAttribute("value"),text);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerInputParticipants(){
        return new Object[][]{{prop.getProperty("correctParticipants")}};
    }

    @Test(dataProvider = "providerInputParticipants")
    public void testInputParticipants(String value) {
        addEventPage.getParticipants().clear();
        addEventPage.inputParticipants(value);
        assertEquals(addEventPage.getParticipants().getAttribute("value"),value);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateFrom(){
        return new Object[][]{{prop.getProperty("correctDateFrom")}};
    }

    @Test(dataProvider = "dataProviderDateFrom")
    public void testInputDateFrom(String date) {
        addEventPage.inputDateFrom(date);
        assertEquals(addEventPage.getDateFrom().getAttribute("value"),date);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateTo(){
        return new Object[][]{{prop.getProperty("correctDateTo")}};
    }

    @Test(dataProvider = "dataProviderDateTo", dependsOnMethods="testInputDateFrom")
    public void testInputDateTo(String date) {
        addEventPage.inputDateTo(date);
        assertEquals(addEventPage.getDateTo().getAttribute("value"),date);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDescription(){
        return new Object[][]{{prop.getProperty("correctDescription")}};
    }

    @Test(dataProvider = "dataProviderDescription")
    public void testInputDescription(String text) {
        addEventPage.getDescription().clear();
        addEventPage.inputDescription(text);
        assertEquals(addEventPage.getDescription().getAttribute("value"),text);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderHashtags(){
        List<String> hashtags= Arrays.asList(prop.getProperty("correctHashtags").split(","));
        return new Object[][]{{hashtags}};
    }

    @Test(dataProvider = "dataProviderHashtags")
    public void testInputHashtags(List<String> hashtagsToEnter) {
        addEventPage.getHashtags().clear();
        addEventPage.inputHashtags(hashtagsToEnter);
        List<WebElement> webElements = addEventPage.getDriver().findElements(By.cssSelector("#rw_1_taglist > li > span"));
        List<String> list = new ArrayList<>();
        for(WebElement element: webElements)list.add(element.getText());
        assertEquals(list, hashtagsToEnter);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderCountry(){
        return new Object[][]{{prop.getProperty("correctCountry")}};
    }

    @Test(dataProvider = "dataProviderCountry")
    public void testInputCountry(String text) {
        assertTrue(addEventPage.inputCountry(text));
    }

    @Test(groups = "Positive tests")
    public void testCountryAmount(){
        assertEquals(EventsRepository.getAmountOfCountries()+1,addEventPage.countriesAmount());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderCity(){
        return new Object[][]{{prop.getProperty("correctCity")}};
    }

    @Test(dataProvider = "dataProviderCity", dependsOnMethods ="testInputCountry")
    public void testInputCity(String text) {
        assertTrue(addEventPage.inputCity(text));
    }

    @Test(dataProvider = "dataProviderCountry", dependsOnMethods ="testInputCountry")
    public void testCitiesAmount(String country){
        assertEquals(EventsRepository.getAmountOfCities(country)+1,addEventPage.citiesAmount());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testClickSave() {
        assertTrue(addEventPage.clickSave());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}