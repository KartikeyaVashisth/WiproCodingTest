package com.wipro.utils;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass {

	protected static ExtentReports report;
	protected static ExtentTest test;
	protected static AndroidDriver<AndroidElement> driver;
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private DesiredCapabilities cap;
	private int port = 4723;
	protected static URL appiumServerURL;
	public static final String LOG = "src/test/resources/Log/log4j.properties";
	public static Logger APP_LOGS = null;

	@BeforeTest
	public void setUp() {

		try {
			File appPath=new File("src/test/resources/Apk/Amazon_shopping.apk");

			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "XP8800");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
			cap.setCapability(MobileCapabilityType.UDID, "c1fbacfb");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.APP, appPath.getAbsolutePath());
			cap.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
			cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");

			driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite	
	public void startServer() {

		System.out.println("<------------------------Started Appium Server---------------------------------->");
		//Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", false);
		//cap.setCapability("relaxed-security", true);

		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
		builder.withAppiumJS(new File(System.getProperty("user.home")+"\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
		builder.withLogFile(new File (System.getProperty("user.home")+"\\AppiumServerLogs.txt"));
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(port);
		builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		appiumServerURL = service.getUrl();
	}

	public void click(AndroidElement e) {
		e.click();
	}
	
	public void enterText(AndroidElement e,String searchtext) {
		e.sendKeys(searchtext);
	}
	
	public void scrollToElementAndClick(String searchtext) {
		
		String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
		String textElement = ".scrollIntoView(new UiSelector().text(\""+ searchtext +"\"))";
		driver.findElementByAndroidUIAutomator(scrollable+textElement).click();	
	}
	
	public void swipe() {
		
	Dimension size=driver.manage().window().getSize();
	int x=size.getWidth()/2;
	int starty=(int)(size.getHeight()*0.60);
	int endy=(int)(size.getHeight()*0.30);
	
	TouchAction t=new TouchAction(driver);
	t.press(PointOption.point(x,starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).
	moveTo(PointOption.point(x,endy));
	t.release().perform();	
	}
	
	public void tap(int startx,int starty) {
		
		TouchAction t=new TouchAction(driver);
		t.tap(PointOption.point(startx,starty));
		t.perform();	
	}

	public void quit(){
		if(driver!=null) {
			driver.quit();
		}
	}
}
