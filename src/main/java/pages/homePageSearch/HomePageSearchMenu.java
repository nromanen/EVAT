package pages.homePageSearch;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.EventElement;

import java.time.LocalDate;

public class HomePageSearchMenu {
    public static final String URL = "https://eventsexpress-test.azurewebsites.net/home/events?page=1";
    private WebDriver driver;
    public HomePageSearchMenu(WebDriver driver){
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

    @FindBy(css = ".react-datepicker__day--selected")
    public WebElement selectedDayInCalendar;

    @FindBy(css = "")
    public WebElement incorrectKeywordTooSmall;

    @FindBy(css = "")
    public WebElement incorrectKeywordTooLong;

    public HomePageSearchMenu clickMoreFiltersButton(){
        EventElement element = new EventElement(driver, moreFiltersButton);
        element.clickAndWait(searchButton);
        return this;
    }
    public HomePageSearchMenu clickSearchButton(){
        searchButton.click();
        return this;
    }
    public HomePageSearchMenu clickSearchButtonAndWait(){
        EventElement element = new EventElement(driver, searchButton);
        element.clickAndWait(SearchResultPage.numberOfEvents);
        return this;
    }
    public HomePageSearchMenu clickLessButton(){
        lessButton.click();
        return this;
    }
    public HomePageSearchMenu clickResetButton(){
        resetButton.click();
        return this;
    }
    public HomePageSearchMenu clickResetButtonForClearResults(){
        EventElement element = new EventElement(driver, resetButton);
        element.clickAndWait(SearchResultPage.numberOfEvents);
        return this;
    }
    public HomePageSearchMenu clickHashtagButton(){
        hashtagButton.click();
        return this;
    }
    public HomePageSearchMenu clickHashtagCloseButton(){
        hashtagCloseButton.click();
        return this;
    }
    public HomePageSearchMenu clickInKeywordField(){
        keywordField.click();
        return this;
    }

    public HomePageSearchMenu typeKeyword(String keyword){
        keywordField.sendKeys(keyword);
        return this;
    }
    public HomePageSearchMenu clearDateFrom(){
        dateFromPicker.sendKeys(Keys.CONTROL + "A");
        dateFromPicker.sendKeys(Keys.DELETE);
        return this;
    }
    public HomePageSearchMenu clearDateTo(){
        dateToPicker.sendKeys(Keys.CONTROL + "A");
        dateToPicker.sendKeys(Keys.DELETE);
        return this;
    }
    public HomePageSearchMenu typeDateFrom(LocalDate date){
        dateFromPicker.sendKeys(date.getMonth()+"/"+date.getDayOfMonth()+"/"+date.getYear());
        dateFromPicker.sendKeys(Keys.ENTER);
        return this;
    }
    public HomePageSearchMenu typeDateFromWithAnotherFormat(String date){
        dateFromPicker.sendKeys(date);
        return this;
    }
    public HomePageSearchMenu typeDateTo(LocalDate date){
        dateToPicker.sendKeys(date.getMonth()+"/"+date.getDayOfMonth()+"/"+date.getYear());
        dateToPicker.sendKeys(Keys.ENTER);
        return this;
    }
    public HomePageSearchMenu typeHashtag(String hashtag){
        hashtagField.sendKeys(hashtag);
        hashtagField.sendKeys(Keys.ENTER);
        hashtagField.sendKeys(Keys.ESCAPE);
        return this;
    }
    public HomePageSearchMenu typeIncorrectHashtag(String hashtag){
        hashtagField.sendKeys(hashtag);
        return this;
    }
    public HomePageSearchMenu searchByKeyword(String keyword){
        typeKeyword(keyword);
        clickSearchButtonAndWait();
        return this;
    }
    public HomePageSearchMenu searchByTwoDates(LocalDate date1, LocalDate date2){
        clickMoreFiltersButton();
        clearDateFrom();
        typeDateFrom(date1);
        clearDateTo();
        typeDateTo(date2);
        clickSearchButtonAndWait();
        return this;
    }
    public HomePageSearchMenu searchByDateFrom(LocalDate date){
        clickMoreFiltersButton();
        clearDateFrom();
        typeDateFrom(date);
        clickSearchButtonAndWait();
        return this;
    }
    public HomePageSearchMenu searchByDateTo(LocalDate date){
        clickMoreFiltersButton();
        clearDateTo();
        typeDateTo(date);
        clickSearchButtonAndWait();
        return this;
    }
    public HomePageSearchMenu searchByOneHashtag(String hashtag){
        clickMoreFiltersButton();
        typeHashtag(hashtag);
        clickSearchButtonAndWait();
        return this;
    }
    public HomePageSearchMenu searchByTwoHashtags(String hashtag1, String hashtag2){
        clickMoreFiltersButton();
        typeHashtag(hashtag1);
        typeHashtag(hashtag2);
        clickSearchButtonAndWait();
        return this;
    }

    public HomePageSearchMenu searchByKeywordAndDateFrom(String keyword, LocalDate date){
        typeKeyword(keyword);
        clickMoreFiltersButton();
        clearDateFrom();
        typeDateFrom(date);
        clickSearchButtonAndWait();
        return this;
    }
    public HomePageSearchMenu searchByKeywordAndHashtag(String keyword, String hashtag){
        typeKeyword(keyword);
        clickMoreFiltersButton();
        typeHashtag(hashtag);
        clickSearchButtonAndWait();
        return this;
    }
    public HomePageSearchMenu searchByDatesAndHashtag(LocalDate date1, LocalDate date2, String hashtag){
        clickMoreFiltersButton();
        clearDateFrom();
        typeDateFrom(date1);
        clearDateTo();
        typeDateTo(date2);
        typeHashtag(hashtag);
        clickSearchButtonAndWait();
        return this;
    }
    public HomePageSearchMenu searchByKeywordAndDateAndHashtag(String keyword, LocalDate date, String hashtag) {
        typeKeyword(keyword);
        clickMoreFiltersButton();
        clearDateFrom();
        typeDateFrom(date);
        typeHashtag(hashtag);
        clickSearchButtonAndWait();
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
    public String getHashtagFieldText(){
        return hashtagField.getAttribute("value");
    }
    public String getSelectedDayInCalendarValue(){
        return selectedDayInCalendar.getText();
    }
}
