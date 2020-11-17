package profile.event;

import org.testng.annotations.Test;
import baseTest.SignInBaseTest;
import pages.HomePageNavBar;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class EventsNotificationPageTest extends SignInBaseTest {
	
	@BeforeClass
	public void profileSetup() {
	new HomePageNavBar(driver).clickNotificationButton();
	}

	@Test
	public void checkNotifications() {
		Assert.assertEquals(eventsNotificationPage.getNotificationsStatusText(), "You don't have notifications");
	}
}
