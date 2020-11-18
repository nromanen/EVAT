package pages.profile;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;
import pages.base.Helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEventPage extends BasePage {

    public static final String VALUE_ATTRIBUTE = "value";
    public static final String ALT_ATTRIBUTE = "alt";

    @FindBy(how = How.CSS, using = ".placeholder-preview >input")
    private WebElement fileUploader;

    @FindBy(how = How.CSS, using = ".image-container>img")
    private WebElement image;

    @FindBy(how = How.CSS, using = ".MuiInputBase-formControl > [name = \"title\"]")
    private WebElement title;

    @FindBy(how = How.CSS, using = ".MuiInputBase-formControl > [name=\"maxParticipants\"]")
    private WebElement participants;

    @FindBy(how = How.CSS, using = ".meta-wrap > span:nth-child(1) > div > div > input")
    private WebElement dateFrom;
    @FindBy(how = How.CSS, using = ".meta-wrap > span:nth-child(2) > div > div > input")
    private WebElement dateTo;

    @FindBy(how = How.CSS, using = ".MuiOutlinedInput-multiline > textarea")
    private WebElement description;

    @FindBy(how = How.CSS, using = ".rw-widget-container > div > input")
    private WebElement hashtags;
    private final By findListOfHashtags=By.cssSelector(".rw-widget-container ul li > span");

    private final By selectorCountryOption=By.cssSelector(".MuiInputBase-formControl > [name=\"countryId\"] >option");
    private Select country;
    private final By findCountry=By.cssSelector(".MuiInputBase-formControl > [name=\"countryId\"]");

    private final By selectorCityOption=By.cssSelector(".MuiInputBase-formControl >[name=\"cityId\"] > option");
    private Select city;
    private final By findCity=By.cssSelector(".MuiInputBase-formControl >[name=\"cityId\"]");

    @FindBy(how =How.CSS, using = ".pl-md-4 > button")
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

    @FindBy(how = How.CSS, using = ".m-auto > form > button")
    private WebElement save;

    @FindBy(how = How.CSS, using = ".preview-container > .error")
    private WebElement requiredImage;

    @FindBy(how = How.CSS,using = ".mt-2:nth-child(3) > div > p")
    private WebElement requiredTitle;

    @FindBy(how = How.CSS, using = ".mt-2:nth-child(7) > div > p")
    private WebElement requiredDescription;

    @FindBy(how = How.CSS,using = ".mt-2 > p")
    private WebElement requiredHashtags;

    @FindBy(how = How.CSS, using = ".mt-2:nth-child(9) > div > p")
    private WebElement requiredCountry;

    @FindBy(how = How.CSS, using = ".mt-2:nth-child(10) > div > p")
    private WebElement requiredCity;

    @FindBy(how = How.CSS, using = "#client-snackbar")
    private WebElement createdEventMessage;


    public AddEventPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForElementToAppear(findCountry);
        country=new Select(driver.findElement(findCountry));
    }

    public void loadImage(String nameFileImg) {
        fileUploader.sendKeys(nameFileImg);
    }

    public String getAltAttribute(WebElement element){
        return element.getAttribute(ALT_ATTRIBUTE);
    }

    public void inputTitle(String text) {
        title.clear();
        title.sendKeys(text);
    }

    public void inputParticipants(String value){
        participants.clear();
        participants.sendKeys(value);
    }

    public static String convertDateToCorrect(String date, LocalDate noLessThisDate){
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

    public void clearDateFrom(){
        dateFrom.sendKeys(Keys.CONTROL + "A");
        dateFrom.sendKeys(Keys.DELETE);
    }

    public void inputDateFrom(String date){
        dateFrom.sendKeys(date);
        dateFrom.sendKeys(Keys.ENTER);
    }

    public void clearDateTo() {
        dateTo.sendKeys(Keys.CONTROL + "A");
        dateTo.sendKeys(Keys.DELETE);
    }

    public void inputDateTo(String date) {
        dateTo.sendKeys(date);
        dateTo.sendKeys(Keys.ENTER);
    }

    public String getValueAttribute(WebElement element){
        return element.getAttribute(VALUE_ATTRIBUTE);
    }

    public void inputDescription(String text) {
        description.clear();
        description.sendKeys(text);
    }

    public List<String> getValueOfHashtags(){
        List<WebElement> listOfHashtagsWebElements = driver.findElements(findListOfHashtags);
        List<String> listOfHashtags = new ArrayList<>();
        for(WebElement element: listOfHashtagsWebElements)listOfHashtags.add(element.getText());
        return listOfHashtags;
    }

    public void inputHashtags(List<String> hashtagsToEnter) {
        hashtags.clear();
        for (String str : hashtagsToEnter) {
            hashtags.sendKeys(str);
            hashtags.sendKeys(Keys.ENTER);
        }
        hashtags.sendKeys(Keys.ESCAPE);
    }

    public String getValueOfCountry(){
        return country.getFirstSelectedOption().getText();
    }

    public void inputCountry(String text) {
        waitForElementToBeClickable(selectorCountryOption);
        country.selectByVisibleText(text);
    }

    public void initCity(){
        try {
            city.getFirstSelectedOption();
        }catch (NoSuchElementException | NullPointerException e){
            waitForElementToAppear(findCity);
            city = new Select(driver.findElement(findCity));
        }
    }

    public int getCountriesAmountFromPage(){
        waitForElementToAppear(selectorCountryOption);
        return country.getOptions().size();
    }

    public void inputCity(String text){
        waitForElementToAppear(selectorCityOption);
        city.selectByVisibleText(text);
    }

    public int getCitiesAmountFromPage(){
        waitForElementToAppear(selectorCityOption);
        return city.getOptions().size();
    }

    public String getValueOfCity(){
        return city.getFirstSelectedOption().getText();
    }

    public void clickClear(){
        clear.click();
    }

    public boolean isAppearCreatedEventMessage(){
        waitForElementToVisible(createdEventMessage);
        return createdEventMessage.isDisplayed();
    }

    public boolean isPageEmpty(){
        List<WebElement> listOfHashtagsWebElements = driver.findElements(findListOfHashtags);
        return Helper.isElementPresent(fileUploader) &&
                    title.getAttribute(VALUE_ATTRIBUTE).isEmpty() &&
                    participants.getAttribute(VALUE_ATTRIBUTE).isEmpty() &&
                    description.getAttribute(VALUE_ATTRIBUTE).isEmpty() &&
                    listOfHashtagsWebElements.isEmpty() &&
                    country.getFirstSelectedOption().getText().isBlank();
    }

    public boolean isPageFull(){
        List<WebElement> listOfHashtagsWebElements = driver.findElements(findListOfHashtags);
        return Helper.isElementPresent(image) &&
                    !title.getAttribute("value").isEmpty() &&
                    !participants.getAttribute("value").isEmpty() &&
                    !dateFrom.getAttribute("value").isEmpty() &&
                    !description.getAttribute("value").isEmpty() &&
                    !listOfHashtagsWebElements.isEmpty() &&
                    !country.getFirstSelectedOption().getText().isBlank() &&
                    !city.getFirstSelectedOption().getText().isBlank();

    }


    public void clickSave(){
        save.submit();
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

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public By getFindListOfHashtags() {
        return findListOfHashtags;
    }
}
