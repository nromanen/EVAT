package profile;

import base.Helper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.profile.EventMenu;


public class EventMenuTest extends ProfileBaseTest{

    EventMenu eventMenu;

    @BeforeClass
    @Override
    public void setUp(){
        super.setUp();
        signingIn();
        goToProfilePage();
        eventMenu = new EventMenu(driver);

    }

    @Test
    public void testDefaultBehavior() {
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(Helper.checkActionElement(eventMenu.getVisitedEvents()));
        softAssert.assertTrue(Helper.checkActionElement(eventMenu.getArchiveEvents()));
        softAssert.assertTrue(Helper.checkActionElement(eventMenu.getFutureEvents()));
        softAssert.assertTrue(Helper.checkActionElement(eventMenu.getToGoEvents()));
        softAssert.assertTrue(Helper.checkActionElement(eventMenu.getAddEvent()));
    }

}