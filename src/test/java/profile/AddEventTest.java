package profile;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.profile.EventMenu;
import pages.profile.eventsMenuPages.AddEventPage;

import java.io.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

import static org.testng.Assert.assertTrue;

public class AddEventTest {

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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] dataProviderLoadImage(){
        String[] hashtags=prop.getProperty("correctHashtags").split(",");
        return new Object[][]{{prop.getProperty("correctPhoto"),prop.getProperty("correctTitle"), LocalDate.parse(prop.getProperty("correctDataFrom")),
                LocalDate.parse(prop.getProperty("correctDataTo")),prop.getProperty("correctDescription"),
                hashtags,prop.getProperty("correctCountry"),prop.getProperty("correctCity")}};//TODO: change absolute path in the file testData
    }


    @Test(dataProvider = "dataProviderLoadImage")
    public void testSaveCorrectData(String nameFileImg,String title, LocalDate dateFrom, LocalDate dateTo,
                                    String description,String[] hashtagsToEnter,String country,String city){
        addEventPage.loadImage(nameFileImg);
        addEventPage.inputTitle(title);
        addEventPage.inputDateFrom(dateFrom);
        addEventPage.inputDateTo(dateTo);
        addEventPage.inputDescription(description);
        addEventPage.inputHashtags(hashtagsToEnter);
        addEventPage.inputCountry(country);
        addEventPage.inputCity(city);
        assertTrue(addEventPage.clickSave());
    }



}
