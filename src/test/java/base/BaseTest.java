package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Listeners(TestListener.class)
public abstract class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;
    private static final Properties prop = new Properties();
    private static final int TIMEOUT = 100;

    @BeforeClass
    public void setUp(){
        try (InputStream input = new FileInputStream("src/main/resources/driver.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.setProperty(prop.getProperty("webDriverKey"),prop.getProperty("webDriverValue"));
    }

    private static WebDriver initDriver(){
        switch (prop.getProperty("browser")){
            case "chrome": return new ChromeDriver();
            case "gecko":  return new FirefoxDriver();
            default: throw new NullPointerException("There is no properties for webdriver: "+prop.getProperty("browser"));
        }
    }

    public void openBrowser(){
        getDriver();
        webDriverWait= new WebDriverWait(driver,TIMEOUT);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        if(driver==null) driver=initDriver();
        return driver;
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser(){
        if(driver!=null) driver.close();
        driver=null;
    }
}
