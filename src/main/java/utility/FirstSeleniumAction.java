package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchMenu;
import pages.SignInUpMenu;


public class FirstSeleniumAction {

    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.gecko.driver", "/Users/illyashulman/EventExpress/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://eventsexpress-test.azurewebsites.net/home/events?page=1");
        String email = "carat98@icloud.com";
        String password = "090909iS";

        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.registerUser(email,password,password);


//        SearchMenu searchMenu = new SearchMenu(driver);
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOf(searchMenu.getEventAvatar()));
//        searchMenu.clickKeyword();
//        searchMenu.setKeyword("за семочками");
//        searchMenu.clickSearch();


    }
}
