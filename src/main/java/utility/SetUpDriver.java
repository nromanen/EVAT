package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SetUpDriver {

    protected static Properties prop = new Properties();
    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;

    private SetUpDriver() { }

    public static WebDriver getWebDriver(){
        if(driver==null) {
            try (InputStream input = new FileInputStream("src/main/resources/driver.properties")) {
                prop.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.setProperty(prop.getProperty("webDriverKey"), prop.getProperty("webDriverValue"));
            switch (prop.getProperty("browser")) {
                case "gecko":
                    driver = new FirefoxDriver(); break;
                default:
                    driver = new ChromeDriver();
            }
        }
        webDriverWait= new WebDriverWait(driver,30);
        return driver;
    }

    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    public static void tearDown(){
        driver.close();
    }

    public static void driverQuit(){
        driver.quit();
    }

    public static Properties getProp() {
        return prop;
    }

    public static void setProp(Properties prop) {
        SetUpDriver.prop = prop;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        SetUpDriver.driver = driver;
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public static void setWebDriverWait(WebDriverWait webDriverWait) {
        SetUpDriver.webDriverWait = webDriverWait;
    }
}