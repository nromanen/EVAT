package profile.event;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.base.BasePage.isElementPresentWait;
import static pages.base.Helper.isElementPresent;

public class ErrorsAddEventTest extends AddEventBaseTest {


    @DataProvider
    public Object[][] negativeProviderLoadImage() {
        return new Object[][]{{getDataByKey("incorrectFormatPhoto")}};
    }

    /**
     * Verify that photo don't load
     * @param nameFileImg is path of file not in correct format
     */
    @Description("Verify that photo don't load")
    @Test(dataProvider = "negativeProviderLoadImage")
    public void notLoadImageIncorrectFormat(String nameFileImg) {
        addEventPage.loadImage(nameFileImg);
        assertTrue(isElementPresent(addEventPage.getFileUploader()),"The photo with incorrect format was uploaded");
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerIncorrectResolution() {
        return new Object[][]{{getDataByKey("smallResolutionPhoto")}, {getDataByKey("bigResolutionPhoto")}};
    }

    /**
     * Verify that error message is shown after loading photo in incorrect resolutions
     * and message disappear after input correct image
     * @param nameFileImg is path of file not in correct resolutions
     */
    @Description("Verify that error message is shown after loading photo in incorrect resolutions and message disappear after input correct image")
    @Test(dataProvider = "negativeProviderLoadImage")
    public void notLoadImageIncorrectResolution(String nameFileImg) {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.loadImage(nameFileImg);
        softAssert.assertTrue(isElementPresent(addEventPage.getFileUploader()),"The photo was uploaded by mistake");
        softAssert.assertTrue(isElementPresent(addEventPage.getErrorIncorrectResolutionPhoto()),"ErrorIncorrectResolutionPhoto isn't present");
        softAssert.assertEquals(addEventPage.getErrorIncorrectResolutionPhoto().getText(), getDataByKey("errorIncorrectResolutionPhoto"),"Incorrect text of ErrorIncorrectResolutionPhoto");
        addEventPage.loadImage(getDataByKey("correctPhoto"));
        softAssert.assertFalse(isElementPresent(addEventPage.getErrorIncorrectResolutionPhoto()),"ErrorIncorrectResolutionPhoto doesn't disappear");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] providerIncorrectSize() {
        return new Object[][]{{getDataByKey("bigPhoto")}};
    }

    /**
     * Verify that error message is shown after loading photo in incorrect size
     * and message disappear after input correct image
     * @param nameFileImg is path of file not in correct size
     */
    @Description("Verify that error message is shown after loading photo in incorrect size and message disappear after input correct image")
    @Test(dataProvider = "negativeProviderLoadImage")
    public void notLoadImageIncorrectSize(String nameFileImg) {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.loadImage(nameFileImg);
        softAssert.assertTrue(isElementPresent(addEventPage.getFileUploader()),"The photo was uploaded by mistake");
        softAssert.assertTrue(isElementPresent(addEventPage.getErrorIncorrectPhotoSize()),"ErrorIncorrectPhotoSize isn't present");
        softAssert.assertEquals(addEventPage.getErrorIncorrectPhotoSize().getText(), getDataByKey("errorIncorrectPhotoSize"),"Incorrect text of ErrorIncorrectPhotoSize");
        addEventPage.loadImage(getDataByKey("correctPhoto"));
        softAssert.assertFalse(isElementPresent(addEventPage.getErrorIncorrectPhotoSize()),"ErrorIncorrectPhotoSize doesn't disappear");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] negativeProviderInputTitle() {
        return new Object[][]{{getDataByKey("smallTitle")}, {getDataByKey("bigTitle")}};
    }

    /**
     * Verify that error message is shown after input text in incorrect size in field "Title"
     * and message disappear after input correct title
     * @param text
     */
    @Description("Verify that error message is shown after input text in incorrect size in field \"Title\" and message disappear after input correct title")
    @Test(dataProvider = "negativeProviderInputTitle")
    public void negativeInputTitle(String text) {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.inputTitle(text);
        softAssert.assertTrue(isElementPresent(addEventPage.getErrorIncorrectTitle()),"ErrorIncorrectTitle isn't present");
        softAssert.assertEquals(addEventPage.getErrorIncorrectTitle().getText(), getDataByKey("errorIncorrectTitle"),"Incorrect text of ErrorIncorrectTitle");
        addEventPage.inputTitle(getDataByKey("correctTitle"));
        softAssert.assertFalse(isElementPresent(addEventPage.getErrorIncorrectTitle()),"ErrorIncorrectTitle doesn't disappear");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] negativeProviderInputTitleNoOneLetter() {
        return new Object[][]{{getDataByKey("noOneLetterInTitle")}};
    }

    /**
     * Verify that error message is shown after input text without letter in field "Title"
     * and message disappear after input correct title
     * @param text
     */
    @Description("Verify that error message is shown after input text without letter in field \"Title\" and message disappear after input correct title")
    @Test(dataProvider = "negativeProviderInputTitleNoOneLetter")
    public void negativeInputTitleNoOneLetter(String text) {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.inputTitle(text);
        softAssert.assertTrue(isElementPresent(addEventPage.getErrorIncorrectTitleNoOneLetter()),"ErrorIncorrectTitleNoOneLetter isn't present");
        softAssert.assertEquals(addEventPage.getErrorIncorrectTitleNoOneLetter().getText(), getDataByKey("errorIncorrectTitleNoOneLetter"),"Incorrect text of ErrorIncorrectTitleNoOneLetter");
        addEventPage.inputTitle(getDataByKey("correctTitle"));
        softAssert.assertFalse(isElementPresent(addEventPage.getErrorIncorrectTitleNoOneLetter()),"ErrorIncorrectTitleNoOneLetter doesn't disappear");
        softAssert.assertAll();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerTooManyParticipants() {
        return new Object[][]{{getDataByKey("tooManyParticipants")}};
    }

    /**
     * Verify that error message is shown after input too many participants in field "Participants"
     * and message disappear after input correct amount of participants
     * @param value
     */
    @Description("Verify that error message is shown after input too many participants in field \"Participants\" and message disappear after input correct amount of participants")
    @Test(dataProvider = "providerTooManyParticipants")
    public void negativeTooManyParticipants(String value) {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.inputParticipants(value);
        softAssert.assertTrue(isElementPresent(addEventPage.getErrorTooManyParticipants()),"ErrorTooManyParticipants isn't present");
        softAssert.assertEquals(addEventPage.getErrorTooManyParticipants().getText(), getDataByKey("errorTooManyParticipants"),"Incorrect text of ErrorTooManyParticipants");
        addEventPage.inputParticipants(getDataByKey("correctParticipants"));
        softAssert.assertFalse(isElementPresent(addEventPage.getErrorTooManyParticipants()),"ErrorTooManyParticipants doesn't disappear");
        softAssert.assertAll();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateFrom() {
        return new Object[][]{{getDataByKey("incorrectMonthDateFrom")}, {getDataByKey("incorrectDayDateFrom")},
                {getDataByKey("incorrectYearDateFrom")}, {getDataByKey("notInFormatDateFrom")},
                {getDataByKey("beforeThanPresentDateFrom")}, {""}};
    }

    /**
     * Verify entering incorrect data in the field date from
     * @param date
     */
    @Description("Verify entering incorrect data in the field date from")
    @Test(dataProvider = "dataProviderDateFrom")
    public void negativeInputDateFrom(String date) {
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(date);
        assertEquals(addEventPage.getValueAttribute(addEventPage.getDateFrom()),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                "Incorrect date doesn't convert to current date");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateTo() {
        return new Object[][]{{getDataByKey("incorrectMonthDateTo")}, {getDataByKey("incorrectDayDateTo")},
                {getDataByKey("incorrectYearDateTo")},
                {getDataByKey("notInFormatDateTo")}, {getDataByKey("beforeThanInFrom")}, {""}};
    }

    /**
     * Verify entering incorrect data in the field date to
     * @param date
     */
    @Description("Verify entering incorrect data in the field date to")
    @Test(dataProvider = "dataProviderDateTo")
    public void negativeInputDateTo(String date) {
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(LocalDate.now().toString());
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(date);
        assertEquals(addEventPage.getValueAttribute(addEventPage.getDateTo()),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                "Incorrect date doesn't convert to date in field \"From\"");
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputDescription() {
        return new Object[][]{{getDataByKey("smallDescription")}, {getDataByKey("bigDescription")}};
    }

    /**
     * Verify that error message is shown after input text in incorrect size in field "Description"
     * and message disappear after input correct description
     * @param text
     */
    @Description("Verify that error message is shown after input text in incorrect size in field \"Description\" and message disappear after input correct description")
    @Test(dataProvider = "negativeProviderInputDescription")
    public void negativeInputDescription(String text) {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.inputDescription(text);
        softAssert.assertTrue(isElementPresent(addEventPage.getErrorIncorrectDescription()),"ErrorIncorrectDescription isn't present");
        softAssert.assertEquals(addEventPage.getErrorIncorrectDescription().getText(), getDataByKey("errorIncorrectDescription"),"Incorrect text of ErrorIncorrectDescription");
        addEventPage.inputDescription(getDataByKey("correctDescription"));
        softAssert.assertFalse(isElementPresent(addEventPage.getErrorIncorrectDescription()),"ErrorIncorrectDescription doesn't disappear");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] negativeProviderInputHashtags() {
        List<String> hashtags = Arrays.asList(getDataByKey("tooManyHashtags").split(","));
        return new Object[][]{{hashtags}};
    }

    /**
     * Verify that error message is shown after input too many hashtags in field "Hashtags"
     * and message disappear after input correct hashtags
     * @param hashtags
     */
    @Description("Verify that error message is shown after input too many hashtags in field \"Hashtags\" and message disappear after input correct hashtags")
    @Test(dataProvider = "negativeProviderInputHashtags")
    public void negativeInputHashtags(List<String> hashtags) {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.inputHashtags(hashtags);
        softAssert.assertTrue(isElementPresent(addEventPage.getErrorTooManyHashtags()),"ErrorTooManyHashtags isn't present");
        softAssert.assertEquals(addEventPage.getErrorTooManyHashtags().getText(), getDataByKey("errorTooManyHashtags"),"Incorrect text of ErrorTooManyHashtags");
        List<String> hashtagsCorrect = Arrays.asList(getDataByKey("correctHashtags").split(","));
        addEventPage.inputHashtags(hashtagsCorrect);
        softAssert.assertFalse(isElementPresent(addEventPage.getErrorTooManyHashtags()),"ErrorTooManyHashtags doesn't disappear");
        softAssert.assertAll();
    }

    /**
     * Verify that error message "Required" is shown after clicking "Save" and
     * disappearing these messages after entering the correct value
     */
    @Description("Verify that error message \"Required\" is shown after clicking \"Save\" and disappearing these messages after entering the correct value")
    @Test
    public void testRequiredErrors() {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.clickSave();

        softAssert.assertTrue(isElementPresentWait(addEventPage.getRequiredImage()),"RequiredImage isn't present");
        softAssert.assertEquals(addEventPage.getRequiredImage().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredImage");
        addEventPage.loadImage(getDataByKey("correctPhoto"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredImage()),"RequiredImage doesn't disappear");

        softAssert.assertTrue(isElementPresentWait(addEventPage.getRequiredTitle()),"RequiredTitle isn't present");
        softAssert.assertEquals(addEventPage.getRequiredTitle().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredTitle");
        addEventPage.inputTitle(getDataByKey("correctTitle"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredTitle()),"RequiredTitle doesn't disappear");

        softAssert.assertTrue(isElementPresentWait(addEventPage.getRequiredDescription()),"RequiredDescription isn't present");
        softAssert.assertEquals(addEventPage.getRequiredDescription().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredDescription");
        addEventPage.inputDescription(getDataByKey("correctDescription"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredDescription()),"RequiredDescription doesn't disappear");

        softAssert.assertTrue(isElementPresentWait(addEventPage.getRequiredHashtags()),"RequiredHashtag isn't present");
        softAssert.assertEquals(addEventPage.getRequiredHashtags().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredHashtag");
        List<String> hashtagsCorrect = Arrays.asList(getDataByKey("correctHashtags").split(","));
        addEventPage.inputHashtags(hashtagsCorrect);
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredHashtags()),"RequiredHashtag doesn't disappear");

        softAssert.assertTrue(isElementPresentWait(addEventPage.getRequiredCountry()),"RequiredCountry isn't present");
        softAssert.assertEquals(addEventPage.getRequiredCountry().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredCountry");
        addEventPage.inputCountry(getDataByKey("correctCountry"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredCountry()),"RequiredCountry doesn't disappear");

        softAssert.assertAll();
    }

    /**
     * Verify that error message "Required" is shown after clicking "Save" without data in the field "City" and
     * disappearing these messages after entering the correct value
     */
    @Description("Verify that error message \"Required\" is shown after clicking \"Save\" without data in the field disappearing these messages after entering the correct value")
    @Test
    public void testRequiredCity() {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.inputCountry(getDataByKey("correctCountry"));
        addEventPage.clickSave();
        softAssert.assertTrue(isElementPresent(addEventPage.getRequiredCity()),"RequiredCity isn't present");
        softAssert.assertEquals(addEventPage.getRequiredCity().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredCity");
        addEventPage.initCity();
        addEventPage.inputCity(getDataByKey("correctCity"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredCity()),"RequiredCity doesn't disappear");
        softAssert.assertAll();
    }
}