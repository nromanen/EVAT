package base;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    /**
     * Take a screenshot and save it in package and add to allure report
     * @param result
     */
    public static void captureScreenshot(ITestResult result){
        try{
            TakesScreenshot ts=(TakesScreenshot)BaseTest.getDriver();
            FileUtils.copyFile( ts.getScreenshotAs(OutputType.FILE), new File("src/test/resources/screenshots/"+result.getName()+"-"+
                    (result.getMethod().getCurrentInvocationCount()-1)+
                    result.getTestClass().getName()+".png"));
            Allure.addAttachment(result.getName(), new ByteArrayInputStream( ts.getScreenshotAs(OutputType.BYTES)));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}