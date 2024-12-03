package pages;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.base.Optional;

import driverManager.DriverManager;
import helper.LocalHelper;

public class SecondPage extends LocalHelper {
	private static final Logger logger = LogManager.getLogger(SecondPage.class);
	//private volatile static SecondPage instance;
	List<String> pressKeysAndReturnTextFromChangeOccured;
	String parentWindow;
	String tableValue;
	String firstFlash;
	String beforeClickCssValue;
	WebDriver driver;
	public SecondPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

//	public static SecondPage getInstance() {
//		if (instance == null) {
//			//synchronized (HomePage.class) {
//				if (instance == null) {
//					instance = new SecondPage();
//				}
//		//	}
//		}
//		return instance;
//	}

	@FindBy(xpath = "//button[text()='Where am I?']")
	private WebElement whereAmIButton;
	@FindBy(css = "#lat-value")
	private WebElement lattitudeText;
	@FindBy(css = "#long-value")
	private WebElement longitudeText;
	@FindBy(xpath = "//input[@type='range']")
	private WebElement slider;
	@FindBy(xpath = "//span[@id='range']")
	private WebElement sliderText;
	@FindBy(xpath = "//div/img")
	private List<WebElement> images;
	@FindBy(css = ".figcaption h5")
	private List<WebElement> imageText;
	@FindBy(css = ".example input")
	private WebElement numberBox;
	@FindBy(xpath = "//a[text()='Enabled']")
	private WebElement enabledTab;
	@FindBy(xpath = "//a[text()='Enabled']//following::ul[@aria-expanded='true']/child::li/a")
	private List<WebElement> enabledOptions;
	@FindBy(xpath = "//a[text()='Downloads']")
	private WebElement downloadsTab;
	@FindBy(xpath = "//a[text()='Menu']")
	private WebElement menuHyperLink;
	@FindBy(xpath = "//a[text()='Back to JQuery UI']")
	private WebElement backToJQueryUI;
	@FindBy(xpath = "//a[text()='Downloads']//following::ul//li/a")
	private List<WebElement> downloadsOptions;
	@FindBy(xpath = "//button[text()='Click for JS Alert']")
	private WebElement clickForJsAlertButton;
	@FindBy(xpath = "//button[text()='Click for JS Confirm']")
	private WebElement clickForJsAlertConfirmButton;
	@FindBy(xpath = "//button[text()='Click for JS Prompt']")
	private WebElement clickForJsAlertPromptButton;
	@FindBy(css = "#result")
	private WebElement resultText;
	@FindBy(xpath  = "//p")
	private WebElement jsErrorText;
	@FindBy(xpath  = "(//th/span[text()='21']//following::tr)[6]//td[22]")
	private WebElement tableCellValue;
	@FindBy(xpath  = "//a[text()='Click Here']")
	private WebElement clickHereToOpenNewWindow;
	@FindBy(xpath  = "//h3")
	private WebElement newWindowText;
	@FindBy(xpath  = "//frame[@src='/frame_top']")
	private WebElement topFrame;
	@FindBy(xpath  = "//frame[@src='/frame_right']")
	private WebElement rightFrame;
	@FindBy(xpath  = "//body")
	private WebElement rightFrameContent;
	@FindBy(xpath  = "//frame[@src='/frame_bottom']")
	private WebElement bottomFrame;
	@FindBy(xpath  = "//body")
	private WebElement bottomFrameContent;
	@FindBy(css  = "#flash")
	private WebElement flashMessage;
	@FindBy(xpath   = "//a[text()='Click here']")
	private WebElement clickHereNotificationLoad;
	@FindBy(css   = "#redirect")
	private WebElement redirectClick;
	@FindBy(css = "my-paragraph:first-of-type")
	private WebElement shadowHost;
	@FindBy(css = "my-paragraph:nth-of-type(2)")
	private WebElement shadowHostSecond;
	//@FindBy(xpath = "//ul[@slot='my-text']/li")
	private By secondParaContents=By.cssSelector("[slot='my-text'] li");
	private By firstParagraph=By.cssSelector("[slot='my-text']");
	@FindBy(xpath = "//a[text()='Example 1: Menu Element']")
	private WebElement menuElement;
	@FindBy(xpath = "//a[text()='Example 2: An image']")
	private WebElement anImageElement;
	@FindBy(xpath = "//a[text()='Example 3: List']")
	private WebElement listElement;
	@FindBy(xpath = "//code[text()='?mode=random']//following-sibling::a[text()='click here']")
	private WebElement clickHereToReloadForImage;
	@FindBy(xpath = "//ul/li/a")
	private List<WebElement> tabNames;
	@FindBy(css = "img.shift")
	private WebElement imgLocation;
	@FindBy(xpath = "//div[@id='content']//child::div[contains(@class,'large-centered')]")
	private WebElement centreContents;
	@FindBy(xpath = "//table[@id='table1']/tbody//td[1]")
	private List<WebElement> example1LastNames;
	@FindBy(xpath = "//table[@id='table1']/tbody//td[2]")
	private List<WebElement> example1FirstNames;
	@FindBy(xpath = "//table[@id='table1']/tbody//td[3]")
	private List<WebElement> example1Email;
	@FindBy(xpath = "//table[@id='table1']/tbody//td[4]")
	private List<WebElement> example1Due;
	@FindBy(xpath = "//table[@id='table1']/tbody//td[5]")
	private List<WebElement> example1WebSite;
	@FindBy(xpath = "//table[@id='table2']/tbody//td[1]")
	private List<WebElement> example2LastNames;
	@FindBy(xpath = "//table[@id='table2']/tbody//td[2]")
	private List<WebElement> example2FirstNames;
	@FindBy(xpath = "//table[@id='table2']/tbody//td[3]")
	private List<WebElement> example2Email;
	@FindBy(xpath = "//table[@id='table2']/tbody//td[4]")
	private List<WebElement> example2Due;
	@FindBy(xpath = "//table[@id='table2']/tbody//td[5]")
	private List<WebElement> example2WebSite;
	@FindBy(xpath = "//table[@id='table1']//span[text()='Last Name']")
	private WebElement lastNameHeaderExample1;
	@FindBy(xpath = "//table[@id='table1']//span[text()='First Name']")
	private WebElement firstNameHeaderExample1;
	@FindBy(xpath = "//table[@id='table1']//span[text()='Email']")
	private WebElement emailHeaderExample1;
	@FindBy(xpath = "//table[@id='table1']//span[text()='Due']")
	private WebElement dueHeaderExample1;
	@FindBy(xpath = "//table[@id='table1']//span[text()='Web Site']")
	private WebElement websiteHeaderExample1;
	@FindBy(xpath = "//table[@id='table2']//span[text()='Last Name']")
	private WebElement lastNameHeaderExample2;
	@FindBy(xpath = "//table[@id='table2']//span[text()='First Name']")
	private WebElement firstNameHeaderExample2;
	@FindBy(xpath = "//table[@id='table2']//span[text()='Email']")
	private WebElement emailHeaderExample2;
	@FindBy(xpath = "//table[@id='table2']//span[text()='Due']")
	private WebElement dueHeaderExample2;
	@FindBy(xpath = "//table[@id='table2']//span[text()='Web Site']")
	private WebElement websiteHeaderExample2;
	@FindBy(xpath = "//a[text()='200']")
	private WebElement TwoHundredHyperLink;
	@FindBy(xpath = "//a[text()='301']")
	private WebElement ThreeZeroOneHyperLink;
	@FindBy(xpath = "//a[text()='404']")
	private WebElement FourZeroFourHyperLink;
	@FindBy(xpath = "//a[text()='500']")
	private WebElement FiveZeroZeroHyperLink;
	@FindBy(xpath = "//a[@href='redirect']")
	private WebElement clickHereRedirectLink;
	public void clickWhereAmIButton() {
		clickElement(whereAmIButton, driver);
	}

