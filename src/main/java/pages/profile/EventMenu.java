package pages.profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EventMenu {

    WebDriver driver;

    @FindBy(how = How.CSS, using = "#full-width-tab-0")
    private WebElement futureEvents;

    @FindBy(how = How.CSS, using = "#full-width-tab-1")
    private WebElement archiveEvents;

    @FindBy(how = How.CSS, using = "#full-width-tab-2")
    private WebElement visitedEvents;

    @FindBy(how = How.CSS, using = "#full-width-tab-3")
    private WebElement toGoEvents;

    @FindBy(how = How.CSS, using = "#full-width-tab-4")
    private WebElement addEvent;

    public EventMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean clickFutureEvents(){
        if(futureEvents.isEnabled()) {
            futureEvents.click();
            return true;
        }
        return false;
    }

    public boolean clickArchiveEvents(){
        if(archiveEvents.isEnabled()) {
            archiveEvents.click();
            return true;
        }
        return false;
    }

    public boolean clickVisitedEvents(){
        if(visitedEvents.isEnabled()) {
            visitedEvents.click();
            return true;
        }
        return false;
    }

    public boolean clickToGoEvents(){
        if(toGoEvents.isEnabled()) {
            toGoEvents.click();
            return true;
        }
        return false;
    }

    public boolean clickAddEvent(){
        if(addEvent.isEnabled()) {
            addEvent.click();
            return true;
        }
        return false;
    }

    public WebElement getFutureEvents() {
        return futureEvents;
    }

    public void setFutureEvents(WebElement futureEvents) {
        this.futureEvents = futureEvents;
    }

    public WebElement getArchiveEvents() {
        return archiveEvents;
    }

    public void setArchiveEvents(WebElement archiveEvents) {
        this.archiveEvents = archiveEvents;
    }

    public WebElement getVisitedEvents() {
        return visitedEvents;
    }

    public void setVisitedEvents(WebElement visitedEvents) {
        this.visitedEvents = visitedEvents;
    }

    public WebElement getToGoEvents() {
        return toGoEvents;
    }

    public void setToGoEvents(WebElement toGoEvents) {
        this.toGoEvents = toGoEvents;
    }

    public WebElement getAddEvent() {
        return addEvent;
    }

    public void setAddEvent(WebElement addEvent) {
        this.addEvent = addEvent;
    }
}
