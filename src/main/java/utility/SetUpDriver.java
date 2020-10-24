package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SetUpDriver {

    public static WebDriver driver;
    public static Properties prop = new Properties();

    public static void setUpDriver(){
        try (InputStream input = new FileInputStream("src/driver.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.setProperty(prop.getProperty("webDriverKey"),prop.getProperty("webDriverValue"));
        if(prop.getProperty("browser").equals("chrome")) driver = new ChromeDriver();
        else if(prop.getProperty("browser").equals("gecko"));
    }


    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    public static void tearDown(){
        driver.close();
    }
}