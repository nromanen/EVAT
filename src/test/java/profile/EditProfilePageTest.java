package profile;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.navBar.EditProfilePage;
import utility.SetUpDriver;

public class EditProfilePageTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private SetUpDriver setUpDriver;
	private EditProfilePage editProfilePage;
	
	private String url = "https://eventsexpress.azurewebsites.net/home/events?page=1";
	private By signInUpButton = By.xpath("//span[contains(text(),'Sign In/Up')]");
	private By email = By.name("email");
	private By password = By.name("password");
	private By signin = By.cssSelector(".MuiDialogActions-root > button:nth-child(2)");
	private By editYourProfileButton = By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div[2]/a[1]/button/span[1]");
	private String imagePath = "C:\\resources\\img.jpg";

	@BeforeTest
	public void setUp() {
		setUpDriver = new SetUpDriver();
		driver = setUpDriver.getDriver();
		wait = setUpDriver.getWebDriverWait();
		editProfilePage = PageFactory.initElements(driver, EditProfilePage.class);
		driver.manage().window().maximize();
	}

	@BeforeClass
	public void profileSetup() {
		driver.get(url);
		driver.findElement(signInUpButton).click();
		driver.findElement(email).sendKeys("d.bozhevilnyi@gmail.com");
		driver.findElement(password).sendKeys("131089");
		driver.findElement(signin).click();
		wait.until(ExpectedConditions.elementToBeClickable(editYourProfileButton));
		driver.findElement(editYourProfileButton).click();
	}
	
	@Test
	public void changeAvatarTest() {
		editProfilePage.changeAvatar(imagePath);
		wait.until(ExpectedConditions.visibilityOf(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Avatar is update");
	}
	
	@Test
	public void changeUsernameTest() {
		editProfilePage.changeUserName("Saul");
		wait.until(ExpectedConditions.visibilityOf(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Username is changed");
	}
	
	@Test
	public void chooseGenderTest() {
		editProfilePage.chooseGender("Male");
		wait.until(ExpectedConditions.visibilityOf(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Set gender successed");
	}
	
	@Test
	public void setDateOfBirthTest() {
		editProfilePage.setDateOfBirth(LocalDate.of(1989, 10, 13));
		wait.until(ExpectedConditions.visibilityOf(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Set date of birth successed");
	}
	
	@Test
	public void chooseFavoriteCategoriesTest() throws InterruptedException {
		editProfilePage.chooseFavoriteCategories("Summer");
		wait.until(ExpectedConditions.visibilityOf(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Favarote categoris is updated");
	}
	
	@Test
	public void changePasswordTest() {
		editProfilePage.changePassword("1234", "1234", "1234");
		wait.until(ExpectedConditions.visibilityOf(editProfilePage.clientSnackbar));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed");
	}
	
	@AfterTest
	public void closeUp() {
		setUpDriver.driverQuit();
	}
}
