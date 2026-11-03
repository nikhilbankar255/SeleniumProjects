package Utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import BaseClass.TestBase;

public class ExtentTestListener implements ITestListener {

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();
    
    @Override
    public void onStart(ITestContext context) {

        System.out.println("Test Suite Started");

    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = TestBase.extent.createTest(
                 result.getTestClass().getName() +" - "+result.getMethod().getMethodName());

        test.set(extentTest);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());
        String screenshotPath= captureScreenshot(result);
        if(screenshotPath!=null)
        {
        		try
        		{
        			test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        		
        		}
        		catch (Exception e) {
        			test.get().warning("Unable to attch screenshot: "+e.getMessage());
        		}
        }
    }

    
	@Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped->"+result.getThrowable());
    }
    
    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Test Suite Execution Completed");

    }

    public String captureScreenshot(ITestResult result) {
    		WebDriver driver =TestBase.driver;
    		
    		if(driver == null)
    		{
    			return null;
    		}
    		try {
				String timestamp=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
				String folder= "Screenshots";
				
				Files.createDirectories(Paths.get(folder));
				String 	fileName= result.getTestClass().getName()+ " - " + result.getMethod().getMethodName()+" - "+ timestamp +".png";
				
				String path= new File(folder, fileName).getAbsolutePath();
				    			
				File source = ((TakesScreenshot) driver)
	                    .getScreenshotAs(OutputType.FILE);
    		
				Files.copy(source.toPath(), Paths.get(path));
				return path;
				
    			} catch (Exception e) {
    				System.out.println("Screenshot error: " + e.getMessage());
    	            return null;
    			}
    		
	}

}
