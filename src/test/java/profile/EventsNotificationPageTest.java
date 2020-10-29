package profile;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class EventsNotificationPageTest {
	
	private WebDriver driver;
	
	private String url = "https://eventsexpress-test.azurewebsites.net/home/events?page=1";
	private By signInUpButton = By.xpath("//span[contains(text(),'Sign In/Up')]");
	private By email = By.name("email");
	private By password = By.name("password");
	private By signin = By.cssSelector(".MuiDialogActions-root > button:nth-child(2)");
	private By notificationsButton = By.cssSelector("#root > div.left-sidebar-closed.left-sidebar > div > div > div > div:nth-child(3) > a:nth-child(2) > button");
	private By notificationsStatus = By.cssSelector("#main > p");
	
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeTest
	public void profileSetup() {
		driver.manage().window().maximize();
		driver.get(url);
		driver.findElement(signInUpButton).click();
		driver.findElement(email).sendKeys("d.bozhevilnyi@gmail.com");
		driver.findElement(password).sendKeys("131089");
		driver.findElement(signin).click();
		driver.findElement(notificationsButton).click();
	}

	@Test
	public void checkNotifications() {
		Assert.assertEquals(driver.findElement(notificationsStatus).getText(), "You don't have notifications");
	}

	@AfterTest
	public void closeUp() {
		driver.close();
	}
}
