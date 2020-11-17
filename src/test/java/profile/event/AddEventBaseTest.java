package profile.event;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.profile.AddEventPage;
import pages.profile.EventMenu;
import profile.ProfileBaseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AddEventBaseTest extends ProfileBaseTest {
    protected Properties testDataAE;
    protected AddEventPage addEventPage;
    protected EventMenu eventMenu;

    @BeforeClass
    public void initTestData(){
        initTestDataAE();
    }

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        if(testDataAE ==null)initTestDataAE();
        signingIn();
        goToProfilePage();
        eventMenu=new EventMenu(driver);
        eventMenu.clickAddEvent();
        addEventPage=new AddEventPage(driver);
    }

    public void initTestDataAE(){
        testDataAE =new Properties();
        try (InputStream testData = new FileInputStream("src/test/resources/testDataAE.properties") ){
            testDataAE.load(testData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDataByKey(String key){
        return testDataAE.getProperty(key);
    }

}
