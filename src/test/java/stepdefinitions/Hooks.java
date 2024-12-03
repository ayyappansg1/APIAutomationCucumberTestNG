package stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import driverManager.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utils.AllureLogger;
import utils.CommonUtils;

/**
 * This @Before @After methods gets invoked automatically and will be execute as
 * per the name
 */
public class Hooks {
	private final static Logger logger = LogManager.getLogger(Hooks.class);
	public static ExtentReports reports;
	public static ExtentTest test;
	  static {
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport/AutomationReport.html");
	        sparkReporter.config().setReportName("Test Report");
	        sparkReporter.config().setDocumentTitle("Automation Results");

	        reports = new ExtentReports();
	        reports.attachReporter(sparkReporter);
	    }
	@Before
	public void beforeScenario(Scenario scenario) throws IOException {
        logger.info("Starting scenario: " + scenario.getName() + " on thread: " + Thread.currentThread().getId());
		if (DriverManager.getDriver() == null) {
            logger.info("Driver is null in beforeScenario, initializing for thread: " + Thread.currentThread().getId());
	        DriverManager.getDriver();  // No need for a separate launchBrowser call
	    }		//	DriverManager.launchBrowser();
	    //ExtentSparkReporter reporter = new ExtentSparkReporter("AutomationReport_" + Thread.currentThread().getId() + ".html");
		//reports = new ExtentReports();
		//reporter.config().setReportName("Test Report");
		//reporter.config().setDocumentTitle("Automation Results");
		//reports.attachReporter(reporter);
		logger.info("Browser name: " + DriverManager.getBrowserName());
		test = reports.createTest(scenario.getName()+"-"+DriverManager.getBrowserName());
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {
        logger.info("Tearing down scenario: " + scenario.getName() + " on thread: " + Thread.currentThread().getId());
		if (scenario.isFailed()) {
			test.fail("Test is failed"+ scenario.getName());
            logger.info("Scenario failed, capturing screenshot for thread: " + Thread.currentThread().getId());
			String screenshot = CommonUtils.screenshot(scenario.getName());
			FileInputStream stream = new FileInputStream(new File(screenshot));
			Allure.addAttachment("Screenshot", stream);
			byte[] screenshotAsByte = CommonUtils.screenshotAsByte(DriverManager.getDriver());
			String base64Screenshot = Base64.getEncoder().encodeToString(screenshotAsByte);
			scenario.attach(screenshotAsByte, "image/png", screenshot);
            test.addScreenCaptureFromPath(screenshot, "Failure Screenshot");
            test.addScreenCaptureFromBase64String(base64Screenshot, "Screenshot on failure");
		} else {
			test.pass("Test passed-"+scenario.getName());
		}
	    AllureLogger.attachLogFile();
	    DriverManager.quitDriver();
	    //DriverManager.closeBrowser();
	   // reports.flush();
	}
	@AfterAll
	public static void afterAll() {
	    reports.flush();
	}
	

}
