package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import constants.Constants;
import driverManager.DriverManager;

public class CommonUtils {
	private static final Logger logger = LogManager.getLogger(CommonUtils.class);
	private volatile static CommonUtils instance;

	private CommonUtils() {
	}

	public static CommonUtils getInstance() {
		if (instance == null) {
			synchronized (CommonUtils.class) {
				if (instance == null) {
					instance = new CommonUtils();
				}
			}
		}
		return instance;
	}

	public static void loadProperties(String browserName) throws IOException {
		try {
			FileReader reader = new FileReader(new File("src\\test\\resources\\Config.properties"));
			Properties properties = new Properties();
			properties.load(reader);
			Constants.application_url = properties.getProperty("application_url");
			logger.info("browserName is :"+browserName);
			logger.info("ApplicationUrl is :"+Constants.application_url );
			//Constants.browserName = properties.getProperty("browser_name");
			//Constants.browserName=browserName;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String screenshot(String fileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
	    File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
	    String screenshotPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "screenshots";
	    File directory = new File(screenshotPath);
	    if (!directory.exists()) {
	        directory.mkdirs();
	    }
	    File destination = new File(directory, fileName + ".png");
	    FileHandler.copy(screenshotAs, destination);
	    return destination.getAbsolutePath();
	}

	public static String screenshotAsBase64(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
	    return ts.getScreenshotAs(OutputType.BASE64);
	}

	public static byte[] screenshotAsByte(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BYTES);
	}
	public static String todayDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		return sdf.format(date);
	}
	public static void cleanAllureResults() {
		File allureResultsDir = new File(System.getProperty("user.dir") + "/allure-results");
	    if (allureResultsDir.exists()) {
	        for (File file : Objects.requireNonNull(allureResultsDir.listFiles())) {
	            file.delete();
	        }
	    }
    }

}
