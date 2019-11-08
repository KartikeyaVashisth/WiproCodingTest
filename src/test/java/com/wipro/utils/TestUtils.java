package com.wipro.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TestUtils extends BaseClass {

	
	public 	ArrayList<String> price_description;


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

	public void pressEnterBtn()
	{
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}

	public void customWait(int secs)  {

		try {
			TimeUnit.SECONDS.sleep(secs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void getDescription_Price_Of_TV()
	{
		
		
		try {
			String description=Locators.itemTextDescription.getText();
			String price=Locators.itemPrice.getText();
			String[] only_price=price.split(" ");
			price_description=new ArrayList<String>();
			price_description.add(only_price[21]);
			price_description.add(description);
			click(Locators.itemTextDescription);
			click(Locators.seeAllBuyingOptionBtn);
			click(Locators.addToCart);
			customWait(3);
			click(Locators.itemBasket);
			customWait(3);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
