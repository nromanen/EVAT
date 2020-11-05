package utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EventElement implements WebElement {
    private WebElement element;
    private WebDriverWait wait;

    public EventElement(WebDriver driver, WebElement element) {
        this.element = element;
        wait = new WebDriverWait(driver,30);
    }

    @Override
    public void click() {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void clickAndWait(WebElement element){
        click();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickAndWait(List<WebElement> elements){
        click();
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    @Override
    public void submit() {

    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {

    }

    @Override
    public void clear() {

    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String name) {
        return null;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
