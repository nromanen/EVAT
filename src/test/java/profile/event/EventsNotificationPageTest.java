package profile.event;

import base.SignInBaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.homePage.HomePageNavBar;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class EventsNotificationPageTest extends SignInBaseTest {
	
	@BeforeClass
	public void profileSetup() {
	new HomePageNavBar(driver).clickNotificationButton();
	}

	/**
	 * Test to verify that authorized user in case of absence notifications
	 * will receive the appropriate message
	 */
	@Test
	@Description(value = "Test to verify that authorized user in case of absence notifications will receive the appropriate message")
	public void checkForNotificationsAbsenceTest() {
		Assert.assertEquals(eventsNotificationPage.getNotificationsStatusText(), "You don't have notifications");
	}
}
