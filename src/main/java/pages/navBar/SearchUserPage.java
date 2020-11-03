package pages.navBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchUserPage {
    private WebDriver driver;
    public SearchUserPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(css = ".MuiInputBase-input")
    public WebElement searchField;

    @FindBy(css = "button.MuiButton-root:nth-child(1) > span:nth-child(1)")
    public WebElement clearButton;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(2) > span:nth-child(1)")
    public WebElement searchButton;

    @FindBy(css = ".col-12")
    public List<WebElement> numberOfUsers;

    public SearchUserPage typeInSearchField(String text){
        searchField.sendKeys(text);
        return this;
    }
    public SearchUserPage clickSearchButton(){
        searchButton.click();
        return this;
    }
    public SearchUserPage clickClearButton(){
        clearButton.click();
        return this;
    }
    public SearchUserPage searchUser(String name){
        typeInSearchField(name).clickSearchButton();
        return this;
    }

    public String getSearchFieldValue() {
        return searchField.getAttribute("value");
    }
    public int getNumberOfFoundedUsers(){
        return numberOfUsers.size();
    }
}
