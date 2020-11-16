package profile;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseTest.WrapBaseTest;

public class EventInfoPageTest extends WrapBaseTest {
	
	@BeforeClass
	public void profileSetup() {
		eventInfoPage.clickOnTestEventInfo();
	}
	
	/**
	 * Test to verify that authorized user
	 * can add comment below event
	 */
	@Test
	public void addCommentTest() {
		eventInfoPage.addCommentToEvent(comment);
		Assert.assertEquals(eventInfoPage.getCommentText(), "auto generated comment");
	}
	
	/**
	 * Test to verify that authorized user
	 * can join or/and leave event
	 */
	@Test
	public void joinAndLeaveEventTest() {
		SoftAssert asert = new SoftAssert();
		
		eventInfoPage.joinEvent();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(eventInfoPage.leaveEventButton));
		asert.assertEquals(eventInfoPage.getCurrentStatusInfoText(), "Current status: Approving participation.");
		
		eventInfoPage.leaveEvent();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(eventInfoPage.joinEventButton));
		asert.assertEquals(eventInfoPage.getCurrentStatusInfoText(), "Current status: Not in event.");
		
		asert.assertAll();
	}
}
