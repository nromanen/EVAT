package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.LocalDate;

public class HomePage {
    public static final String URL = "https://eventsexpress-test.azurewebsites.net/home/events?page=1";
    private WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "search")
    public WebElement keywordField;

    @FindBy(css = ".MuiInputBase-input")
    public WebElement keywordFieldValue;

    @FindBy(css = ".box > div:nth-child(2) > button:nth-child(1) > span:nth-child(1)")
    public WebElement  moreFiltersButton;

    @FindBy(css = "#main > div.sidebar-filter > form > div:nth-child(2) > div.react-datepicker-wrapper > div > input")
    public WebElement  dateFromPicker;

    @FindBy(css = "div.form-group:nth-child(2) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")
    public WebElement dateFromPickerValue;

    @FindBy(css = "#main > div.sidebar-filter > form > div:nth-child(3) > div.react-datepicker-wrapper > div > input")
    public WebElement  dateToPicker;

    @FindBy(css = ".rw-input-reset")
    public WebElement hashtagField;

    @FindBy(css = ".rw-btn")
    public WebElement  hashtagButton;

    @FindBy(css = ".rw-multiselect-tag-btn > span:nth-child(1)")
    public WebElement  hashtagCloseButton;

    @FindBy(css = ".box > div:nth-child(5) > button:nth-child(1) > span:nth-child(1)")
    public WebElement  lessButton;

    @FindBy(css = "button.MuiButton-textPrimary:nth-child(1) > span:nth-child(1)")
    public WebElement  resetButton;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(2) > span:nth-child(1)")
    public WebElement  searchButton;

    @FindBy(css = ".rw-list-empty")
    public WebElement incorrectHashtag;

    @FindBy(css = "")
    public WebElement incorrectKeywordTooSmall;

    @FindBy(css = "")
    public WebElement incorrectKeywordTooLong;

    public HomePage clickMoreFiltersButton(){
        moreFiltersButton.click();
        return this;
    }
    public HomePage clickSearchButton(){
        searchButton.click();
        return new HomePage(driver);
    }
    public HomePage clickLessButton(){
        lessButton.click();
        return this;
    }
    public HomePage clickResetButton(){
        resetButton.click();
        return this;
    }
    public HomePage clickHashtagButton(){
        hashtagButton.click();
        return this;
    }
    public HomePage clickHashtagCloseButton(){
        hashtagCloseButton.click();
        return this;
    }
    public HomePage clickInKeywordField(){
        keywordField.click();
        return this;
    }

    public HomePage typeKeyword(String keyword){
        keywordField.sendKeys(keyword);
        return this;
    }
    public HomePage clearDateFrom(){
        dateFromPicker.sendKeys(Keys.CONTROL + "A");
        dateFromPicker.sendKeys(Keys.DELETE);
        return this;
    }
    public HomePage clearDateTo(){
        dateToPicker.sendKeys(Keys.CONTROL + "A");
        dateToPicker.sendKeys(Keys.DELETE);
        return this;
    }
    public HomePage typeDateFrom(LocalDate date){
        dateFromPicker.sendKeys(date.getMonth()+"/"+date.getDayOfMonth()+"/"+date.getYear());
        dateFromPicker.sendKeys(Keys.ENTER);
        return this;
    }
    public HomePage typeDateTo(LocalDate date){
        dateToPicker.sendKeys(date.getMonth()+"/"+date.getDayOfMonth()+"/"+date.getYear());
        dateToPicker.sendKeys(Keys.ENTER);
        return this;
    }
    public HomePage typeHashtag(String hashtag){
        hashtagField.sendKeys(hashtag);
        hashtagField.sendKeys(Keys.ENTER);
        hashtagField.sendKeys(Keys.ESCAPE);
        return this;
    }
    public HomePage typeIncorrectHashtag(String hashtag){
        hashtagField.sendKeys(hashtag);
        return this;
    }
    public HomePage searchByKeyword(String keyword){
        this.typeKeyword(keyword);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByTwoDates(LocalDate date1, LocalDate date2){
        this.clickMoreFiltersButton();
        this.clearDateFrom();
        this.typeDateFrom(date1);
        this.clearDateTo();
        this.typeDateTo(date2);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByDateFrom(LocalDate date){
        this.clickMoreFiltersButton();
        this.clearDateFrom();
        this.typeDateFrom(date);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByDateTo(LocalDate date){
        this.clickMoreFiltersButton();
        this.clearDateTo();
        this.typeDateTo(date);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByOneHashtag(String hashtag){
        this.clickMoreFiltersButton();
        this.typeHashtag(hashtag);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByTwoHashtags(String hashtag1, String hashtag2){
        this.clickMoreFiltersButton();
        this.typeHashtag(hashtag1);
        this.typeHashtag(hashtag2);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByKeywordAndDate(String keyword, LocalDate date){
        this.typeKeyword(keyword);
        this.clickMoreFiltersButton();
        this.clearDateTo();
        this.typeDateTo(date);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByKeywordAndHashtag(String keyword, String hashtag){
        this.typeKeyword(keyword);
        this.clickMoreFiltersButton();
        this.typeHashtag(hashtag);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByDatesAndHashtag(LocalDate date1, LocalDate date2, String hashtag){
        this.clickMoreFiltersButton();
        this.clearDateFrom();
        this.typeDateFrom(date1);
        this.clearDateTo();
        this.typeDateTo(date2);
        this.typeHashtag(hashtag);
        this.clickSearchButton();
        return this;
    }
    public HomePage searchByKeywordAndDateAndHashtag(String keyword, LocalDate date, String hashtag) {
        this.typeKeyword(keyword);
        this.clickMoreFiltersButton();
        this.clearDateFrom();
        this.typeDateFrom(date);
        this.typeHashtag(hashtag);
        this.clickSearchButton();
        return this;
    }

    public String getIncorrectHashtagText(){
        return incorrectHashtag.getText();
    }
    public String getIncorrectKeywordTextTooSmall(){
        return incorrectKeywordTooSmall.getText();
    }
    public String getIncorrectKeywordTextTooLong(){
        return incorrectKeywordTooLong.getText();
    }
    public String getKeywordFieldText(){
        return keywordFieldValue.getAttribute("value");
    }
    public String getDateFromPickerText(){
        return dateFromPickerValue.getAttribute("value");
    }
}
