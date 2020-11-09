package profile;

import pages.HomePageNavBar;
import pages.SignInUpMenu;
import utility.SetUpDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetUpProfile extends SetUpDriver {

    private final Properties propertiesSetUpProfile = new Properties();
    private final Properties prop = new Properties();

    public SetUpProfile() {
        super();
        try (InputStream enteringProp = new FileInputStream("src/test/resources/forProfile/entering.properties");
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
        new SignInUpMenu(driver).authoriseUser(propertiesSetUpProfile.getProperty("email"), propertiesSetUpProfile.getProperty("password"));
    }

    public  void goToProfilePage(){
        new HomePageNavBar(driver).clickProfileButton();
    }

    public void driverQuit(){
        driver.quit();
    }

    @Override
    public Properties getProp() {
        return prop;
    }
}
