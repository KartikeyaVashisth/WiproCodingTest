package com.wipro.utils;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Locators {
	
	AndroidDriver<AndroidElement> driver;
	
	public Locators(AndroidDriver<AndroidElement> driver) {
		this.driver=driver;	
	}
	
	
@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/skip_sign_in_button")
public static AndroidElement skipSignInBtn;

@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/action_bar_burger_icon")
public static AndroidElement hamBurgerMenuBtn;

@AndroidFindBy(xpath="//android.widget.TextView[@text='Country & Language']")
public static AndroidElement countrySelection;
	
@AndroidFindBy(id="landing-countryButton")
public static AndroidElement countryRegion;	

@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Australia Amazon.com.au']")
public static AndroidElement countryAustralia;	

@AndroidFindBy(xpath="//android.widget.Button[@text='Done']")
public static AndroidElement doneBtn;	

@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/action_bar_home_logo")
public static AndroidElement amazonHomeLogo;	

@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
public static AndroidElement searchbar;	

@AndroidFindBy(xpath="//android.widget.Button[@text='See All Buying Options']")
public static AndroidElement seeAllBuyingOptionBtn;	

@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Add to Cart\")")
public static AndroidElement addToCart;	

@AndroidFindBy(xpath="//android.view.View[@index='17']")
public static AndroidElement itemPrice;	

@AndroidFindBy(xpath="//android.view.View[@index='17']")
public static AndroidElement itemTextDescription;	

@AndroidFindBy(accessibility="Basket")
public static AndroidElement itemBasket;	

@AndroidFindBy(xpath="//android.view.View[@index='0']")
public static AndroidElement AddedCartItem;	

}
