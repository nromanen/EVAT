package search;

import jdbc.SearchMenuRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SearchMenu;

import java.sql.SQLException;


public class SearchMenuTests {
    String driverPath = "/Users/illyashulman/EventExpress/geckodriver";
    WebDriver driver;
    SearchMenu searchMenu;

    @BeforeTest
    void setup() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get("https://eventsexpress-test.azurewebsites.net/home/events?page=1");
    }

    @Test
    void testSearchResultByTitle() throws SQLException {
        String keyword = "Visit El Teide";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div[1]/div[1]/a/div")));
        searchMenu = new SearchMenu(driver);
        searchMenu.clickKeyword();
        searchMenu.setKeyword(keyword);
        searchMenu.clickSearch();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiCardHeader-root")));

        Assert.assertEquals(driver.findElements(By.className("MuiCardHeader-root")).size(), SearchMenuRepository.getNumberOfEventsByTitle(keyword));
    }
}
