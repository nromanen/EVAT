package base;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.fieldIn;

public abstract class BaseTest {
    protected WebDriver driver;
    private final Properties prop = new Properties();
    protected WebDriverWait webDriverWait;
    private static final int TIMEOUT = 100;
    protected ConditionFactory conditionFactory;




    @BeforeClass
    public void setUp(){
        try (InputStream input = new FileInputStream("src/main/resources/driver.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.setProperty(prop.getProperty("webDriverKey"),prop.getProperty("webDriverValue"));

        if(prop.getProperty("browser").equals("chrome")) driver = new ChromeDriver();
        else
            if(prop.getProperty("browser").equals("gecko"))driver = new FirefoxDriver();

        webDriverWait= new WebDriverWait(driver,TIMEOUT);

        driver.manage().window().maximize();

        conditionFactory= await().atMost(TIMEOUT,TimeUnit.SECONDS);

    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }


}
