package profile.event;

import base.SignInBaseTest;
import org.testng.annotations.Test;
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
