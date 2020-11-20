package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utility.ScreenshotUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public abstract class BaseTest {
    protected WebDriver driver;
    private final Properties prop = new Properties();
    protected WebDriverWait webDriverWait;
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

    private WebDriver initDriver(){
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

    public WebDriver getDriver() {
        if(driver==null) driver=initDriver();
        return driver;
    }

    public void takeScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtility.captureScreenshot(driver,result.getName()+"-"+
                    (result.getMethod().getCurrentInvocationCount()-1)+
                    result.getTestClass().getName());
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser(){
        if(driver!=null) driver.close();
        driver=null;
    }
}
