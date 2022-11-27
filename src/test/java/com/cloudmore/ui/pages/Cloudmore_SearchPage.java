package com.cloudmore.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cloudmore.base.TestBase;

public class Cloudmore_SearchPage extends TestBase{

	@FindBy(css=".input-search")
	protected WebElement SEARCH_TEXTBOX;

	@FindBy(css=".search-input .search-button")
	protected WebElement SEARCH_BUTTON;
	
	protected By SEARCH_RESULTS=By.cssSelector(".search-results article[class='search-result']");
	protected By NO_RESULTS_FOUND=By.cssSelector(".text-area h2+h2");
	
	@FindBy(css="[id='hs-eu-confirmation-button']")
	protected WebElement ACCEPT_COOKIES;
	
	@FindBy(css="[id='hs-eu-decline-button']")
	protected WebElement DECLINE_COOKIES;
	
	protected By COOKIES_MESSAGE=By.cssSelector("[id='hs-en-cookie-confirmation-buttons-area']");
		
	@FindBy(css=".footer-logo")
	protected WebElement FOOTER_LOGO;

	


	
	Cloudmore_SearchPage(){
		driver=super.driver;
		PageFactory.initElements(driver,this);
		
	}
 
}