	public boolean verifyBothLongtitudeAndLatitude(String latitude, String longititude) {
		boolean status = false;
		String latidudeActual = getTextContent(lattitudeText,driver);
		logger.info("Latitude values is :" + latidudeActual);
		status = latidudeActual != null;
		String longititudeActual = getTextContent(longitudeText,driver);
		logger.info("Longitude values is :" + longititudeActual);
		status = longititudeActual != null;
		return status;
	}

	public void performActionOnSlider() {
		logger.info("Performing slider functions");
		slidingUsingMouseAction(slider, sliderText,driver);
	}

	public boolean verifySliderText(Integer number) {
		logger.info("Verifying slider text");
		return getTextContent(sliderText,driver).equals(Integer.toString(number));
	}

	public boolean mouseHoverToEachImage(List<Map<String, String>> wholeList) {
		boolean status = false;
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < images.size(); i++) {
			mouseHover(images.get(i),driver);
			list.add(getTextContent(imageText.get(i),driver));
		}
		logger.info("list of String for hover text" + list);
		logger.info("WholeList size is:" + wholeList.size());
		Set<Entry<String, String>> entrySet2 = wholeList.get(0).entrySet();
		for (Entry<String, String> entry : entrySet2) {
			logger.info("ActualMap" + entry);
		}
		for (int i = 0; i < list.size(); i++) {
			String profile = list.get(i);
			logger.info("Profile name:" + profile);
			for (int j = 0; j < wholeList.size(); j++) {
				Set<Entry<String, String>> entrySet = wholeList.get(j).entrySet();
				for (Entry<String, String> entry : entrySet) {
					if (profile.contains(entry.getValue())) {
						status = true;
						break;
					} else {
						status = false;
					}
				}
			}
		}
		return status;
	}
	
	public void scrollFiveTimes(Integer num) throws InterruptedException {
		logger.info("BeforeScrolling "+num+" times");
		scrollNumberOfTimes(num,driver);
	}
	public void enterNumberAndClickAdd(Integer num) {
		sendValue(numberBox, driver, Integer.toString(num));
		sendValueWithArrow(numberBox);
	}
	public void mouseHoverToEnabled() {
		mouseHover(enabledTab,driver);
	}
	public boolean verifyAfterMouseHoverToEnabledTab(String first,String second) {
		boolean status=false;
		List<String> allTextContents = getAllTextContents(enabledOptions,driver);
		for (String string : allTextContents) {
			if(string.equals(first)) {
				status=true;
			}else if(string.equals(second)) {
				status=true;
			}else {
				status=false;
			}
		}
		return status;
	}
	public void clickOnJQueryBackUI() {
		clickElement(backToJQueryUI, driver);
	}
	public boolean verifyMenuHyperLink() {
		return verifyElement(menuHyperLink);
	}
	public void clickMenuRedirectLink() {
		clickElement(menuHyperLink, driver);
	}
	public boolean verifyRedirectionLink(String url) {
		return getCurrentPageUrl(driver).contains(url);
	}
	public void mouseHoverToDownloadsPage() {
		mouseHover(enabledTab,driver);
		mouseHover(downloadsTab,driver);
	}
	public boolean verifyDownloadsTabOptions(String first,String second,String third) {
		List<String> textFromFeatureFile = Arrays.asList(first,second,third);
		List<String> allTextContents = getAllTextContentsPlain(downloadsOptions,driver);
		return allTextContents.containsAll(textFromFeatureFile);
	}

	public void clicksOnPdfOption() {
		clickElement(downloadsOptions.get(0), driver);
	}
	public boolean verifyDownloadedFile() {
		return verifyFileName("menu");
	}
	public void clickJSAlertButton() {
		clickElement(clickForJsAlertButton, driver);
		handlingAlertWithAllPossibleActions("accept", null,driver);
	}
	
	public void clickJSAlertConfirmButton() {
		clickElement(clickForJsAlertConfirmButton, driver);
		handlingAlertWithAllPossibleActions("accept", null,driver);
	}
	public void clickJSAlertConfirmWithCancelButton() {
		clickElement(clickForJsAlertConfirmButton, driver);
		handlingAlertWithAllPossibleActions("dismiss", null,driver);
	}
	public void clickJSAlertPromptButton(String text) {
		clickElement(clickForJsAlertPromptButton, driver);
		handlingAlertWithAllPossibleActions("accept", text,driver);
	}
	public boolean verifyJSAlertButton(String text) {
		return getTextContent(resultText,driver).equals(text);
	}
	public boolean verifyUserLandingPageAfterClickJSOnloadError() {
		return verifyUrlContainsExpectedText("javascript_error", driver);
	}
	public boolean verifyJavascriptErrorOnPage(String text) {
		return getTextContent(jsErrorText,driver).contains(text);
	}
	public void pressKeysBasedOnGiven(List<String> list) {
		logger.info("Before key press");
		pressKeysAndReturnTextFromChangeOccured = pressKeysAndReturnTextFromChangeOccured(list,resultText,driver);
	}
	public boolean verifyKeysPressed(List<String> list) {
		return list.containsAll(pressKeysAndReturnTextFromChangeOccured);
	}
	public void fetchValueFromTable() {
		tableValue = getTextContent(tableCellValue,driver);
	}
	public boolean verifyingTheValueWithTableValue(Double value) {
		return tableValue.equals(Double.toString(value));
	}
	public void clickClickHereNewWindow() {
		parentWindow = getParentWindow(driver);
		clickElement(clickHereToOpenNewWindow, driver);
	}
	public boolean switchToNewWindowAndGrabText(String text) {
		switchToAnotherWindow(parentWindow,driver);
		String textContent = getTextContent(newWindowText,driver);
		logger.info("ACtual Text from web:"+textContent);
		logger.info("ACtual Text from feature file:"+text);
		boolean equals = textContent.equals(text);
		switchToParentWindow(parentWindow,driver);
		return equals;
	}
	public boolean fetchingRightFrameTextAndVerify(String right) {
		switchToFrame(driver, topFrame);
		switchToFrame(driver, rightFrame);
		String textContent = getTextContent(rightFrameContent,driver);
		logger.info("right Frame content"+textContent);
		switchToParent(driver);
		return textContent.contains(right);
	}
	public boolean fetchingBottomFrameTextAndVerify(String bottom) {
		switchToFrame(driver,bottomFrame);
		String textContent = getTextContent(bottomFrameContent,driver);
		switchToParent(driver);
		logger.info("Bottom Frame content"+textContent);
		return textContent.contains(bottom);
	}
	public void clickClickHereNotification() {
		clickElement(clickHereNotificationLoad, driver);
	}
	public boolean verifyTheFirstNotificationMessage(String flashMessageExp,String alternate) {
		firstFlash = getTextContent(flashMessage,driver);
		logger.info("First flash message"+firstFlash);
		boolean status=false;
		if(firstFlash.contains(flashMessageExp) ||firstFlash.contains(alternate)) status=true;
		return status;
	}
	public void clickClickHereNotificationUntilNewMessageAppears() {
		String textContent = getTextContent(flashMessage,driver);
		while(textContent.equals(firstFlash)) {
		clickElement(clickHereNotificationLoad, driver);
		textContent = getTextContent(flashMessage,driver);
		}
	}
	public boolean verifyTheSecondNotification() {
		String textContent = getTextContent(flashMessage,driver);
		logger.info("Second flash message"+textContent);
		if(!textContent.contains(firstFlash)) return true;
		else return false;
	}
	public void clickRedirectLink() {
		clickElement(redirectClick, driver);
	}
	public void enterCredentialsUsingRobot() throws AWTException {
		passingValuesInWindowPopup("admin", "admin");
	}
	public boolean checkDownloadedFileInLocal(String fileName) throws InterruptedException {
		Thread.sleep(5000);
		return verifyFileName(fileName);
	}
	public boolean gettingTextFromFirstParagraphShadowDom(String text) {
		return getTextFromShadowDomAccessing(shadowHost, "[slot='my-text']",driver).contains(text);
	}
	public boolean gettingTextFromSeconddParagraphShadowDom(String first,String second) {
		List<String> asList = Arrays.asList(first,second);
		List<String> textFromShadowDomAccessingMultiple = getTextFromShadowDomAccessingMultiple(shadowHostSecond, "[slot='my-text'] li",driver);
		return compareTwoListAndCheckItContainsStringIn(asList,textFromShadowDomAccessingMultiple);
	}
	public boolean clickMenuElementAndVerifyTabs(List<String> lists) {
		clickElement(menuElement, driver);
		List<String> allTextContents = getAllTextContents(tabNames,driver);
		return lists.containsAll(allTextContents);
	}
	public boolean clickHomeElementAndRedirectToHomePage() {
		clickElement(tabNames.get(0), driver);
		return getCurrentPageUrl(driver).equals("https://the-internet.herokuapp.com/");
	}
	public void clickAnImageButtonAndGetBeforeClickCssValue() {
		new HomePage(driver).clickShiftingContentButton();
		clickElement(anImageElement, driver);
		beforeClickCssValue = getCssValueFromParticularElement(imgLocation, "left");
		logger.info("Before clicking the reload pixel is:"+beforeClickCssValue);
		clickElement(clickHereToReloadForImage, driver);
	}
	public boolean verifyImageMovedAfterReload() {
		String text = getCssValueFromParticularElement(imgLocation, "left");
		logger.info("After reload css value  is :"+text);
		while(beforeClickCssValue.equals(text)) {
			clickElement(clickHereToReloadForImage, driver);
			text = getCssValueFromParticularElement(imgLocation, "left");
			logger.info("After every click :"+text);
		}
		return beforeClickCssValue.equals(text);
	}
	public void clickList() {
		directlylandOnParticularUrl("https://the-internet.herokuapp.com/shifting_content",driver);
		clickElement(listElement, driver);
	}
	public boolean compareContentsForEveryLoad() {
		String[] textContent = getTextContent(centreContents,driver).split("\n");
		List<String>list=new ArrayList<String>();
		for (String string : textContent) {
			list.add(string);
		}
		Consumer<String>c=(word)->logger.info("BeforeList"+word);
		list.forEach(c);
		refreshTheCurrentPage(driver);
		String[] textContentAfterRefresh = getTextContent(centreContents,driver).split("\n");
		List<String>AfterRefreshlist=new ArrayList<String>();
		for (String string : textContentAfterRefresh) {
			AfterRefreshlist.add(string);
		}
		Consumer<String>cs=(word)->logger.info("AfterList"+word);
		AfterRefreshlist.forEach(cs);
		return list.containsAll(AfterRefreshlist);
	}
	public boolean captureAndReturnUrls(int statusCode) throws URISyntaxException, IOException {
		List<String> useDevToolstoGrabAPINetworks = useDevToolstoGrabAPINetworks(driver);
		String actualURL = useDevToolstoGrabAPINetworks.
		stream().
		filter((string)->string.contains("slow_external")).toString();
		logger.info("Filtered API"+actualURL);
		return hitParticularAPIUsingInbuiltJava(actualURL)==statusCode;
	}
	public boolean verifyLastNameFirstTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example1LastNames,driver);
		Collections.sort(beforeSorting);
		logger.info("Before sorting Lastname column:"+beforeSorting);
		clickElement(lastNameHeaderExample1, driver);
		List<String> afterSorting = getAllTextContentsPlain(example1LastNames,driver);
		logger.info("After sorting fromActual web Lastname column:"+afterSorting);
		return beforeSorting.equals(afterSorting);
	}
	public boolean verifyFirstNameFirstTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example1FirstNames,driver);
		Collections.sort(beforeSorting);
		logger.info("Before sorting treeset firstname column:"+beforeSorting);
		clickElement(firstNameHeaderExample1, driver);
		List<String> afterSorting = getAllTextContentsPlain(example1FirstNames,driver);
		logger.info("After sorting fromActual web firstname column"+afterSorting);
		return beforeSorting.equals(afterSorting);
	}
	public boolean verifyEmailFirstTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example1Email,driver);
		Collections.sort(beforeSorting);
		logger.info("Before sorting Email column:"+beforeSorting);
		clickElement(emailHeaderExample1, driver);
		List<String> afterSorting = getAllTextContentsPlain(example1Email,driver);
		logger.info("After sorting fromActual web Email column"+afterSorting);
		return beforeSorting.equals(afterSorting);
	}
	public boolean verifyDueFirstTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example1Due,driver);
		List<Float>convertedToInt=new LinkedList<Float>();
		for (String string : beforeSorting) {
			convertedToInt.add(Float.parseFloat(string.replace("$", "")));
		}
		Collections.sort(convertedToInt);
		logger.info("Before sorting Due column:"+beforeSorting);
		clickElement(dueHeaderExample1, driver);
		List<String> afterSorting = getAllTextContentsPlain(example1Due,driver);
		List<Float>convertedToIntAfterSorting=new LinkedList<Float>();
		for (String string : afterSorting) {
			convertedToIntAfterSorting.add(Float.parseFloat(string.replace("$", "")));
		}
		logger.info("After sorting fromActual Due column"+afterSorting);
		return convertedToInt.equals(convertedToIntAfterSorting);
	}
	public boolean verifyWebSiteFirstTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example1WebSite,driver);
		Collections.sort(beforeSorting);
		logger.info("Before sorting website column:"+beforeSorting);
		clickElement(websiteHeaderExample1, driver);
		List<String> afterSorting = getAllTextContentsPlain(example1WebSite,driver);
		logger.info("After sorting fromActual website column"+afterSorting);
		return beforeSorting.equals(afterSorting);
	}
	public boolean verifyLastNameSecondTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example2LastNames,driver);
		Collections.sort(beforeSorting);
		logger.info("Before sorting Lastname column:"+beforeSorting);
		clickElement(lastNameHeaderExample2, driver);
		List<String> afterSorting = getAllTextContentsPlain(example2LastNames,driver);
		logger.info("After sorting fromActual web Lastname column:"+afterSorting);
		return beforeSorting.equals(afterSorting);
	}
	public boolean verifyFirstNameSecondTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example2FirstNames,driver);
		Collections.sort(beforeSorting);
		logger.info("Before sorting treeset firstname column:"+beforeSorting);
		clickElement(firstNameHeaderExample2, driver);
		List<String> afterSorting = getAllTextContentsPlain(example2FirstNames,driver);
		logger.info("After sorting fromActual web firstname column"+afterSorting);
		return beforeSorting.equals(afterSorting);
	}
	public boolean verifyEmailSecondTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example2Email,driver);
		Collections.sort(beforeSorting);
		logger.info("Before sorting Email column:"+beforeSorting);
		clickElement(emailHeaderExample2, driver);
		List<String> afterSorting = getAllTextContentsPlain(example2Email,driver);
		logger.info("After sorting fromActual web Email column"+afterSorting);
		return beforeSorting.equals(afterSorting);
	}
	public boolean verifyDueSecondTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example2Due,driver);
		List<Float>convertedToInt=new LinkedList<Float>();
		for (String string : beforeSorting) {
			convertedToInt.add(Float.parseFloat(string.replace("$", "")));
		}
		Collections.sort(convertedToInt);
		logger.info("Before sorting Due column:"+beforeSorting);
		clickElement(dueHeaderExample2, driver);
		List<String> afterSorting = getAllTextContentsPlain(example2Due,driver);
		List<Float>convertedToIntAfterSorting=new LinkedList<Float>();
		for (String string : afterSorting) {
			convertedToIntAfterSorting.add(Float.parseFloat(string.replace("$", "")));
		}
		logger.info("After sorting fromActual Due column"+afterSorting);
		return convertedToInt.equals(convertedToIntAfterSorting);
	}
	public boolean verifyWebSiteSecondTable() {
		List<String> beforeSorting = getAllTextContentsPlain(example2WebSite,driver);
		Collections.sort(beforeSorting);
		logger.info("Before sorting website column:"+beforeSorting);
		clickElement(websiteHeaderExample2, driver);
		List<String> afterSorting = getAllTextContentsPlain(example2WebSite,driver);
		logger.info("After sorting fromActual website column"+afterSorting);
		return beforeSorting.equals(afterSorting);
	}
	public boolean clickAndValidate200StatusCode(int statusCode) throws URISyntaxException, IOException {
		List<String> useDevToolstoGrabAPINetworks = useDevToolstoGrabAPINetworks(driver);
		clickElement(TwoHundredHyperLink, driver);
		List<String> fullUrl = useDevToolstoGrabAPINetworks.stream().filter((string)->string.contains("com/status_codes/200")).collect(Collectors.toList());
		logger.info("200 url is :"+fullUrl.get(0));
		return hitParticularAPIUsingInbuiltJava(fullUrl.get(0))==statusCode;
	}
	public boolean clickAndValidate301StatusCode(int statusCode) throws URISyntaxException, IOException {
		directlylandOnParticularUrl("https://the-internet.herokuapp.com/status_codes",driver);
		List<String> useDevToolstoGrabAPINetworks = useDevToolstoGrabAPINetworks(driver);
		clickElement(ThreeZeroOneHyperLink, driver);
		List<String> fullUrl = useDevToolstoGrabAPINetworks.stream().filter((string)->string.contains("com/status_codes/301")).collect(Collectors.toList());
		logger.info("301 url is :"+fullUrl.get(0));
		return hitParticularAPIUsingInbuiltJava(fullUrl.get(0))==statusCode;
	}
	public boolean clickAndValidate404StatusCode(int statusCode) throws URISyntaxException, IOException {
		directlylandOnParticularUrl("https://the-internet.herokuapp.com/status_codes",driver);
		List<String> useDevToolstoGrabAPINetworks = useDevToolstoGrabAPINetworks(driver);
		clickElement(FourZeroFourHyperLink, driver);
		List<String> fullUrl = useDevToolstoGrabAPINetworks.stream().filter((string)->string.contains("com/status_codes/404")).collect(Collectors.toList());
		logger.info("404 url is :"+fullUrl.get(0));
		return hitParticularAPIUsingInbuiltJava(fullUrl.get(0))==statusCode;
	}
	public boolean clickAndValidate500StatusCode(int statusCode) throws URISyntaxException, IOException {
		directlylandOnParticularUrl("https://the-internet.herokuapp.com/status_codes",driver);
		List<String> useDevToolstoGrabAPINetworks = useDevToolstoGrabAPINetworks(driver);
		clickElement(FiveZeroZeroHyperLink, driver);
		List<String> fullUrl = useDevToolstoGrabAPINetworks.stream().filter((string)->string.contains("com/status_codes/500")).collect(Collectors.toList());
		logger.info("500 url is :"+fullUrl.get(0));
		return hitParticularAPIUsingInbuiltJava(fullUrl.get(0))==statusCode;
	}
	public void clickClickHereRedirectLinkbutton() {
		clickElement(clickHereRedirectLink, driver);
	}
	public boolean verifyURLAfterRedirectClick(String text) {
		return verifyUrlContainsExpectedText(text, driver);
	}
}
