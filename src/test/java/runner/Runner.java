package runner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import baseClass.BaseClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.CommonUtils;
import utils.ExtentReportListener;
import utils.ExtentReportManager;

/**
 * This is a Runner Class Execution starts from here.Additional runner classes
 * also can be Maintained
 */

@CucumberOptions(dryRun = false, features = "src\\test\\resources\\FeatureFiles", glue = {
		"stepdefinitions" }, monochrome = true, plugin = { "rerun:target/failed.txt", "json:target/forReporting.json",
				"html:target/sangar.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }, tags = "@smoke")
public class Runner extends AbstractTestNGCucumberTests {
	private static final Logger logger=LogManager.getLogger(Runner.class);

	@BeforeSuite(alwaysRun = true)
	public void initialisation() {
		logger.info("Initialisation");
		CommonUtils.cleanAllureResults();
	}
}
