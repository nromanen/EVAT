package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class SearchMenu {
    WebDriver driver;

    @FindBy(name = "search")
    WebElement keyword;

    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/form/div[6]/button[1]")
    WebElement reset;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/form/div[3]/button[2]")
    WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/form/div[2]/div[2]/div" )
    WebElement fromDatePicker;

    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/form/div[3]/div[2]/div/input")
    WebElement toDatePicker;

    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/form/div[4]/div/div[5]/div/input")
    Select hashtagsDropList;

    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/form/div[5]/button")
    WebElement moreCollapse;

    @FindBy(xpath = "//*[@id=\"main\"]/div[2]/div/div[1]/div/div[1]/div[1]/a/div")
    WebElement eventAvatar;

    public SearchMenu(WebDriver webDriver){
        this.driver=webDriver;
        PageFactory.initElements(driver,this);
    }

    public void setKeyword(String text){
        keyword.sendKeys(text);
    }

    public void setFromDate(LocalDate date){
        fromDatePicker.sendKeys(date.getMonth()+"/"+date.getDayOfMonth()+"/"+date.getYear());
    }

    public void setToDate(LocalDate date){
        toDatePicker.sendKeys(date.getMonth()+"/"+date.getDayOfMonth()+"/"+date.getYear());
    }

    public void clickSearch(){
        searchButton.click();
    }

    public void clickReset(){
        reset.click();
    }

    public void clickKeyword(){
        keyword.click();
    }

    public WebElement getEventAvatar(){
        return eventAvatar;
    }
    public void clickFromDate(){
        fromDatePicker.click();
    }
    public void clickToDate(){
        toDatePicker.click();
    }

    public void selectHashtag(String text){
        hashtagsDropList.selectByVisibleText(text);
    }



}
