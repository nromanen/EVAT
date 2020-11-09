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

    private  Properties prop = new Properties();
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public SetUpDriver() {
        try (InputStream input = new FileInputStream("src/main/resources/driver.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.setProperty(prop.getProperty("webDriverKey"),prop.getProperty("webDriverValue"));
        if(prop.getProperty("browser").equals("chrome")) driver = new ChromeDriver();
        else if(prop.getProperty("browser").equals("gecko"))driver = new FirefoxDriver();
        webDriverWait= new WebDriverWait(driver,100);
    }

    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    public void tearDown(){
        driver.close();
    }

    public void driverQuit(){
        if (null != driver){
            driver.quit();
        }
        driver = null;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public void setWebDriverWait(WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
    }
}