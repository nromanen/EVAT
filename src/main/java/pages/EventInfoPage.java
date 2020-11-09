package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventInfoPage {
	
private WebDriver driver;
	
	public EventInfoPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[contains(text(),'Join')]")
	public
	WebElement joinEventButton;
	
	@FindBy(xpath = "//button[contains(text(),'Leave')]")
	public
	WebElement leaveEventButton;
	
	@FindBy(css = "#main > div > div > div.col-9 > div.col-12 > div.button-block")
	WebElement joinEventStatusButton;
	
	@FindBy(css = "#main > div > div > div.col-9 > div:nth-child(3) > div > form:nth-child(1) > div.MuiFormControl-root.MuiTextField-root.MuiFormControl-fullWidth > div > input")
	public
	WebElement commentEventInput;
	
	@FindBy(xpath = "//span[contains(text(),'Add')]")
	WebElement addCommentButton;

	@FindBy(css = "#main > div > div > div.col-9 > div:nth-child(3) > div > div.ItemComment > div > div.ItemComment > div:nth-child(2) > button")
	WebElement deleteCommentButton;
	
	@FindBy(css = "#main > div > div > div.col-9 > div:nth-child(3) > div > div.ItemComment > div > div.ItemComment > div > button")
	WebElement replyCommentButton;
	
	@FindBy(css = "#main > div > div > div.col-9 > div:nth-child(3) > div > form:nth-child(4) > div.MuiFormControl-root.MuiTextField-root.MuiFormControl-fullWidth > div > input")
	WebElement replyCommentEventInput;
	
	@FindBy(css = "#main > div > div > div.col-9 > div:nth-child(3) > div > form:nth-child(4) > div.MuiDialogActions-root.MuiDialogActions-spacing > button > span.MuiButton-label")
	WebElement addReplyCommentButton;
	
	@FindBy(css = "#main > div > div > div.col-9 > div:nth-child(3) > div > div.children > div.ItemComment > div > div.ItemComment > div > button")
	WebElement deleteReplyCommentButton;
	
	@FindBy(css = "#main > div > div > div.col-9 > div:nth-child(3) > div > div.ItemComment > div > div:nth-child(1) > div > div > div.mybutton > p:nth-child(3)")
	WebElement commentText;
	
	@FindBy(css = "#main > div > div > div.col-9 > div:nth-child(3) > div > div.children > div.ItemComment > div > div:nth-child(1) > div > div > div.mybutton > p:nth-child(3)")
	WebElement replyCommentText;
	
	public String getCommentText() {
		return commentText.getText();
	}
	
	public String joinEventStatusText() {
		return joinEventStatusButton.getText();
	}
	
	public String getReplyCommentText() {
		return replyCommentText.getText();
	}
	
	public void clickOnJoinEventButton() {
		joinEventButton.click();
	}
	
	public void clickOnLeaveEventButton() {
		leaveEventButton.click();
	}
	
	public void joinEvent() {
		clickOnJoinEventButton();
	}
	
	public void leaveEvent() {
		clickOnLeaveEventButton();
	}
	
	public void writeComment(String comment) {
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
	
	public void writeReplyToComment(String replyText) {
		replyCommentEventInput.sendKeys(replyText);
	}
	
	public void replyOnComment(String replyText) {
		clickOnReplyCommentButton();
		writeReplyToComment(replyText);
		clickOnAddReplyCommentButton();
	}
}
