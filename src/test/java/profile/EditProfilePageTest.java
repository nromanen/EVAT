package profile;

import java.time.LocalDate;
import base.SignInBaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.homePage.HomePageNavBar;

public class EditProfilePageTest extends SignInBaseTest {

	@BeforeClass
	public void profileSetup() {
		new HomePageNavBar(driver).clickEditProfileButton();
	}
	
	@DataProvider(name = "dateOfBirthData")
	public Object[][] provideDateOfBirthData() {
		return new Object[][] {
			{ LocalDate.of(1989, 10, 13), "Set date of birth successed" },
			{ LocalDate.of(1905, 01, 31), "Failed" },
			{ LocalDate.of(1905, 02, 01), "Set date of birth successed" },
			{ LocalDate.of(2006, 01, 31), "Set date of birth successed" },
			{ LocalDate.of(2006, 02, 01), "Failed" }};
	}
	
	@DataProvider(name = "genderData")
	public Object[][] provideGenderData() {
		return new Object[][] {
			{ "Other", "Set gender successed" },
			{ "Male", "Set gender successed" },
			{ "Female", "Set gender successed" }};
	}

	/**
	 * Test to verify that authorized user
	 * can change avatar
	 */
	@Test
	@Description(value = "Test to verify that authorized user can change avatar")
	public void changeAvatarTest() {
		editProfilePage.changeAvatar(imagePath);
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Avatar is update");
	}

	/**
	 * Test to verify that authorized user
	 * can change Username
	 */
	@Test
	@Description(value = "Test to verify that authorized user can change Username")
	public void changeUsernameTest() {
		editProfilePage.changeUserName("Saul");
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Username is changed");
	}

	/**
	 * Test to verify that authorized user
	 * can choose gender
	 */
	@Test(dataProvider = "genderData")
	@Description(value = "Test to verify that authorized user can choose gender")
	public void chooseGenderTest(String gender, String message) {
		editProfilePage.chooseGender(gender);
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), message);
	}

	/**
	 * Test to verify that authorized user
	 * can set date of birth
	 */
	@Test(dataProvider = "dateOfBirthData")
	@Description(value = "Test to verify that authorized user can set date of birth")
	public void verifySettingDateOfBirthTest(LocalDate date, String message) {
		editProfilePage.setDateOfBirth(date);
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), message);
	}

	/**
	 * Test to verify that authorized user
	 * can choose favorite categories
	 */
	@Test
	@Description(value = "Test to verify that authorized user can choose favorite categories")
	public void chooseFavoriteCategoriesTest() {
		editProfilePage.chooseFavoriteCategories("Summer");
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Favarote categoris is updated");
	}

	/**
	 * Test to verify that registered user via email
	 * can change password
	 */
	@Test
	@Description(value = "Test to verify that registered user via email can change password")
	public void changePasswordTest() {
		editProfilePage.changePassword("1234", "1234", "1234");
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed");
	}
}
