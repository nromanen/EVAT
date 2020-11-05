package pages.profile.events_menu_pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEventPage{

    WebDriver driver;
    WebDriverWait webDriverWait;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.preview-container > div > input[type=file]")
    private WebElement fileUploader;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.preview-container > div > div > img")
    private WebElement image;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(3) > div > div > input")
    private WebElement title;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(4) > div > div > input")
    private WebElement participants;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.meta-wrap.m-2 > span > div.react-datepicker-wrapper > div > input")
    private WebElement dateFrom;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.meta-wrap.m-2 > span:nth-child(2) > div.react-datepicker-wrapper > div > input")
    private WebElement dateTo;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(6) > div > div > textarea")
    private WebElement description;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(7) > div > div.rw-widget-input.rw-widget-picker.rw-widget-container > div > input")
    private WebElement hashtags;

    private Select country;
    private final By findCountry=By.name("countryId");

    private Select city;
    private final By findCity=By.name("cityId");

    @FindBy(how =How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > button")
    private WebElement clear;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorIncorrectResolutionPhoto;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorIncorrectPhotoSize;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorIncorrectTitle;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorIncorrectTitleNoOneLetter;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorIncorrectDescription;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorTooManyHashtags;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorTooManyParticipants;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > button > span.MuiTouchRipple-root")
    private WebElement save;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.preview-container > div.error")
    private WebElement requiredImage;

    @FindBy(how = How.CSS,using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(3) > div > p")
    private WebElement requiredTitle;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(6) > div > p")
    private WebElement requiredDescription;

    @FindBy(how = How.CSS,using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(7) > p")
    private WebElement requiredHashtags;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(8) > div > p")
    private WebElement requiredCountry;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(9) > div > p")
    private WebElement requiredCity;

    @FindBy(how = How.CSS, using = "#client-snackbar")
    private WebElement createdEventMessage;


    public AddEventPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webDriverWait=wait;
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(findCountry));
        country=new Select(driver.findElement(findCountry));
    }

    public boolean loadImage(String nameFileImg) {
        if (fileUploader==null) return false;
        fileUploader.sendKeys(nameFileImg);
        return true;
    }

    public String getValueImage(){
        if (image==null) return "";
        return image.getAttribute("alt");
    }

    public boolean inputTitle(String text) {
        title.sendKeys(text);
        return true;
    }

    public boolean inputParticipants(String value){
        if(participants==null) return false;
        participants.sendKeys(value);
        return true;
    }

    public static String convertDate(String date, LocalDate noLessThisDate){
        String  PATTERN="([0-9]+)/([0-9]+)/([0-9]{4,})";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher matcher = pat.matcher(date);
        if(!matcher.find())
            return noLessThisDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        int m=Integer.parseInt(matcher.group(1));
        int d=Integer.parseInt(matcher.group(2));
        int y=Integer.parseInt(matcher.group(3));

        if (m < 1 || m > 12 || m < noLessThisDate.getMonthValue())
            m = noLessThisDate.getMonthValue();

        if(y < LocalDate.now().getYear())
            y=noLessThisDate.getYear();
        if(y > LocalDate.now().getYear()+1)
            y=noLessThisDate.getYear()+1;

        if(d < 1 || d > LocalDate.of(y,m,1).lengthOfMonth()
                || (m==noLessThisDate.getMonthValue() && d<noLessThisDate.getDayOfMonth()))
            d=noLessThisDate.getDayOfMonth();

        LocalDate res = LocalDate.of(y,m,d);
        return  res.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public boolean inputDateFrom(String date){
        if (dateFrom==null) return false;
        dateFrom.sendKeys(Keys.CONTROL + "A");
        dateFrom.sendKeys(Keys.DELETE);
        dateFrom.sendKeys(date);
        dateFrom.sendKeys(Keys.ENTER);
        return true;
    }

    public boolean inputDateTo(String date) {
        if (dateTo==null) return false;
        dateTo.sendKeys(Keys.CONTROL + "A");
        dateTo.sendKeys(Keys.DELETE);
        dateTo.sendKeys(date);
        dateTo.sendKeys(Keys.ENTER);
        return true;
    }

    public boolean inputDescription(String text) {
        if (description==null)return false;
        description.sendKeys(text);
        return true;
    }

    public boolean inputHashtags(List<String> hashtagsToEnter) {
        if (hashtags==null) return false;
        for (String str : hashtagsToEnter) {
            hashtags.sendKeys(str);
            hashtags.sendKeys(Keys.ENTER);
        }
        hashtags.sendKeys(Keys.ESCAPE);
        return true;
    }

    public boolean inputCountry(String text) {
        if (country != null && country.getOptions()!= null) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(8) > div > div > select > option:nth-child(2)")));
            country.selectByVisibleText(text);
            if(city==null){
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(findCity));
                city = new Select(driver.findElement(findCity));
            }
            return true;
        }
        return false;
    }

    public int countriesAmount(){
        if (country != null && country.getOptions()!= null) return country.getOptions().size();
        return -1;
    }

    public boolean inputCity(String text){
        if(country.getFirstSelectedOption()==null ) return false;
        else
            if (city.getOptions() != null) {
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(9) > div > div > select > option:nth-child(2)")));
                city.selectByVisibleText(text);
                return true;
            }
        return false;
    }

    public int citiesAmount(){
        if(country.getFirstSelectedOption()==null ) return -1;
        else
            if (city.getOptions() != null) {
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#age-native-simple > option:nth-child(2)")));
                return city.getOptions().size();
            }

        return -1;
    }

    public boolean clickClear(){
        if(clear==null)return false;
        clear.click();
        return true;
    }

    public boolean isAppearCreatedEventMessage(){
        webDriverWait.until(ExpectedConditions.visibilityOf(createdEventMessage));
        if(createdEventMessage.isDisplayed())return true;
        return false;
    }

    public boolean isPageEmpty(){
        List<WebElement> hashtags = driver.findElements(By.cssSelector("#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(7) > div > div.rw-widget-input.rw-widget-picker.rw-widget-container > div > ul >li"));
        if(title.getAttribute("value").isEmpty() &&
                participants.getAttribute("value").isEmpty() &&
                description.getAttribute("value").isEmpty() &&
                hashtags.isEmpty() &&
                country.getFirstSelectedOption().getText().isBlank()){
            try{
                image.isDisplayed();
                return false;
            }
            catch (NoSuchElementException e){
                return true;
            }
        }
        return false;
    }

    public boolean isPageFull(){
        List<WebElement> hashtags = driver.findElements(By.cssSelector("#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(7) > div > div.rw-widget-input.rw-widget-picker.rw-widget-container > div > ul >li"));
        if(image.isEnabled() &&
                !title.getAttribute("value").isEmpty() &&
                !participants.getAttribute("value").isEmpty() &&
                !dateFrom.getAttribute("value").isEmpty() &&
                !description.getAttribute("value").isEmpty() &&
                !hashtags.isEmpty() &&
                !country.getFirstSelectedOption().getText().isBlank() &&
                !city.getFirstSelectedOption().getText().isBlank())
            return true;
        return false;
    }


    public boolean clickSave(){
        if(save==null)return false;
        save.submit();
        return true;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "AddEventPage{" +
                "image=" + fileUploader +
                ", title=" + title +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", description=" + description +
                ", hashtags=" + hashtags +
                ", country=" + country +
                ", city=" + city +
                '}';
    }

    public WebElement getImage() {
        return image;
    }

    public void setImage(WebElement image) {
        this.image = image;
    }

    public WebElement getClear() {
        return clear;
    }

    public void setClear(WebElement clear) {
        this.clear = clear;
    }

    public WebElement getFileUploader() {
        return fileUploader;
    }

    public void setFileUploader(WebElement fileUploader) {
        this.fileUploader = fileUploader;
    }

    public WebElement getTitle() {
        return title;
    }

    public void setTitle(WebElement title) {
        this.title = title;
    }

    public WebElement getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(WebElement dateFrom) {
        this.dateFrom = dateFrom;
    }

    public WebElement getDateTo() {
        return dateTo;
    }

    public void setDateTo(WebElement dateTo) {
        this.dateTo = dateTo;
    }

    public WebElement getDescription() {
        return description;
    }

    public void setDescription(WebElement description) {
        this.description = description;
    }

    public WebElement getHashtags() {
        return hashtags;
    }

    public void setHashtags(WebElement hashtags) {
        this.hashtags = hashtags;
    }

    public Select getCountry() {
        return country;
    }

    public void setCountry(Select country) {
       this.country = country;
    }

    public Select getCity() {
        return city;
    }

    public void setCity(Select city) {
        this.city = city;
    }

    public WebElement getErrorIncorrectTitle() {
        return errorIncorrectTitle;
    }

    public void setErrorIncorrectTitle(WebElement errorIncorrectTitle) {
        this.errorIncorrectTitle = errorIncorrectTitle;
    }

    public WebElement getErrorIncorrectTitleNoOneLetter() {
        return errorIncorrectTitleNoOneLetter;
    }

    public void setErrorIncorrectTitleNoOneLetter(WebElement errorIncorrectTitleNoOneLetter) {
        this.errorIncorrectTitleNoOneLetter = errorIncorrectTitleNoOneLetter;
    }

    public WebElement getErrorIncorrectDescription() {
        return errorIncorrectDescription;
    }

    public void setErrorIncorrectDescription(WebElement errorIncorrectDescription) {
        this.errorIncorrectDescription = errorIncorrectDescription;
    }

    public WebElement getErrorTooManyHashtags() {
        return errorTooManyHashtags;
    }

    public void setErrorTooManyHashtags(WebElement errorTooManyHashtags) {
        this.errorTooManyHashtags = errorTooManyHashtags;
    }

    public WebElement getSave() {
        return save;
    }

    public void setSave(WebElement save) {
        this.save = save;
    }

    public WebElement getRequiredImage() {
        return requiredImage;
    }

    public void setRequiredImage(WebElement requiredImage) {
        this.requiredImage = requiredImage;
    }

    public WebElement getRequiredTitle() {
        return requiredTitle;
    }

    public void setRequiredTitle(WebElement requiredTitle) {
        this.requiredTitle = requiredTitle;
    }

    public WebElement getRequiredDescription() {
        return requiredDescription;
    }

    public void setRequiredDescription(WebElement requiredDescription) {
        this.requiredDescription = requiredDescription;
    }

    public WebElement getRequiredHashtags() {
        return requiredHashtags;
    }

    public void setRequiredHashtags(WebElement requiredHashtags) {
        this.requiredHashtags = requiredHashtags;
    }

    public WebElement getRequiredCountry() {
        return requiredCountry;
    }

    public void setRequiredCountry(WebElement requiredCountry) {
        this.requiredCountry = requiredCountry;
    }

    public WebElement getRequiredCity() {
        return requiredCity;
    }

    public void setRequiredCity(WebElement requiredCity) {
        this.requiredCity = requiredCity;
    }

    public WebElement getErrorIncorrectResolutionPhoto() {
        return errorIncorrectResolutionPhoto;
    }

    public void setErrorIncorrectResolutionPhoto(WebElement errorIncorrectResolutionPhoto) {
        this.errorIncorrectResolutionPhoto = errorIncorrectResolutionPhoto;
    }

    public WebElement getErrorIncorrectPhotoSize() {
        return errorIncorrectPhotoSize;
    }

    public void setErrorIncorrectPhotoSize(WebElement errorIncorrectPhotoSize) {
        this.errorIncorrectPhotoSize = errorIncorrectPhotoSize;
    }

    public WebElement getErrorTooManyParticipants() {
        return errorTooManyParticipants;
    }

    public void setErrorTooManyParticipants(WebElement errorTooManyParticipants) {
        this.errorTooManyParticipants = errorTooManyParticipants;
    }

    public WebElement getParticipants() {
        return participants;
    }

    public void setParticipants(WebElement participants) {
        this.participants = participants;
    }

    public WebElement getCreatedEventMessage() {
        return createdEventMessage;
    }

    public void setCreatedEventMessage(WebElement createdEventMessage) {
        this.createdEventMessage = createdEventMessage;
    }
}
