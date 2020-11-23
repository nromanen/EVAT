package profile;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.profile.EventMenu;

import static pages.base.Helper.isElementPresent;


public class EventMenuTest extends ProfileBaseTest{

    EventMenu eventMenu;

    /**
     * Verify elements on the page is present
     */

    @Test
    public void testDefaultBehavior() {
        signingIn();
        goToProfilePage();
        eventMenu = new EventMenu(driver);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(isElementPresent(eventMenu.getVisitedEvents()),"Element visited events isn't present");
        softAssert.assertTrue(isElementPresent(eventMenu.getArchiveEvents()),"Element archive events isn't present");
        softAssert.assertTrue(isElementPresent(eventMenu.getFutureEvents()),"Element visited events isn't present");
        softAssert.assertTrue(isElementPresent(eventMenu.getToGoEvents()),"Element visited events isn't present");
        softAssert.assertTrue(isElementPresent(eventMenu.getAddEvent()),"Element visited events isn't present");
        softAssert.assertAll();
    }

}