package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.EventElement;


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
        new EventElement(driver, editProfileButton).click();
        return this;
    }

    public HomePageNavBar clickNotificationButton() {
        new EventElement(driver, notificationsButton).click();
        return this;
    }

    public HomePageNavBar clickSignOutButton() {
        new EventElement(driver, signOutButton).click();
        return this;
    }

    public HomePageNavBar clickHomeButton() {
        new EventElement(driver, homeButton).click();
        return this;
    }

    public HomePageNavBar clickProfileButton() {
        new EventElement(driver, profileButton).click();
        return this;
    }

    public HomePageNavBar clickSearchUsersButton() {
        new EventElement(driver, searchUsersButton).click();
        return this;
    }

    public HomePageNavBar clickComunaButton() {
        new EventElement(driver, comunaButton).click();
        return this;
    }

    public HomePageNavBar clickContactUsButton() {
        new EventElement(driver, contactUsButton).click();
        return this;
    }


    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://eventsexpress-test.azurewebsites.net/home/events/?page=1");
        SignInUpMenu signInUpMenu = new SignInUpMenu(driver);
        signInUpMenu.authoriseUser("carat98@icloud.com", "12345678");
        HomePageNavBar homePageNavBar = new HomePageNavBar(driver);
        homePageNavBar.clickProfileButton();
    }

}
