package profile;

import base.BaseTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.homePage.HomePageNavBar;
import pages.homePage.SignInUpMenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ProfileBaseTest extends BaseTest {
    private Properties testDataProfile;

    @BeforeClass
    public void initTestData() {
        initTestDataProfile();
    }

    @BeforeMethod
    public void beforeMethod() {
        openBrowser();
    }


    public void initTestDataProfile(){
        testDataProfile =new Properties();
        try (InputStream enteringProp = new FileInputStream("src/test/resources/testDataEntering.properties") ){
            testDataProfile.load(enteringProp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void signingIn() {
        getDriver().get(testDataProfile.getProperty("homePage"));
        new SignInUpMenu(getDriver()).authoriseUser(testDataProfile.getProperty("email"),
                testDataProfile.getProperty("password"));
    }

    public void signingIn(String email,String password) {
        getDriver().get(testDataProfile.getProperty("homePage"));
        new SignInUpMenu(getDriver()).authoriseUser(email,password);
    }

    public  void goToProfilePage(){
        new HomePageNavBar(getDriver()).clickProfileButton();
    }

    public String getDataByKey(String key){
        return testDataProfile.getProperty(key);
    }

    @AfterMethod
    public void tearDown(ITestResult result)
    {
        closeBrowser();
    }
}
