package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

public class EventInfoPage extends BasePage {

	public EventInfoPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(text(),'Join')]")
	public WebElement joinEventButton;

	@FindBy(xpath = "//button[contains(text(),'Leave')]")
	public WebElement leaveEventButton;

	@FindBy(css = "input[name='comment']")
	public WebElement commentEventInput;

	@FindBy(css = "button[value='Add']")
	WebElement addCommentButton;

	@FindBy(css = "button[aria-label='Delete']")
	WebElement deleteCommentButton;

	@FindBy(css = "button[aria-label='Reply']:nth-of-type(1)")
	WebElement replyCommentButton;

	@FindBy(css = "input[name='comment']") // TODO same as commentEventInput
	WebElement replyCommentEventInput;

	@FindBy(css = "button[value='Add']") // TODO same as addCommentButton
	WebElement addReplyCommentButton;

	@FindBy(css = "button[aria-label='Delete']") // TODO same as deleteCommentButton
	WebElement deleteReplyCommentButton;

	@FindBy(css = ".mybutton > p:nth-child(3)") // TODO same for all comments
	WebElement commentText;

	@FindBy(css = ".mybutton > p:nth-child(3)") // TODO same for all comments
	WebElement replyCommentText;
	
	@FindBy(css = ".events-container>div>div:nth-child(1) a>button")
	WebElement testEventOnMainPage;
	
	@FindBy(css = ".rounded>div>div:nth-child(1) .float-right>a>button")
	WebElement testEventOnEventsToGoPage;
	
	@FindBy(css = "span[role='alert']")
	WebElement currentStatusInfo;
	
	@FindBy(css = ".MuiButton-textSecondary")
	WebElement agreeButton;
	
	public String getCurrentStatusInfoText() {
		return currentStatusInfo.getText();
	}
	
	public void clickOnTestEventInfo() {
		waitForElementToVisible(testEventOnMainPage);
		testEventOnMainPage.click();
	}
	
	public void clickOnTestJoinedEventInfo() {
		waitForElementToVisible(testEventOnEventsToGoPage);
		testEventOnEventsToGoPage.click();
	}

	public String getCommentText() {
		return commentText.getText();
	}

	public String joinEventStatusText() {
		return joinEventButton.getText();
	}

	public String getReplyCommentText() {
		return replyCommentText.getText();
	}

	public void clickOnJoinEventButton() {
		waitForElementToVisible(joinEventButton);
		joinEventButton.click();
	}

	public void clickOnLeaveEventButton() {
		waitForElementToVisible(leaveEventButton);
		leaveEventButton.click();
	}

	public void joinEvent() {
		clickOnJoinEventButton();
	}
	
	public void clickOnAgreeButton() {
		waitForElementToVisible(agreeButton);
		agreeButton.click();
	}

	public void leaveEvent() {
		clickOnLeaveEventButton();
		clickOnAgreeButton();
	}

	public void writeComment(String comment) {
		waitForElementToVisible(commentEventInput);
		commentEventInput.sendKeys(comment);
	}

	public void clickOnAddCommentButton() {
		addCommentButton.click();
	}

	public void clickOnDeleteCommentButton() {
		deleteCommentButton.click();
	}

	public void addCommentToEvent(String comment) {
		writeComment(comment);
		waitForElementToVisible(addCommentButton);
		clickOnAddCommentButton();
	}

	public void clickOnReplyCommentButton() {
		replyCommentButton.click();
	}

	public void clickOnAddReplyCommentButton() {
		addReplyCommentButton.click();
	}

	public void clickOnDeleteReplyCommentButton() {
		deleteReplyCommentButton.click();
	}

	public void writeReplyOnComment(String replyText) {
		replyCommentEventInput.sendKeys(replyText);
	}

	public void replyOnComment(String replyText) {
		clickOnReplyCommentButton();
		writeReplyOnComment(replyText);
		clickOnAddReplyCommentButton();
	}
}
