package com.cloudmore.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cloudmore.base.TestBase;

/**
 *  This class is a repository for all the WebElements present in Cloudmore Website Home Page. 
 */

public class Cloudmore_HomePage extends TestBase {

	private static String url="https://web.cloudmore.com";
	WebDriverWait wait;
	
	Cloudmore_HomePage(){
		PageFactory.initElements(driver,this);
		wait=super.wait;
	}
	
	@FindBy(css="[id='hs-eu-confirmation-button']")
	protected WebElement ACCEPT_COOKIES;
	
	@FindBy(css="[id='hs-eu-decline-button']")
	protected WebElement DECLINE_COOKIES;
	
/*	@FindBy(css="[id='hs-en-cookie-confirmation-buttons-area']")
	protected WebElement COOKIES_MESSAGE;*/
	
	protected By COOKIES_MESSAGE=By.cssSelector("[id='hs-en-cookie-confirmation-buttons-area']");

	@FindAll
	(
			{
				@FindBy(css=".navbar-brand img"),
				@FindBy(css="img[src$='logo-cloudmore.png']")
			}
			)
	protected WebElement CLOUDMORE_LOGO;


	protected By DASHBOARD_HEADER_MENUS=By.cssSelector("[id='mega-menu-primary_navigation'] .mega-menu-link");

	@FindBy(xpath="//*[@class='mega-menu-link'][text()='Solutions']")
	protected WebElement SOLUTIONS_MENU_HOMEPAGE;

	@FindBy(css="//*[[text()='Solutions']/span[@class='mega-indicator']")
	protected WebElement SOLUTIONS_MENU_DROPDOWN;

	@FindBy(xpath="//*[@class='mega-menu-link'][text()='Product']")
	protected WebElement PRODUCT_MENU_HOMEPAGE;

	@FindBy(xpath="//*[@class='mega-menu-link'][text()='Resources']")
	protected WebElement RESOURCES_MENU_HOMEPAGE;

	@FindBy(xpath="//*[@class='mega-menu-link'][text()='Pricing']")
	protected WebElement PRICING_MENU_HOMEPAGE;

	@FindBy(xpath="//*[@class='mega-menu-link'][text()='Company']")
	protected WebElement COMPANY_MENU_HOMEPAGE;
	
	@FindBy(css=".btn-search img")
	protected WebElement SEARCH_BUTTON;
	
	@FindBy(css=".input-search")
	protected WebElement SEARCH_TEXTBOX;


	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		Cloudmore_HomePage.url = url;
	}

}