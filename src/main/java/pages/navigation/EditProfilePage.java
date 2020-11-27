package pages.navigation;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;


public class EditProfilePage extends BasePage {

	public EditProfilePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#main>div>div:nth-child(1)")
	WebElement changeAvatarField;

	@FindBy(css = "img.pic")
	WebElement occupiedAvatarField;

	@FindBy(css = ".uk-button")
	WebElement clearAvatarButton;

	@FindBy(css = "#panel1bh-content div:nth-child(3)>button")
	WebElement submitAvatarButton;

	@FindBy(css = "input[type=file]")
	WebElement uploaderAvatarComponent;

	@FindBy(css = "#main>div>div:nth-child(2)")
	WebElement usernameField;

	@FindBy(css = "input[name=UserName]")
	WebElement inputUsernameComponent;

	@FindBy(css = "#panel1bh-content div:nth-child(2)>button:nth-child(1)")
	WebElement submitUsernameButton;

	@FindBy(css = "#main>div>div:nth-child(3)")
	WebElement genderField;

	@FindBy(css = "div[name=Gender] div:nth-child(2)")
	WebElement chooseGenderDropDownList;

	@FindBy(css = "#panel2bh-content div:nth-child(2)>button")
	WebElement submitGenderButton;

	@FindBy(css = "#main>div>div:nth-child(4)")
	WebElement dateOfBirthField;

	@FindBy(css = "div.react-datepicker__input-container>input")
	WebElement inputDateOfBirthComponent;

	@FindBy(css = "#panel3bh-content button:nth-child(1)")
	WebElement submitDateOfBirthButton;

	@FindBy(css = "#main>div>div:nth-child(5)")
	WebElement favoriteCategoriesField;

	@FindBy(css = ".rw-widget-container")
	WebElement favoriteCategoriesDropDownList;
	
	@FindBy(css = "li[role=option]")
	WebElement favoriteCategoriesDropDownListElements;

	@FindBy(css = "#panel4bh-content>div")
	WebElement emptyFieldFavoriteCategories;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement favoriteCategoriesSaveButton;

	@FindBy(css = "#main>div>div:nth-child(6)")
	WebElement changePasswordField;

	@FindBy(css = "input[name='oldPassword']")
	WebElement inputCurrentPasswordComponent;

	@FindBy(css = "input[name='newPassword']")
	WebElement inputNewPasswordComponent;

	@FindBy(css = "input[name='repeatPassword']")
	WebElement repeatNewPasswordComponent;

	@FindBy(css = "div.mt-2>button:nth-child(1)")
	WebElement submitChangePasswordButton;

	@FindBy(css = "div[role=alertdialog]")
	public WebElement clientSnackbar;

	public String getClientSnackbarText() {
		waitForElementToVisible(clientSnackbar);
		return clientSnackbar.getText();
	}
	
	public void clickOnChangeAvatarField() {
		changeAvatarField.click();
	}

	public void clickOnClearAvatarButton() {
		waitForElementToVisible(clearAvatarButton);
		clearAvatarButton.click();
	}

	public void clickOnSubmitAvatarButton() {
		waitForElementToVisible(submitAvatarButton);
		submitAvatarButton.click();
	}

	public void loadAvatar(String imagePath) {
		uploaderAvatarComponent.sendKeys(imagePath);
	}

	public void changeAvatar(String imagePath) {
		clickOnChangeAvatarField();
		if (occupiedAvatarField.isEnabled()) { // if avatar exists
			clickOnClearAvatarButton();
		}
		loadAvatar(imagePath);
		clickOnSubmitAvatarButton();
	}

	public void clickOnUsernameField() {
		usernameField.click();
	}

	public void setUsernameIntoInputComponent(String name) {
		waitForElementToVisible(inputUsernameComponent);
		inputUsernameComponent.sendKeys(name);
	}

	public void clickOnSubmitUsernameButton() {
		submitUsernameButton.click();
	}

	public void changeUserName(String name) {
		clickOnUsernameField();
		setUsernameIntoInputComponent(name);
		clickOnSubmitUsernameButton();
	}

	public void clickOnGenderField() {
		genderField.click();
	}

	public void clickOnChooseGenderDropDownList() {
		waitForElementToVisible(chooseGenderDropDownList);
		chooseGenderDropDownList.click();
	}

	public void clickOnSubmitGenderButton() {
		submitGenderButton.click();
	}
	
	public void selectGender(String gender) {
		String genderXpath = String.format("//div[contains(text(),'%s')]", gender);
		driver.findElement(By.xpath(genderXpath)).click();
	}

	public void chooseGender(String gender) {
		clickOnGenderField();
		clickOnChooseGenderDropDownList();
		selectGender(gender);
		clickOnSubmitGenderButton();
	}

	public void clickOnDateOfBirthField() {
		dateOfBirthField.click();
	}

	public void clickOnInputDateOfBirthComponent() {
		waitForElementToVisible(inputDateOfBirthComponent);
		inputDateOfBirthComponent.click();
	}

	public void clickOnSubmitDateOfBirthButton() {
		submitDateOfBirthButton.click();
	}

	public void setDateOfBirth(LocalDate date) {
		clickOnDateOfBirthField();
		inputDateOfBirthComponent.sendKeys(Keys.CONTROL + "a");
		inputDateOfBirthComponent.sendKeys(Keys.DELETE);
		inputDateOfBirthComponent.sendKeys(date.getMonth() + "/" + date.getDayOfMonth() + "/" + date.getYear());
		inputDateOfBirthComponent.sendKeys(Keys.ENTER);
		clickOnSubmitDateOfBirthButton();
	}

	public void clickOnFavoriteCategoriesField() {
		favoriteCategoriesField.click();
	}

	public void clickOnFavoriteCategoriesDropDownList() {
		waitForElementToVisible(favoriteCategoriesDropDownList);
		favoriteCategoriesDropDownList.click();
	}

	public void clickOnEmptyFieldFavoriteCategories() {
		emptyFieldFavoriteCategories.click();
	}

	public void clickOnFavoriteCategoriesSaveButton() {
		favoriteCategoriesSaveButton.click();
	}

	public void selectCategory(String category) {
		String categoryXpath = String.format("//li[contains(text(),'%s')]", category);
		driver.findElement(By.xpath(categoryXpath)).click();
	}

	public void chooseFavoriteCategories(String category) {
		clickOnFavoriteCategoriesField();
		clickOnFavoriteCategoriesDropDownList();
		waitForElementToVisible(favoriteCategoriesDropDownListElements);
		selectCategory(category);
		clickOnEmptyFieldFavoriteCategories();
		clickOnFavoriteCategoriesSaveButton();
	}

	public void clickOnChangePasswordField() {
		changePasswordField.click();
	}

	public void setCurrentPasswordComponent(String currentPassword) {
		waitForElementToVisible(inputCurrentPasswordComponent);
		inputCurrentPasswordComponent.sendKeys(currentPassword);
	}

	public void setNewPasswordComponent(String newPassword) {
		inputNewPasswordComponent.sendKeys(newPassword);
	}

	public void setRepeatNewPasswordComponent(String repNewPassword) {
		repeatNewPasswordComponent.sendKeys(repNewPassword);
	}

	public void clickOnSubmitChangePasswordButton() {
		submitChangePasswordButton.click();
	}

	public void changePassword(String currentPassword, String newPassword, String repNewPassword) {
		clickOnChangePasswordField();
		setCurrentPasswordComponent(currentPassword);
		setNewPasswordComponent(newPassword);
		setRepeatNewPasswordComponent(repNewPassword);
		clickOnSubmitChangePasswordButton();
	}
}
