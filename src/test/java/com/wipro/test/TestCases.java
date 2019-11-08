package com.wipro.test;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.wipro.utils.Locators;
import com.wipro.utils.TestUtils;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TestCases extends TestUtils {
	
	/**
	 * This Method is used to initialize the report before test execution starts.
	 * 
	 * 
	 */
	
	@BeforeSuite
	public void initialiseReport() {
		
		report=new ExtentReports("src/test/resources/report/Report.html",true);
		
	}

/**
 * This Method is used to initialize the PageFactory and locators present in locator class 
 * Here i have used Logger class to initialize log4J before the test
 * 
 */

	@BeforeTest
	public void beforeTest() {
	
		Locators loc=new Locators(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver),loc);
		APP_LOGS=Logger.getLogger(this.getClass().getName());
		PropertyConfigurator.configure(LOG);
		APP_LOGS.info("Initialised Locators and Log4J successfully");
	}

/**
 * This Method is responsible for printing the method name of current running Test cases	
 * 
 */
	@BeforeMethod
	public void beforeMethod(Method method) {

		test=report.startTest(this.getClass().getSimpleName() +"::"+method.getName());
		APP_LOGS.info("Running Test case is"+method.getName());

	}
	
/**
 * This method is responsible for taking screenshot whenever there is a test cases failure and
 * appending the result to the report 
 */
	@AfterMethod
	public void AfterMethod(ITestResult result,Method method) throws IOException {

		if(result.getStatus()==ITestResult.FAILURE){

			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("src/test/resources/screenshots"));

		}
		report.endTest(test);
		report.flush();			
	}
	
/*
 *This method is responsible for changing the country name to Australia before Actual test starts 
 * 	
 * 
 */
/*
	@BeforeTest
	public void change_Country_To_Australiya() throws InterruptedException {
		
		try {
			
			TimeUnit.SECONDS.sleep(3);
			String val="sas";
			
			List<String> lst=Arrays.asList("window windows | grep 'mCurrentFocus'");
			Map<String,Object> map=ImmutableMap.of("command","dumpsys","args",lst);
			String val=(String)driver.executeScript("mobile:shell", map);
			System.out.println(val);
			
			driver.runAppInBackground(Duration.ofSeconds(5));
			
			
			
			
		
			click(Locators.skipSignInBtn);
			click(Locators.hamBurgerMenuBtn);
			scrollToElementAndClick("Settings");
			click(Locators.countrySelection);
			click(Locators.countryRegion);
			click(Locators.countryAustralia);
			click(Locators.doneBtn);
			
			APP_LOGS.info("Country Changed to Australia Successfully");
	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Exception found in change_Country_To_Australia");
		}
	}
	*/
	
/*
 * This Method is used to search 65 inch tv on amazon search page and randomly select a tv and add it to card and validate	
 */
	
	@Test(priority=1)
	public void searchItemAndAddToCartAndValidate() throws InterruptedException {
		
			SoftAssert sa=new SoftAssert();
			enterText(Locators.searchbar, "65 inch TV");
			pressEnterBtn();
			customWait(3);
			swipe();
			customWait(3);
		    getDescription_Price_Of_TV();
			
			if(Locators.AddedCartItem.getText().equals(price_description.get(1))) {
				sa.assertTrue(true);
				test.log(LogStatus.PASS, "Test Case is passed");
				
			}else {
				sa.fail();
				test.log(LogStatus.FAIL, "Test Case is Failed");	
			}
						
	}

}
