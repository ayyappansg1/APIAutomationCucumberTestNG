package baseClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import client.RestClient;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import utils.CommonUtils;
import utils.ExtentReportListener;
import utils.ExtentReportManager;

public class BaseClass{
	private static final Logger logger=LogManager.getLogger(BaseClass.class);
	protected MultiPartEmail email;
	ExtentReports extent = ExtentReportManager.getInstance();
	public ExtentReportListener extentReportListener;

	

	@BeforeClass(alwaysRun = true)
	public void parallelCheck(String browser) throws IOException {
		extentReportListener = new ExtentReportListener();
		CommonUtils.loadConfigProperties();
	}
	@AfterSuite(alwaysRun = true)
	public void sendEmail() throws EmailException {
		//generateAllureReport(); // Step 1: Generate Allure report
	    //File allureZip = zipAllureReport(); // Step 2: Zip the report

	    StringBuilder emailContent = new StringBuilder();
	    emailContent.append("Test Results: ");
	    
	    // Attach Extent report
	    EmailAttachment  extentAttachment = new EmailAttachment();
	    extentAttachment.setPath(System.getProperty("user.dir") + File.separator + "extentSpark.html");
	    extentAttachment.setDisposition(EmailAttachment.ATTACHMENT);
	    extentAttachment.setDescription("Selenium Extent Report");
	    extentAttachment.setName("ExtentReport.html");

	     //Attach Allure report zip
//	    EmailAttachment allureAttachment = new EmailAttachment();
//	    allureAttachment.setPath(allureZip.getAbsolutePath());
//	    allureAttachment.setDisposition(EmailAttachment.ATTACHMENT);
//	    allureAttachment.setDescription("Allure Report");
//	    allureAttachment.setName("AllureReport.zip");

	    email = new MultiPartEmail();
	    email.setHostName("smtp.zoho.in");
	    email.setSmtpPort(465);
	    email.setSSLOnConnect(true);
	    email.setAuthentication("ayyappansg1@zohomail.in", "FArvYhxi2Emk");
	    email.setFrom("ayyappansg1@zohomail.in");
	    email.addTo("ayyappangunasekaran5@gmail.com");
	    email.setMsg("Hi sir, Automated Mail");
	    email.setSubject("HerokuApp automation result-" + emailContent.toString() + "- " + dateAndTime());
	    email.setSocketConnectionTimeout(60000); 
	    email.setSocketTimeout(60000);

	    email.attach(extentAttachment);
	   // email.attach(allureAttachment); // Attach the zipped Allure report
	    
	    //     email.send();
		logger.info("Email sent successfully");
	    extent.flush();
	}

	private void generateAllureReport() {
		 try {
		        ProcessBuilder processBuilder = new ProcessBuilder("allure", "generate", "--clean");
		        processBuilder.redirectErrorStream(true); // Merge error and output streams
		        processBuilder.directory(new File(System.getProperty("user.dir"))); // Set working directory

		        Process process = processBuilder.start();
		        BufferedReader reader = new BufferedReader(new InputStreamReader (process.getInputStream()));
		        String line;
		        while ((line = reader.readLine()) != null) {
		            logger.info(line); // Log output for debugging
		        }
		        int exitCode = process.waitFor();
		        if (exitCode == 0) {
		            logger.info("Allure report generated successfully.");
		        } else {
		            logger.error("Allure report generation failed with exit code: " + exitCode);
		        }
		    } catch (IOException | InterruptedException e) {
		        logger.error("Failed to generate Allure report: " + e.getMessage());
		    }

	}

	private File zipAllureReport() {
		Path sourceDir = Paths.get(System.getProperty("user.dir"), "allure-report");
	    File zipFile = new File(System.getProperty("user.dir") + File.separator + "allure-report.zip");

	    try (FileOutputStream fos = new FileOutputStream(zipFile);
	         ZipOutputStream zos = new ZipOutputStream(fos)) {

	        Files.walk(sourceDir)
	            .filter(path -> !Files.isDirectory(path))
	            .forEach(path -> {
	                ZipEntry zipEntry = new ZipEntry(sourceDir.relativize(path).toString());
	                try (FileInputStream fis = new FileInputStream(path.toFile())) {
	                    zos.putNextEntry(zipEntry);
	                    byte[] buffer = new byte[1024];
	                    int length;
	                    while ((length = fis.read(buffer)) > 0) {
	                        zos.write(buffer, 0, length);
	                    }
	                    zos.closeEntry();
	                } catch (IOException e) {
	                    logger.error("Error zipping file: " + e.getMessage());
	                }
	            });
	    } catch (IOException e) {
	        logger.error("Failed to create zip file: " + e.getMessage());
	    }
	    return zipFile;
	}

	public String dateAndTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMMyyyy h.mma");
		String formattedDateTime = currentDateTime.format(formatter);
		return formattedDateTime;
	}
	@Attachment(value = "{0}", type = "text/plain")
    public static String attachLog(String message) {
        logger.info(message);
        return message;
    }
	@AfterMethod(alwaysRun = true)
	public void tearDownAfterMethod(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE||result.getStatus()==ITestResult.SKIP) {
            attachLog(String.valueOf(result.getMethod()).replaceAll("[()]",""));
		}
	}

}
