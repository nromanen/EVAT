package profile;

import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePageNavBar;
import pages.SignInUpMenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ProfileBaseTest extends BaseTest {
    private Properties testDataProfile;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        if(testDataProfile ==null)initTestDataProfile();
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
        driver.get(testDataProfile.getProperty("homePage"));
        new SignInUpMenu(driver).authoriseUser(testDataProfile.getProperty("email"),
                testDataProfile.getProperty("password"));
    }

    public void signingIn(String email,String password) {
        driver.get(testDataProfile.getProperty("homePage"));
        new SignInUpMenu(driver).authoriseUser(email,password);
    }

    public  void goToProfilePage(){
        new HomePageNavBar(driver).clickProfileButton();
    }

    public String getDataByKey(String key){
        return testDataProfile.getProperty(key);
    }

    @AfterMethod
    public void closeBrowser(){
        super.closeBrowser();
    }
}
