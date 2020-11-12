package profile;

import baseTest.BaseTest;
import org.testng.annotations.BeforeClass;
import pages.HomePageNavBar;
import pages.SignInUpMenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ProfileBaseTest extends BaseTest {
    private final Properties propertiesSetUpProfile = new Properties();
    protected final Properties prop = new Properties();

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        try (InputStream enteringProp = new FileInputStream("src/test/resources/forProfile/testDataEntering.properties");
             InputStream testDataProfileProp = new FileInputStream("src/test/resources/forProfile/testDataProfile.properties")) {
            propertiesSetUpProfile.load(enteringProp);
            prop.load(testDataProfileProp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        signingIn();
        goToProfilePage();
    }

    public void signingIn() {
        driver.get(propertiesSetUpProfile.getProperty("homePage"));
        new SignInUpMenu(driver).authoriseUser(propertiesSetUpProfile.getProperty("email"),
                propertiesSetUpProfile.getProperty("password"));
    }

    public  void goToProfilePage(){
        new HomePageNavBar(driver).clickProfileButton();
    }

}
