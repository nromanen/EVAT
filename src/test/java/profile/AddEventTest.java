package profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.profile.eventsMenuPages.AddEventPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddEventTest {

    protected static WebDriver driver;
    public static WebDriverWait wait=new WebDriverWait(driver, 50);
    protected static AddEventPage addEventPage;

    @BeforeClass
    public static void setUpPageAddEvent() throws FileNotFoundException {
        System.setProperty("webdriver.chrome.driver","D:/moe/my_projects_on_java/softserve/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://eventsexpress-test.azurewebsites.net/home/events?page=1");
        Scanner scanner=new Scanner(new File("src/test/resources/property.txt"));
        String email = null;
        String password = null;
        while (scanner.hasNextLine()){
            String str=scanner.nextLine();
            if(str.startsWith("email")){
                email=str.substring(str.indexOf("="));
            } else if(str.startsWith("password")){
                password=str.substring(str.indexOf("="));
            }
        }
        //new SignInPage(driver,email,password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiCardHeader-root")));
        driver.get("https://eventsexpress-test.azurewebsites.net/user/9b90b6b0-6c92-4676-b3f3-08d866c8a89a");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#full-width-tab-4 > span.MuiTab-wrapper")));
        WebElement buttonAddEvent=driver.findElement(By.cssSelector("#full-width-tab-4 > span.MuiTab-wrapper"));
        buttonAddEvent.click();
        addEventPage=new AddEventPage(driver);
    }
     @Test
    public static void ads(){

     }


}
