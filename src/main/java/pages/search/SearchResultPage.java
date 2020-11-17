package pages.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import java.util.List;

public class SearchResultPage extends BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public SearchResultPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions
                .or(ExpectedConditions.visibilityOf(event),
                        ExpectedConditions.visibilityOf(noResultText)));
    }

    @FindBy(className = "MuiCardHeader-root")
    public static List<WebElement> numberOfEvents;

    @FindBy(className = "MuiCardHeader-root")
    public WebElement event;

    @FindBy(css = ".h1")
    public WebElement noResultText;

    @FindBy(id = "notfound")
    public WebElement spinner;

    public int getNumberOfEvents(){
        return numberOfEvents.size();
    }
    public String getNoResultText(){
        return noResultText.getText();
    }
}
