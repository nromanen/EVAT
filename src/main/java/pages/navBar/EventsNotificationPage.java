package pages.navBar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class EventsNotificationPage extends BasePage {

	public EventsNotificationPage(WebDriver driver) {
		super(driver);
	}

	// TODO fix css selectors when the functionality will be correct
	
	@FindBy(css = "#main > div > div > div > div.MuiCardActions-root > div > div.d-flex.flex-row.align-items-center.justify-content-center.float-right > a > button > span.MuiTouchRipple-root")
	WebElement viewEventButton;

	@FindBy(css = "#main > div > div > div > div.MuiCardActions-root > div > div.d-flex.flex-row.align-items-center.justify-content-center.float-right > div > button")
	WebElement shareEventButton;

	@FindBy(css = "#customized-menu > div.MuiPaper-root.MuiMenu-paper.jss1680.MuiPaper-elevation0.MuiPopover-paper.MuiPaper-rounded > ul > li > button")
	WebElement shareOnFaceBookButton;

	@FindBy(css = "#customized-menu > div.MuiPaper-root.MuiMenu-paper.jss1680.MuiPaper-elevation0.MuiPopover-paper.MuiPaper-rounded > ul > li > a:nth-child(2)")
	WebElement shareOnTelegramButton;

	@FindBy(css = "#customized-menu > div.MuiPaper-root.MuiMenu-paper.jss1680.MuiPaper-elevation0.MuiPopover-paper.MuiPaper-rounded > ul > li > a:nth-child(3)")
	WebElement shareOnTwiterButton;

	@FindBy(css = "#customized-menu > div.MuiPaper-root.MuiMenu-paper.jss1680.MuiPaper-elevation0.MuiPopover-paper.MuiPaper-rounded > ul > li > a:nth-child(4)")
	WebElement shareOnLinkedInButton;

	@FindBy(css = ".text-center")
	WebElement notificationsStatus;

	public String getNotificationsStatusText() {
		return notificationsStatus.getText();
	}

	public void clickOnViewEventButton() {
		viewEventButton.click();
	}

	public void clickOnShareEventButton() {
		shareEventButton.click();
	}

	public void clickOnShareOnFaceBookButton() {
		shareOnFaceBookButton.click();
	}

	public void clickOnShareOnTelegramButton() {
		shareOnTelegramButton.click();
	}

	public void clickOnShareOnTwiterButton() {
		shareOnTwiterButton.click();
	}

	public void clickOnShareOnLinkedInButton() {
		shareOnLinkedInButton.click();
	}

	public void shareEventOnFaceBook() {
		clickOnShareEventButton();
		clickOnShareOnFaceBookButton();
	}

	public void shareEventOnTelegram() {
		clickOnShareEventButton();
		clickOnShareOnTelegramButton();
	}

	public void shareEventOnTwiter() {
		clickOnShareEventButton();
		clickOnShareOnTwiterButton();
	}

	public void shareEventOnLinkedIn() {
		clickOnShareEventButton();
		clickOnShareOnLinkedInButton();
	}
}
