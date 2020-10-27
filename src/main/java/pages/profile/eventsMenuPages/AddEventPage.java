package pages.profile.eventsMenuPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public class AddEventPage{

    WebDriver driver;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.preview-container > div > input[type=file]")
    private WebElement fileUploader;

    final String imageSelector="#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.preview-container > div > div > img";

    @FindBy(how = How.CSS, using = imageSelector)
    private WebElement image;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(3) > div > div > input")
    private WebElement title;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.meta-wrap.m-2 > span > div.react-datepicker-wrapper > div > input")
    private WebElement dateFrom;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.meta-wrap.m-2 > span:nth-child(2) > div.react-datepicker-wrapper > div > input")
    private WebElement dateTo;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(5) > div > div > textarea")
    private WebElement description;

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div:nth-child(6) > div > div.rw-widget-input.rw-widget-picker.rw-widget-container > div > input")
    private WebElement hashtags;

    @FindBy(how =How.NAME, using = "countryId")
    private WebElement countryElement;
    private Select country=new Select(countryElement);
    //Select select = new Select(driver.findElement(By.id("cars")));

    @FindBy(how =How.NAME, using = "cityId")
    private Select city;

    @FindBy(how =How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > button")
    private WebElement clear;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorIncorrectTitle;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorIncorrectTitleNoOneLetter;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorIncorrectDescription;

    @FindBy(how = How.CSS, using = "")
    private WebElement errorTooMuchHashtags;

    //Required?????????????????????????????????????????????????????????????

    @FindBy(how = How.CSS, using = "#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > button > span.MuiTouchRipple-root")
    private WebElement save;

    public AddEventPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean loadImage(String nameFileImg) {
        if (fileUploader==null) return false;
        fileUploader.sendKeys(nameFileImg);
        image = driver.findElement(By.cssSelector("#main > div.mt-2 > div.shadow.mb-5.bg-white.rounded > div > form > div > div.preview-container > div > div > img"));
        return true;
    }

    public String getValueImage(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(imageSelector)));
        if (image==null) return "";
        return image.getAttribute("alt");
    }

    public boolean inputTitle(String text) {
        if (title==null) return false;
        title.sendKeys(text);
        return true;
    }

    public boolean inputDateFrom(LocalDate date) {
        if (dateFrom==null) return false;
        dateFrom.sendKeys(Keys.CONTROL + "A");
        dateFrom.sendKeys(Keys.DELETE);
        dateFrom.sendKeys(date.getMonth().getValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
        dateFrom.sendKeys(Keys.ENTER);
        return true;
    }

    public boolean inputDateTo(LocalDate date) {
        if (dateTo==null) return false;
        dateTo.sendKeys(Keys.CONTROL + "A");
        dateTo.sendKeys(Keys.DELETE);
        dateTo.sendKeys(date.getMonth().getValue() + "/" + date.getDayOfMonth() + "/" + date.getYear());
        dateTo.sendKeys(Keys.ENTER);
        return true;
    }

    public boolean inputDescription(String text) {
        if (description.isEnabled()) {
            description.sendKeys(text);
        } else return false;
        return true;
    }

    public boolean inputHashtags(String[] hashtagsToEnter) {
        if (hashtags==null) return false;
        for (String str : hashtagsToEnter) {
            hashtags.sendKeys(str);
            hashtags.sendKeys(Keys.ENTER);
        }
        return true;
    }

    public boolean inputCountry(String text) {
        if (country == null) {
            country=new Select(driver.findElement(By.name("countryId")));
        }
        if(country.getOptions()!= null) {
            country.selectByVisibleText(text);
        }
        else return false;
        return true;
    }

    public boolean inputCity(String text){
        if(country==null)
            return false;
        else {  if (city == null) {
                    new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.name("cityId")));
                    city = new Select(driver.findElement(By.name("cityId")));
                }
                if (city.getOptions() != null){
                    new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#age-native-simple > option:nth-child(2)")));
                    city.selectByVisibleText(city.getOptions().get(1).getText());
                }
                else return false;
        }
        return true;
    }

    public boolean clickClear(){
        if(clear==null)return false;
        clear.click();
        return true;
    }

    public boolean clickSave(){
        if(save==null)return false;
        save.submit();
        return true;
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

    public WebElement getErrorTooMuchHashtags() {
        return errorTooMuchHashtags;
    }

    public void setErrorTooMuchHashtags(WebElement errorTooMuchHashtags) {
        this.errorTooMuchHashtags = errorTooMuchHashtags;
    }
}
