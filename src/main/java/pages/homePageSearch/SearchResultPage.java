package pages.homePageSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    public SearchResultPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(className = "MuiCardHeader-root")
    public List<WebElement> numberOfEvents;

    @FindBy(css = ".h1")
    public WebElement noResultText;

    public int getNumberOfEvents(){
        return numberOfEvents.size();
    }
    public String getNoResultText(){
        return noResultText.getText();
    }
}
