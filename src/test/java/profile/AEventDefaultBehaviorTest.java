package profile;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.profile.EventMenu;
import pages.profile.eventsMenuPages.AddEventPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AEventDefaultBehaviorTest {

    private static AddEventPage addEventPage;
    private static Properties prop;

    @BeforeClass
    public static void beforeClass() {
        new SetUpProfile();
        SetUpProfile.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#full-width-tab-4")));
        new EventMenu(SetUpProfile.getDriver()).clickAddEvent();
        addEventPage=new AddEventPage(SetUpProfile.getDriver());
        prop=SetUpProfile.getProp();
        try (InputStream input = new FileInputStream("src\\test\\resources\\forProfile\\testDataAddEvent.properties")) {
            prop.load(input);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderLoadImage(){
        return new Object[][]{{prop.getProperty("correctPhoto")}};//TODO: change absolute path in the file testData
    }

    @Test(dataProvider = "dataProviderLoadImage",priority = 1)
    public void testLoadImage(String nameFileImg) {
        assertTrue(addEventPage.loadImage(nameFileImg));
    }

    @Test(dataProvider = "dataProviderLoadImage",dependsOnMethods="testLoadImage")
    public void testGetValueImage(String nameFileImg){
        assertEquals(addEventPage.getValueImage(),nameFileImg.substring(nameFileImg.lastIndexOf('\\')+1));

    }

    @Test(dependsOnMethods="testGetValueImage")
    public void testClickClear(){
      //  addEventPage.clickClear();
       // assertTrue(!addEventPage.getFileUploader().isSelected());
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderInputTitle(){
        return new Object[][]{{prop.getProperty("correctTitle")}};
    }

    @Test(dataProvider = "dataProviderInputTitle")
    public void testInputTitle(String text) {
        addEventPage.getTitle().clear();
        assertTrue(addEventPage.inputTitle(text));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateFrom(){
        return new Object[][]{{LocalDate.parse(prop.getProperty("correctDataFrom"))}};
    }

    @Test(dataProvider = "dataProviderDateFrom")
    public void testInputDateFrom(LocalDate date) {
        addEventPage.getDateFrom().clear();
        assertTrue(addEventPage.inputDateFrom(date));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateTo(){
        return new Object[][]{{LocalDate.parse(prop.getProperty("correctDataTo"))}};
    }

    @Test(dataProvider = "dataProviderDateTo", dependsOnMethods="testInputDateFrom")
    public void testInputDateTo(LocalDate date) {
        addEventPage.getDateTo().clear();
        assertTrue(addEventPage.inputDateTo(date));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDescription(){
        return new Object[][]{{prop.getProperty("correctDescription")}};
    }

    @Test(dataProvider = "dataProviderDescription")
    public void testInputDescription(String text) {
        addEventPage.getDescription().clear();
        assertTrue(addEventPage.inputDescription(text));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderHashtags(){
        String[] hashtags=prop.getProperty("correctHashtags").split(",");
        return new Object[][]{{hashtags}};
    }

    @Test(dataProvider = "dataProviderHashtags")
    public void testInputHashtags(String[] hashtagsToEnter) {
        addEventPage.getHashtags().clear();
        assertTrue(addEventPage.inputHashtags(hashtagsToEnter));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderCountry(){
        return new Object[][]{{prop.getProperty("correctCountry2")}};
    }

    @Test(dataProvider = "dataProviderCountry")
    public void testInputCountry(String text) {
        assertTrue(addEventPage.inputCountry(text));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderCity(){
        return new Object[][]{{"Tokyo"}};//{prop.getProperty("correctCity2")}};
    }

    @Test(dataProvider = "dataProviderCity", dependsOnMethods ="testInputCountry")
    public void testInputCity(String text) {
        assertTrue(addEventPage.inputCity(text));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testClickSave() {
        assertTrue(addEventPage.clickSave());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputTitle(){
        return new Object[][]{{prop.getProperty("smallTitle")},{prop.getProperty("bigTitle")}};
    }

    @Test(dataProvider = "negativeProviderInputTitle",dependsOnMethods = "testInputTitle")
    public void negativeInputTitle(String text) {
        addEventPage.getTitle().clear();
        addEventPage.inputTitle(text);
        assertTrue(addEventPage.getErrorIncorrectTitle().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeInputTitle")
    public void incorrectTitleLengthError() {
        assertEquals(addEventPage.getErrorIncorrectTitle().getText(),prop.getProperty("errorIncorrectTitle"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputTitleNoOneLetter(){
        return new Object[][]{{prop.getProperty("noOneLetterInTitle")}};
    }

    @Test(dataProvider = "negativeProviderInputTitleNoOneLetter",dependsOnMethods = "testInputTitle")
    public void negativeInputTitleNoOneLetter(String text) {
        addEventPage.getTitle().clear();
        addEventPage.inputTitle(text);
        assertTrue(addEventPage.getErrorIncorrectTitleNoOneLetter().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeInputTitleNoOneLetter")
    public void negativeTitleNoOneLetterError() {
        assertEquals(addEventPage.getErrorIncorrectTitleNoOneLetter().getText(),prop.getProperty("errorIncorrectTitleNoOneLetter"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputDescription(){
        return new Object[][]{{prop.getProperty("smallDescription")},{prop.getProperty("bigDescription")}};
    }

    @Test(dataProvider = "negativeProviderInputDescription",dependsOnMethods = "testInputDescription")
    public void negativeInputDescription(String text) {
        addEventPage.inputDescription(text);
        assertTrue(addEventPage.getErrorIncorrectDescription().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeInputDescription")
    public void incorrectDescriptionLengthError() {
        assertEquals(addEventPage.getErrorIncorrectDescription().getText(),prop.getProperty("errorIncorrectDescription"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputHashtags(){
        String[] hashtags=prop.getProperty("toMoreHashtags").split(",");
        return new Object[][]{{hashtags}};
    }

    @Test(dataProvider = "negativeProviderInputHashtags",dependsOnMethods = "testInputHashtags")
    public void negativeInputHashtags(String[] hashtagsToEnter) {
        addEventPage.inputHashtags(hashtagsToEnter);
        assertTrue(addEventPage.getErrorTooMuchHashtags().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeInputHashtags")
    public void negativeTooMoreHashtagsError() {
        assertEquals(addEventPage.getErrorTooMuchHashtags().getText(),prop.getProperty("errorToMoreHashtags"));
    }
}