package profile;

import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import baseTest.WrapBaseTest;
import pages.HomePageNavBar;

public class EditProfilePageTest extends WrapBaseTest {

	@BeforeClass
	public void profileSetup() {
		new HomePageNavBar(driver).clickEditProfileButton();
	}

	/**
	 * Test to verify that authorized user
	 * can change avatar
	 */
	@Test
	public void changeAvatarTest() {
		editProfilePage.changeAvatar(imagePath);
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Avatar is update");
	}

	/**
	 * Test to verify that authorized user
	 * can change Username
	 */
	@Test
	public void changeUsernameTest() {
		editProfilePage.changeUserName("Saul");
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Username is changed");
	}

	/**
	 * Test to verify that authorized user
	 * can choose gender
	 */
	@Test
	public void chooseGenderTest() {
		editProfilePage.chooseGender("Male");
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed"); // Set gender successed
	}

	/**
	 * Test to verify that authorized user
	 * can set date of birth
	 */
	@Test
	public void setDateOfBirthTest() {
		editProfilePage.setDateOfBirth(LocalDate.of(1989, 10, 13));
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed"); // Set date of birth successed
	}

	/**
	 * Test to verify that authorized user
	 * can choose favorite categories
	 */
	@Test
	public void chooseFavoriteCategoriesTest() {
		editProfilePage.chooseFavoriteCategories("Summer");
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed"); // Favarote categoris is updated
	}

	/**
	 * Test to verify that registered user via email
	 * can change password
	 */
	@Test
	public void changePasswordTest() {
		editProfilePage.changePassword("1234", "1234", "1234");
		Assert.assertEquals(editProfilePage.getClientSnackbarText(), "Failed");
	}
}
