package profile;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

import pages.SignInUpMenu;
import utility.SetUpDriver;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class EventsNotificationPageTest {
	
	private WebDriver driver;
	private SetUpDriver setUpDriver;
	private SignInUpMenu signInUpMenu;
	
	private String url = "https://eventsexpress-test.azurewebsites.net/home/events?page=1";
	private String email = "d.bozhevilnyi@gmail.com";
	private String pass = "131089";
	private By notificationsButton = By.cssSelector("#root > div.left-sidebar-closed.left-sidebar > div > div > div > div:nth-child(3) > a:nth-child(2) > button");
	private By notificationsStatus = By.cssSelector("#main > p");
	
	@BeforeTest
	public void setUp() {
		setUpDriver = new SetUpDriver();
		driver = setUpDriver.getDriver();
		signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeClass
	public void profileSetup() {
		driver.get(url);
		signInUpMenu.authoriseUser(email, pass);
		driver.findElement(notificationsButton).click();
	}

	@Test
	public void checkNotifications() {
		Assert.assertEquals(driver.findElement(notificationsStatus).getText(), "You don't have notifications");
	}

	@AfterTest(alwaysRun = true)
	public void closeUp() {
		setUpDriver.driverQuit();
	}
}
