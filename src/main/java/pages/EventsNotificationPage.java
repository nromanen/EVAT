package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventsNotificationPage {
	
private WebDriver driver;
	
	public EventsNotificationPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#main > div > div > div > div.MuiCardActions-root > div > div.d-flex.flex-row.align-items-center.justify-content-center.float-right > a > button > span.MuiTouchRipple-root")
	WebElement viewEventButton;
	
	@FindBy(css = "#main > div > div > div > div.MuiCardActions-root > div > div.d-flex.flex-row.align-items-center.justify-content-center.float-right > div > button")
	WebElement shareEventButton;
	
	public void clickOnViewEventButton() {
		viewEventButton.click();
	}
	
	public void clickOnShareEventButton() {
		shareEventButton.click();
	}
	
	// TODO

}
