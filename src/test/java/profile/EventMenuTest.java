package profile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.profile.EventMenu;

import static org.testng.Assert.*;

public class EventMenuTest {

    EventMenu eventMenuTest;
    SetUpProfile setUpProfile;

    @BeforeTest
    public void setUp(){
        setUpProfile=new SetUpProfile();
        eventMenuTest = new EventMenu(setUpProfile.getDriver());
    }

    @Test
    public void testClickFutureEvents() {
        assertTrue(eventMenuTest.clickFutureEvents());
    }

    @Test
    public void testClickArchiveEvents() {
        assertTrue(eventMenuTest.clickArchiveEvents());
    }

    @Test
    public void testClickVisitedEvents() {
        assertTrue(eventMenuTest.clickVisitedEvents());
    }

    @Test
    public void testClickToGoEvents() {
        assertTrue(eventMenuTest.clickToGoEvents());
    }

    @Test
    public void testClickAddEvent() {
        assertTrue(eventMenuTest.clickAddEvent());
    }

    @AfterClass
    public void afterClass(){
        setUpProfile.driverQuit();
    }
}