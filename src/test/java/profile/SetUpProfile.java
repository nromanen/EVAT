package profile;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInUpMenu;
import utility.SetUpDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SetUpProfile extends SetUpDriver {



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

    public static void signingIn() {
        driver.get(prop.getProperty("homePage"));
        new SignInUpMenu(driver).authoriseUser(prop.getProperty("email"),prop.getProperty("password"));
    }

    public static void goToProfilePage(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div.left-sidebar-closed.left-sidebar > nav > ul > li:nth-child(2) > a > span")));
        driver.get(prop.getProperty("profilePage"));
    }

}
