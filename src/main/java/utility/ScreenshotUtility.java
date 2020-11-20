package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtility {

    public static void captureScreenshot(WebDriver driver, String screenshotName)
    {
        try
        {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("src/test/resources/screenshots/"+screenshotName+".png"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
