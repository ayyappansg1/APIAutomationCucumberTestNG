package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.Constants;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {
	private static final Logger logger = LogManager.getLogger(CommonUtils.class);
//	private volatile static CommonUtils instance;
//
//	private CommonUtils() {
//	}
//
//	public static CommonUtils getInstance() {
//		if (instance == null) {
//			synchronized (CommonUtils.class) {
//				if (instance == null) {
//					instance = new CommonUtils();
//				}
//			}
//		}
//		return instance;
//	}
	public static void loadConfigProperties() throws IOException {
		try {
			FileReader reader = new FileReader(new File("src\\test\\resources\\PropertyFiles\\Config.properties"));
			Properties properties = new Properties();
			properties.load(reader);
			Constants.baseURI = properties.getProperty("baseURI");
			Constants.userName  = properties.getProperty("userName");
			Constants.password = properties.getProperty("password");
			logger.info("BaseURI is :"+Constants.baseURI );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Properties initProperties(String fileName) {
		Properties properties=new Properties();
		try {
			FileInputStream stream=new FileInputStream(new File("src\\test\\resources\\PropertyFiles\\Products\\"+fileName+".properties"));
			properties.load(stream);
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
		
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
	public static String getExpectedJSONFileAsString(String dataFileName) throws IOException {
		byte[] readAllBytes = Files.readAllBytes(Paths.get("./src/test/resources/ExpectedJSONs/"+dataFileName+".json"));
		return new String(readAllBytes);
	}
	public static String getJSONSchemaFileAsString(String dataFileName) throws IOException {
		byte[] readAllBytes = Files.readAllBytes(Paths.get("./src/test/resources/JSONSchemas/"+dataFileName+".json"));
		return new String(readAllBytes);
	}
	public static String getSerialisedJSON(Object object) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
	}

}
