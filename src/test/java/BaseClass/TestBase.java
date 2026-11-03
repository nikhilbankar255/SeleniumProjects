package BaseClass;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utility.EmailUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;

	public static ExtentReports extent;

	String timestamp= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss"));
	
	@BeforeSuite
	public void setUp() {
//use timestamp with file
		ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/AutomationReport_"+ timestamp +".html");
		reporter.config().setDocumentTitle("Ecommerce Test Report");
		reporter.config().setReportName("Selenium test report");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Nikhil Bankar");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", "Chrome");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		
		if(extent!=null)
		{
			extent.flush();
            System.out.println("Extent Report Generated Successfully");
            String reportPath= "Reports/AutomationReport_"+ timestamp+ ".html";
            EmailUtility.sendReport(reportPath);
		}
	}
}