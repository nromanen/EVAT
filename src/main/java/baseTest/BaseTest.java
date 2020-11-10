package baseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.homePageSearch.HomePageSearchMenu;
import utility.SetUpDriver;

public abstract class BaseTest {
    private static SetUpDriver setUpDriver;
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        setUpDriver = new SetUpDriver();
        driver = setUpDriver.getDriver();
        driver.manage().window().maximize();
        driver.get(HomePageSearchMenu.URL);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        setUpDriver.driverQuit();
    }

    public static WebDriver getDriver() {
        return setUpDriver.getDriver();
    }
}
