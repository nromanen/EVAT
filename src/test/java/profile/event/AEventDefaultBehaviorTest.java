package profile.event;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.base.Helper;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertFalse;


public class AEventDefaultBehaviorTest  extends AddEventBaseTest {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderLoadImage(){
        List<String> hashtags= Arrays.asList(getDataByKey("correctHashtags").split(","));
        return new Object[][]{{getDataByKey("correctPhoto"),getDataByKey("correctTitle"),
                getDataByKey("correctDateFrom"),getDataByKey("correctDateTo"),getDataByKey("correctParticipants"),
                getDataByKey("correctDescription"), hashtags,
                getDataByKey("correctCountry"),getDataByKey("correctCity"),
                Integer.parseInt(getDataByKey("amountCountry")),Integer.parseInt(getDataByKey("amountCity"))}};
    }

    /**
     * Verify input in fields
     * @param nameFileImg
     * @param title
     * @param dateFrom
     * @param dateTo
     * @param participants
     * @param description
     * @param hashtags is a list of hashtags
     * @param country
     * @param city
     * @param amountCountry
     * @param amountCity
     */

    @Test(dataProvider = "dataProviderLoadImage")
    public void testDefaultBehavior(String nameFileImg,String title,String dateFrom,String dateTo,
                                    String participants,String description,List<String> hashtags,
                                    String country,String city, int amountCountry, int amountCity) {
        SoftAssert softAssert = new SoftAssert();
        addEventPage.loadImage(nameFileImg);
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
        addEventPage.inputHashtags(hashtags);

        softAssert.assertEquals(addEventPage.getAltAttribute(addEventPage.getImage()),
                nameFileImg.substring(nameFileImg.lastIndexOf('\\') + 1), "Incorrect image on page after input");
        softAssert.assertEquals(addEventPage.getValueAttribute(addEventPage.getTitle()), title, "Incorrect title on page after input");
        softAssert.assertEquals(addEventPage.getValueAttribute(addEventPage.getParticipants()),participants,"Incorrect participants on page after input");
        softAssert.assertEquals(addEventPage.getValueAttribute(addEventPage.getDateFrom()), dateFrom, "Incorrect dateFrom on page after input");
        softAssert.assertEquals(addEventPage.getValueAttribute(addEventPage.getDateTo()),dateTo,"Incorrect dateTo on page after input");
        softAssert.assertEquals(addEventPage.getValueAttribute(addEventPage.getDescription()),description,"Incorrect description on page after input");
        softAssert.assertEquals(addEventPage.getValueOfHashtags(), hashtags,"Incorrect hashtags on page after input");
        softAssert.assertEquals(amountCountry,addEventPage.getCountriesAmountFromPage());
        softAssert.assertEquals(addEventPage.getValueOfCountry(),country,"Incorrect country on page after input");
        softAssert.assertEquals(amountCity,addEventPage.getCitiesAmountFromPage());
        softAssert.assertEquals(addEventPage.getValueOfCity(),city);
        softAssert.assertAll();
    }

    /**
     * Verify that the button "Clear" clear loaded photo
     */

    @Test
    public void testClickClear(){
        addEventPage.loadImage(getDataByKey("correctPhoto"));
        addEventPage.clickClear();
        assertFalse(Helper.isElementPresent(addEventPage.getImage()));
    }

}