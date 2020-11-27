package profile.event;

import base.SignInBaseTest;
import io.qameta.allure.Description;
import pages.homePage.HomePageNavBar;
import pages.profile.EventMenu;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EventInfoPageTest extends SignInBaseTest {
	
	/**
	 * Test to verify that authorized user can add comment below event
	 */
	@Test
	@Description(value = "Test to verify that authorized user can add comment below event")
	public void addCommentTest() {
		new HomePageNavBar(driver).clickHomeButton();
		eventInfoPage.clickOnTestEventInfo();
		eventInfoPage.addCommentToEvent(comment);
		Assert.assertEquals(eventInfoPage.getCommentText(), "auto generated comment");
	}
	
	/**
	 * Test to verify that authorized user can join event
	 */
	@Test
	@Description(value = "Test to verify that authorized user can join event")
	public void joinEventTest() {
		new HomePageNavBar(driver).clickHomeButton();
		eventInfoPage.clickOnTestEventInfo();
		eventInfoPage.joinEvent();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(eventInfoPage.leaveEventButton));
		Assert.assertEquals(eventInfoPage.getCurrentStatusInfoText(), "You are gonna visit.");
	}
	
	/**
	 * Test to verify that authorized user can leave event
	 */
	@Test
	@Description(value = "Test to verify that authorized user can leave event")
	public void leaveEventTest() {
		new HomePageNavBar(driver).clickProfileButton();
		new EventMenu(driver).clickToGoEvents();
		eventInfoPage.clickOnTestJoinedEventInfo();
		eventInfoPage.leaveEvent();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(eventInfoPage.joinEventButton));
		Assert.assertEquals(eventInfoPage.getCurrentStatusInfoText(), "You are not in event yet.");
	}
	
	
}
