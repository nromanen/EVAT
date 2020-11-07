package profile;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.EventInfoPage;
import pages.SignInUpMenu;
import utility.SetUpDriver;

public class EventInfoPageTest {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private SetUpDriver setUpDriver;
	private EventInfoPage eventInfoPage;
	private SignInUpMenu signInUpMenu;
	
	private String url = "https://eventsexpress-test.azurewebsites.net/home/events?page=1";
	private String email = "d.bozhevilnyi@gmail.com";
	private String pass = "131089";
	private By eventInfoButton = By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div/div[4]/div/div[3]/a/button");
	private String comment = "auto generated comment";

	@BeforeTest
	public void setUp() {
		setUpDriver = new SetUpDriver();
		driver = setUpDriver.getDriver();
		wait = setUpDriver.getWebDriverWait();
		eventInfoPage = PageFactory.initElements(driver, EventInfoPage.class);
		signInUpMenu = PageFactory.initElements(driver, SignInUpMenu.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeClass
	public void profileSetup() {
		driver.get(url);
		signInUpMenu.authoriseUser(email,pass);
		wait.until(ExpectedConditions.elementToBeClickable(eventInfoButton));
		driver.findElement(eventInfoButton).click();
	}
	
	@Test(priority = 1)
	public void addComment() {
		eventInfoPage.addCommentToEvent(comment);
		Assert.assertEquals(eventInfoPage.getCommentText(), "auto generated comment");
	}
	
	@Test(priority = 2)
	public void replyOnComment() {
		eventInfoPage.replyOnComment(comment);
		Assert.assertEquals(eventInfoPage.getCommentText(), "auto generated comment");
	}
	
	@Test(priority = 3)
	public void joinEvent() {
		eventInfoPage.joinEvent();
		wait.until(ExpectedConditions.elementToBeClickable(eventInfoPage.leaveEventButton));
		Assert.assertEquals(eventInfoPage.joinEventStatusText(), "Leave");
	}
	
	@Test(priority = 4)
	public void leaveEvent() {
		eventInfoPage.leaveEvent();
		wait.until(ExpectedConditions.elementToBeClickable(eventInfoPage.joinEventButton));
		Assert.assertEquals(eventInfoPage.joinEventStatusText(), "Join");
	}
	
	@AfterClass
	public void cleanUp() {
		eventInfoPage.clickOnDeleteCommentButton();
	}

	@AfterTest(alwaysRun = true)
	public void closeUp() {
		setUpDriver.driverQuit();
	}
}
