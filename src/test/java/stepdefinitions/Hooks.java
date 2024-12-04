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

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
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
		test = reports.createTest(scenario.getName());
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {
        logger.info("Tearing down scenario: " + scenario.getName() + " on thread: " + Thread.currentThread().getId());
		if (scenario.isFailed()) {
			test.fail("Test is failed"+ scenario.getName());
		} else {
			test.pass("Test passed-"+scenario.getName());
		}
	}
	@AfterAll
	public static void afterAll() {
	    reports.flush();
	}
	

}
