package profile.event;

import base.Helper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.profile.AddEventPage;
import pages.profile.EventMenu;
import profile.SetUpProfile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.testng.Assert.*;

public class ErrorsAddEventTest extends AddEventBaseTest{


    @DataProvider
    public Object[][] negativeProviderLoadImage(){
        return new Object[][]{{getDataByKey("incorrectFormatPhoto")}};
    }

    @Test(dataProvider = "negativeProviderLoadImage")
    public void notLoadImageIncorrectFormat(String nameFileImg){
        addEventPage.loadImage(nameFileImg);
        assertTrue(Helper.checkActionElement(addEventPage.getFileUploader()));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerIncorrectResolution(){
        return new Object[][]{{getDataByKey("smallResolutionPhoto")},{getDataByKey("bigResolutionPhoto")}};
    }

    @Test(dataProvider = "negativeProviderLoadImage")
    public void notLoadImageIncorrectResolution(String nameFileImg){
        SoftAssert softAssert=new SoftAssert();
        addEventPage.loadImage(nameFileImg);
        softAssert.assertTrue(Helper.checkActionElement(addEventPage.getFileUploader()));
        softAssert.assertTrue(Helper.checkActionElement(addEventPage.getErrorIncorrectResolutionPhoto()));
        softAssert.assertEquals(addEventPage.getErrorIncorrectResolutionPhoto().getText(),getDataByKey("errorIncorrectResolutionPhoto"));
        addEventPage.loadImage(getDataByKey("getDataByKey"));
        softAssert.assertFalse(Helper.checkActionElement(addEventPage.getErrorIncorrectResolutionPhoto()));
    }


 /*   @DataProvider
    public Object[][] providerCorrectImage(){
        return new Object[][]{{prop.getProperty("correctPhoto")}};
    }

    @DataProvider
    public Object[][] providerCorrectTitle(){
        return new Object[][]{{prop.getProperty("correctTitle")}};
    }

    @DataProvider
    public Object[][] providerCorrectParticipants(){
        return new Object[][]{{prop.getProperty("correctParticipants")}};
    }

    @DataProvider
    public Object[][] providerCorrectDescription(){
        return new Object[][]{{prop.getProperty("correctDescription")}};
    }

    @DataProvider
    public Object[][] providerCorrectHashtags(){
        List<String> hashtags= Arrays.asList(prop.getProperty("correctHashtags").split(","));
        return new Object[][]{{hashtags}};
    }

    @DataProvider
    public Object[][] providerCorrectCountry(){
        return new Object[][]{{prop.getProperty("correctCountry")}};
    }

    @DataProvider
    public Object[][] providerCorrectCity(){
        return new Object[][]{{prop.getProperty("correctCity")}};
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerIncorrectSize(){
        return new Object[][]{{prop.getProperty("bigPhoto")}};
    }

    @Test(dataProvider = "negativeProviderLoadImage")
    public void notLoadImageIncorrectSize(String nameFileImg){
        addEventPage.getAltAttributeOfLoadedImage(nameFileImg);
        assertFalse(addEventPage.getImage().isEnabled());
    }

    @DataProvider
    public Object[][] providerErrorLoadIncorrectSize(){
        return new Object[][]{{prop.getProperty("errorIncorrectSizePhoto")}};
    }

    @Test(dependsOnMethods = "notLoadImageIncorrectSize")
    public void errorDisplayedIncorrectSize() {
        assertTrue(addEventPage.getErrorIncorrectPhotoSize().isDisplayed());
    }

    @Test(dependsOnMethods = "errorDisplayedIncorrectSize", dataProvider = "providerErrorLoadIncorrectSize")
    public void errorTextIncorrectSize(String error) {
        assertEquals(addEventPage.getErrorIncorrectPhotoSize().getText(),error);
    }

    @Test(dependsOnMethods = "errorTextIncorrectSize",dataProvider = "providerCorrectImage",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceErrorIncorrectSize(String photo){
        addEventPage.getAltAttributeOfLoadedImage(photo);
        addEventPage.getErrorIncorrectPhotoSize().isDisplayed();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputTitle(){
        return new Object[][]{{prop.getProperty("smallTitle")},{prop.getProperty("bigTitle")}};
    }

    @Test(dataProvider = "negativeProviderInputTitle")
    public void negativeInputTitle(String text) {
        addEventPage.getTitle().clear();
        addEventPage.getValueOfInputtedTitle(text);
        assertTrue(addEventPage.getErrorIncorrectTitle().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeInputTitle")
    public void errorTextTitleLengthError() {
        assertEquals(addEventPage.getErrorIncorrectTitle().getText(),prop.getProperty("errorIncorrectTitle"));
    }

    @Test(dependsOnMethods = "errorTextTitleLengthError",dataProvider = "providerCorrectTitle",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceTitleLengthError(String text) {
        addEventPage.getTitle().clear();
        addEventPage.getValueOfInputtedTitle(text);
        assertFalse(addEventPage.getErrorIncorrectTitle().isDisplayed());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputTitleNoOneLetter(){
        return new Object[][]{{prop.getProperty("noOneLetterInTitle")}};
    }

    @Test(dataProvider = "negativeProviderInputTitleNoOneLetter")
    public void negativeInputTitleNoOneLetter(String text) {
        addEventPage.getTitle().clear();
        addEventPage.getValueOfInputtedTitle(text);
        assertTrue(addEventPage.getErrorIncorrectTitleNoOneLetter().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeInputTitleNoOneLetter")
    public void errorTextTitleNoOneLetter() {
        assertEquals(addEventPage.getErrorIncorrectTitleNoOneLetter().getText(),prop.getProperty("errorIncorrectTitleNoOneLetter"));
    }

    @Test(dependsOnMethods = "errorTextTitleNoOneLetter",dataProvider = "providerCorrectTitle",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceTitleNoOneLetterError(String text) {
        addEventPage.getTitle().clear();
        addEventPage.getValueOfInputtedTitle(text);
        assertFalse(addEventPage.getErrorIncorrectTitleNoOneLetter().isDisplayed());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerTooManyParticipants(){
        return new Object[][]{{prop.getProperty("tooManyParticipants")}};
    }

    @Test(dataProvider = "providerTooManyParticipants")
    public void negativeTooManyParticipants(String value) {
        addEventPage.getValueOfInputtedParticipants(value);
        assertTrue(addEventPage.getErrorTooManyParticipants().isDisplayed());
    }

    @DataProvider
    public Object[][] providerTextTooManyParticipants(){
        return new Object[][]{{prop.getProperty("errorTooManyParticipants")}};
    }
    @Test(dataProvider = "providerTooManyParticipants",dependsOnMethods = "negativeTooManyParticipants")
    public void errorTextTooManyParticipants(String error) {
        assertEquals(addEventPage.getErrorTooManyParticipants().getText(),error);
    }

    @Test(dependsOnMethods = "errorTextTooManyParticipants", dataProvider = "providerCorrectParticipants",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceErrorTooManyParticipants(String value){
        addEventPage.getValueOfInputtedParticipants(value);
        assertFalse(addEventPage.getErrorTooManyParticipants().isDisplayed());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateFrom(){
        return new Object[][]{{prop.getProperty("incorrectMonthDateFrom")},{prop.getProperty("incorrectDayDateFrom")},
                {prop.getProperty("incorrectYearDateFrom")},{prop.getProperty("notInFormatDateFrom")},
                {prop.getProperty("beforeThanPresentDateFrom")},{""}};
    }

    @Test(dataProvider = "dataProviderDateFrom")
    public void negativeInputDateFrom(String date) {
//        addEventPage.getValueOfInputtedDateFrom(date);
        assertEquals(addEventPage.getDateFrom().getAttribute("value"),AddEventPage.convertDateToCorrect(date,LocalDate.now()));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateTo(){
        return new Object[][]{{prop.getProperty("incorrectMonthDateTo")},{prop.getProperty("incorrectDayDateTo")},
                {prop.getProperty("incorrectYearDateTo")},
                {prop.getProperty("notInFormatDateTo")},{prop.getProperty("beforeThanInFrom")},{""}};
    }

    @Test(dataProvider = "dataProviderDateTo")
    public void negativeInputDateTo(String date) {
       // addEventPage.getValueOfInputtedDateFrom(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        addEventPage.getValueOfInputtedDateTo(date);
        assertEquals(addEventPage.getDateTo().getAttribute("value"),AddEventPage.convertDateToCorrect(date,LocalDate.now()));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputDescription(){
        return new Object[][]{{prop.getProperty("smallDescription")},{prop.getProperty("bigDescription")}};
    }

    @Test(dataProvider = "negativeProviderInputDescription")
    public void negativeInputDescription(String text) {
        addEventPage.getDescription().clear();
        addEventPage.getValueOfInputtedDescription(text);
        assertTrue(addEventPage.getErrorIncorrectDescription().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeInputDescription")
    public void errorTextDescriptionLength() {
        assertEquals(addEventPage.getErrorIncorrectDescription().getText(),prop.getProperty("errorIncorrectDescription"));
    }

    @Test(dependsOnMethods = "errorTextDescriptionLength",dataProvider = "providerCorrectDescription",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceDescriptionLengthError(String text) {
        addEventPage.getDescription().clear();
        addEventPage.getValueOfInputtedDescription(text);
        assertFalse(addEventPage.getErrorIncorrectDescription().isDisplayed());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputHashtags(){
        String[] hashtags=prop.getProperty("tooManyHashtags").split(",");
        return new Object[][]{{hashtags}};
    }

    @Test(dataProvider = "negativeProviderInputHashtags")
    public void negativeInputHashtags(List<String> hashtagsToEnter) {
        addEventPage.getValueOfInputtedHashtags(hashtagsToEnter);
        assertTrue(addEventPage.getErrorTooManyHashtags().isDisplayed());
    }

    @Test(dependsOnMethods = "negativeInputHashtags")
    public void errorTextTooManyHashtags() {
        assertEquals(addEventPage.getErrorTooManyHashtags().getText(),prop.getProperty("errorTooManyHashtags"));
    }

    @Test(dependsOnMethods = "errorTextTooManyHashtags", dataProvider = "providerCorrectHashtags",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceTooManyHashtagsError(List<String> hashtagsToEnter) {
        addEventPage.getValueOfInputtedHashtags(hashtagsToEnter);
        assertFalse(addEventPage.getErrorTooManyHashtags().isDisplayed());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void createEventWithoutData(){
       // addEventPage=new AddEventPage(setUpProfile.getDriver(),setUpProfile.getWebDriverWait());
       // assertTrue(addEventPage.clickSave());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dependsOnMethods = "createEventWithoutData")
    public void emptyImage() {
        assertTrue(addEventPage.getRequiredImage().isDisplayed());
    }

    @Test(dependsOnMethods = "emptyImage")
    public void emptyImageErrorText() {
        assertEquals(addEventPage.getRequiredImage().getText(),prop.getProperty("errorRequired"));
    }

    @Test(dependsOnMethods = "emptyImageErrorText",dataProvider = "providerCorrectImage",
            expectedExceptions={NoSuchElementException.class,StaleElementReferenceException.class})
    public void disappearanceRequiredErrorImage(String photo){
        addEventPage.getAltAttributeOfLoadedImage(photo);
        addEventPage.getRequiredImage().isDisplayed();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dependsOnMethods = "createEventWithoutData")
    public void emptyTitle() {
        assertTrue(addEventPage.getRequiredTitle().isDisplayed());
    }

    @Test(dependsOnMethods = "emptyTitle")
    public void emptyTitleErrorText() {
        assertEquals(addEventPage.getRequiredTitle().getText(),prop.getProperty("errorRequired"));
    }

    @Test(dependsOnMethods = "emptyTitleErrorText",dataProvider = "providerCorrectTitle",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceErrorEmptyTitle(String text){
        addEventPage.getValueOfInputtedTitle(text);
        assertFalse(addEventPage.getRequiredTitle().isEnabled());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dependsOnMethods = "createEventWithoutData")
    public void emptyDescription() {
        assertTrue(addEventPage.getRequiredDescription().isDisplayed());
    }

    @Test(dependsOnMethods = "emptyDescription")
    public void emptyDescriptionErrorText() {
        assertEquals(addEventPage.getRequiredDescription().getText(),prop.getProperty("errorRequired"));
    }

    @Test(dependsOnMethods = "emptyDescriptionErrorText",dataProvider = "providerCorrectDescription",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceErrorEmptyDescription(String text){
        addEventPage.getValueOfInputtedDescription(text);
        assertFalse(addEventPage.getRequiredDescription().isEnabled());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dependsOnMethods = "createEventWithoutData")
    public void emptyHashtags() {
        assertTrue(addEventPage.getRequiredHashtags().isDisplayed());
    }

    @Test(dependsOnMethods = "emptyHashtags")
    public void emptyHashtagsErrorText() {
        assertEquals(addEventPage.getRequiredHashtags().getText(),prop.getProperty("errorRequired"));
    }

    @Test(dependsOnMethods = "emptyHashtagsErrorText",dataProvider = "providerCorrectHashtags",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceErrorEmptyHashtags(List<String> text){
        addEventPage.getValueOfInputtedHashtags(text);
        assertFalse(addEventPage.getRequiredHashtags().isEnabled());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dependsOnMethods = "createEventWithoutData")
    public void emptyCountry() {
        assertTrue(addEventPage.getRequiredCountry().isDisplayed());
    }

    @Test(dependsOnMethods = "emptyCountry")
    public void emptyCountryErrorText() {
        assertEquals(addEventPage.getRequiredCountry().getText(),prop.getProperty("errorRequired"));
    }

    @Test(dependsOnMethods = "emptyCountryErrorText",dataProvider = "providerCorrectCountry",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceErrorEmptyCountry(String text){
        addEventPage.getValueOfInputtedCountry(text);
        assertFalse(addEventPage.getRequiredCountry().isEnabled());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "providerCorrectCountry",dependsOnMethods = "emptyCountryErrorText")
    public void emptyCity(String country) {
        addEventPage.getValueOfInputtedCountry(country);
        addEventPage.clickSave();
        assertTrue(addEventPage.getRequiredCity().isDisplayed());
    }

    @Test(dependsOnMethods = "emptyCity")
    public void emptyCityErrorText() {
        assertEquals(addEventPage.getRequiredCity().getText(),prop.getProperty("errorRequired"));
    }

    @Test(dependsOnMethods = "emptyCityErrorText",dataProvider = "providerCorrectCity",
            expectedExceptions={NoSuchElementException.class, StaleElementReferenceException.class})
    public void disappearanceErrorEmptyCity(String text){
        addEventPage.getValueOfInputtedCity(text);
        assertFalse(addEventPage.getRequiredCity().isEnabled());
    }

    @AfterClass
    public void afterClass(){
        setUpProfile.driverQuit();
    }*/
}
