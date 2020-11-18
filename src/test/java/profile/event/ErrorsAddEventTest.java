package profile.event;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.base.Helper;
import pages.profile.AddEventPage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import static org.awaitility.Awaitility.fieldIn;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.base.Helper.isElementPresent;

public class ErrorsAddEventTest extends AddEventBaseTest {


    @DataProvider
    public Object[][] negativeProviderLoadImage() {
        return new Object[][]{{getDataByKey("incorrectFormatPhoto")}};
    }

    @Test(dataProvider = "negativeProviderLoadImage")
    public void notLoadImageIncorrectFormat(String nameFileImg) {
        addEventPage.loadImage(nameFileImg);
        assertTrue(isElementPresent(addEventPage.getFileUploader()),"The photo was uploaded by mistake");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerIncorrectResolution() {
        return new Object[][]{{getDataByKey("smallResolutionPhoto")}, {getDataByKey("bigResolutionPhoto")}};
    }

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

    @Test(dataProvider = "dataProviderDateFrom")
    public void negativeInputDateFrom(String date) {
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(date);
        assertEquals(addEventPage.getValueAttribute(addEventPage.getDateFrom()), AddEventPage.convertDateToCorrect(date, LocalDate.now()));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderDateTo() {
        return new Object[][]{{getDataByKey("incorrectMonthDateTo")}, {getDataByKey("incorrectDayDateTo")},
                {getDataByKey("incorrectYearDateTo")},
                {getDataByKey("notInFormatDateTo")}, {getDataByKey("beforeThanInFrom")}, {""}};
    }

    @Test(dataProvider = "dataProviderDateTo")
    public void negativeInputDateTo(String date) {
        addEventPage.clearDateFrom();
        addEventPage.inputDateFrom(LocalDate.now().toString());
        addEventPage.clearDateTo();
        addEventPage.inputDateTo(date);
        assertEquals(addEventPage.getValueAttribute(addEventPage.getDateTo()), AddEventPage.convertDateToCorrect(date, LocalDate.now()));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] negativeProviderInputDescription() {
        return new Object[][]{{getDataByKey("smallDescription")}, {getDataByKey("bigDescription")}};
    }

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


    @Test
    public void testRequiredErrors() {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.clickSave();

        conditionFactory.await().until(() -> isElementPresent(addEventPage.getRequiredImage()));
        softAssert.assertTrue(Helper.isElementPresent(addEventPage.getRequiredImage()),"RequiredImage isn't present");
        softAssert.assertEquals(addEventPage.getRequiredImage().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredImage");
        addEventPage.loadImage(getDataByKey("correctPhoto"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredImage()),"RequiredImage doesn't disappear");

        conditionFactory.await().until(() -> isElementPresent(addEventPage.getRequiredTitle()));
        softAssert.assertTrue(Helper.isElementPresent(addEventPage.getRequiredTitle()),"RequiredTitle isn't present");
        assertEquals(addEventPage.getRequiredTitle().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredTitle");
        addEventPage.inputTitle(getDataByKey("correctTitle"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredTitle()),"RequiredTitle doesn't disappear");

        conditionFactory.await().until(() -> isElementPresent(addEventPage.getRequiredDescription()));
        softAssert.assertTrue(Helper.isElementPresent(addEventPage.getRequiredDescription()),"RequiredDescription isn't present");
        assertEquals(addEventPage.getRequiredDescription().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredDescription");
        addEventPage.inputDescription(getDataByKey("correctDescription"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredDescription()),"RequiredDescription doesn't disappear");

        conditionFactory.await().until(() -> isElementPresent(addEventPage.getRequiredHashtags()));
        softAssert.assertTrue(Helper.isElementPresent(addEventPage.getRequiredHashtags()),"RequiredHashtag isn't present");
        assertEquals(addEventPage.getRequiredHashtags().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredHashtag");
        List<String> hashtagsCorrect = Arrays.asList(getDataByKey("correctHashtags").split(","));
        addEventPage.inputHashtags(hashtagsCorrect);
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredHashtags()),"RequiredHashtag doesn't disappear");

        conditionFactory.await().until(() -> isElementPresent(addEventPage.getRequiredCountry()));
        softAssert.assertTrue(Helper.isElementPresent(addEventPage.getRequiredCountry()),"RequiredCountry isn't present");
        assertEquals(addEventPage.getRequiredCountry().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredCountry");
        addEventPage.inputCountry(getDataByKey("correctCountry"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredCountry()),"RequiredCountry doesn't disappear");

        softAssert.assertAll();
    }


    @Test
    public void testRequiredCity() {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.inputCountry(getDataByKey("correctCountry"));
        addEventPage.clickSave();
        softAssert.assertTrue(Helper.isElementPresent(addEventPage.getRequiredCity()),"RequiredCity isn't present");
        assertEquals(addEventPage.getRequiredCity().getText(), getDataByKey("errorRequired"),"Incorrect text of RequiredCity");
        addEventPage.initCity();
        addEventPage.inputCity(getDataByKey("correctCity"));
        softAssert.assertFalse(isElementPresent(addEventPage.getRequiredCity()),"RequiredCity doesn't disappear");
        softAssert.assertAll();
    }
}