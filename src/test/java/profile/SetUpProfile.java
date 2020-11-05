package profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePageNavBar;
import pages.SignInUpMenu;
import utility.SetUpDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetUpProfile extends SetUpDriver {

    private Properties prop = new Properties();

    public SetUpProfile() {
        super();
        try (InputStream input = new FileInputStream("src/test/resources/forProfile/entering.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        signingIn();
        goToProfilePage();

    }

    public void signingIn() {
        driver.get(prop.getProperty("homePage"));
        new SignInUpMenu(driver).authoriseUser(prop.getProperty("email"),prop.getProperty("password"));
    }

    public  void goToProfilePage(){
        new HomePageNavBar(driver).clickProfileButton();
    }

    public void driverQuit(){
        driver.quit();
    }
}
