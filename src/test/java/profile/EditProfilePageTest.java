package profile;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.navBar.EditProfilePage;

class EditProfilePageTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private EditProfilePage editProfilePage;
	
	private String url = "https://eventsexpress-test.azurewebsites.net/home/events?page=1";
	private By signInUpButton = By.xpath("//span[contains(text(),'Sign In/Up')]");
	private By email = By.name("email");
	private By password = By.name("password");
	private By signin = By.cssSelector(".MuiDialogActions-root > button:nth-child(2)");
	private By editYourProfileButton = By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div[2]/a[1]/button/span[1]");
	private String imagePath = "C:\\Users\\Денис\\Desktop\\TAQC\\img.jpg";

	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		System.out.println("The setup process is completed");
	}

	@BeforeTest
	public void profileSetup() {
		driver.manage().window().maximize();
		System.out.println("The profile setup process is completed");
	}
	
	@BeforeClass
	public void appSetup() {
		driver.get(url);
		System.out.println("The app setup process is completed");
		
		driver.findElement(signInUpButton).click();
		driver.findElement(email).sendKeys("d.bozhevilnyi@gmail.com");
		driver.findElement(password).sendKeys("131089");
		driver.findElement(signin).click();
		driver.findElement(editYourProfileButton).click();
		System.out.println("The login process is completed");
	}
	
	@Test
	public void changeAvatarTest() {
		editProfilePage = new EditProfilePage(driver);
		editProfilePage.changeAvatar(imagePath);
		wait.until(ExpectedConditions.visibilityOfAllElements(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Avatar is update");
	}
	
	@Test
	public void changeUsernameTest() {
		editProfilePage = new EditProfilePage(driver);
		editProfilePage.changeUserName("Saul");
		wait.until(ExpectedConditions.visibilityOfAllElements(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Username is changed");
	}
	
	@Test
	public void chooseGenderTest() {
		editProfilePage = new EditProfilePage(driver);
		editProfilePage.chooseGender("Male");
		wait.until(ExpectedConditions.visibilityOfAllElements(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed"); // TODO
	}
	
	@Test
	public void setDateOfBirthTest() {
		editProfilePage = new EditProfilePage(driver);
		editProfilePage.setDateOfBirth(LocalDate.of(1989, 10, 13));
		wait.until(ExpectedConditions.visibilityOfAllElements(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed"); // TODO
	}
	
	@Test
	public void chooseFavoriteCategoriesTest() {
		editProfilePage = new EditProfilePage(driver);
		editProfilePage.chooseFavoriteCategories("Sea");
		wait.until(ExpectedConditions.visibilityOfAllElements(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed"); // TODO
	}
	
	@Test
	public void changePasswordTest() {
		editProfilePage = new EditProfilePage(driver);
		editProfilePage.changePassword("1234", "1234", "1234");
		wait.until(ExpectedConditions.visibilityOfAllElements(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed");
	}
	
	@AfterClass
	public void closeUp() {
		driver.close();
		System.out.println("The close_up process is completed");
	}
}
