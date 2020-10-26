package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageNavBar {
    WebDriver driver;

    //ProfileButtons

    @FindBy(css = ".flex-column > div:nth-child(3) > a:nth-child(1) > button:nth-child(1)")
    WebElement editProfileButton;

    @FindBy(css = ".flex-column > div:nth-child(3) > a:nth-child(2) > button:nth-child(1)")
    WebElement notificationsButton;

    @FindBy(css = ".flex-column > div:nth-child(3) > a:nth-child(3) > button:nth-child(1)")
    WebElement signOutButton;

    //NavBarButtons
    @FindBy(css = "li.sidebar-header:nth-child(1) > a:nth-child(1) > span:nth-child(1)")
    WebElement homeButton;

    @FindBy(css = "li.sidebar-header:nth-child(2) > a:nth-child(1) > span:nth-child(1)")
    WebElement profileButton;

    @FindBy(css = "li.sidebar-header:nth-child(3) > a:nth-child(1) > span:nth-child(1)")
    WebElement searchUsersButton;

    @FindBy(css = "li.sidebar-header:nth-child(4) > a:nth-child(1) > span:nth-child(1)")
    WebElement comunaButton;

    @FindBy(css = "li.sidebar-header:nth-child(5) > a:nth-child(1) > span:nth-child(1)")
    WebElement contactUsButton;


    public HomePageNavBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePageNavBar clickEditProfileButton() {
        editProfileButton.click();
        return new HomePageNavBar(driver);
    }

    public HomePageNavBar clickNotificationButton() {
        notificationsButton.click();
        return new HomePageNavBar(driver);
    }

    public HomePageNavBar clickSignOutButton() {
        signOutButton.click();
        return new HomePageNavBar(driver);
    }

    public HomePageNavBar clickHomeButton() {
        homeButton.click();
        return new HomePageNavBar(driver);
    }

    public HomePageNavBar clickProfileButton() {
        profileButton.click();
        return new HomePageNavBar(driver);
    }

    public HomePageNavBar clickSearchUsersButton() {
        searchUsersButton.click();
        return new HomePageNavBar(driver);
    }

    public HomePageNavBar clickComunaButton() {
        comunaButton.click();
        return new HomePageNavBar(driver);
    }

    public HomePageNavBar clickContactUsButton() {
        contactUsButton.click();
        return new HomePageNavBar(driver);
    }

    //waiter for navbar elements
    public HomePageNavBar waitForNavBarElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
        return new HomePageNavBar(driver);
    }


}
