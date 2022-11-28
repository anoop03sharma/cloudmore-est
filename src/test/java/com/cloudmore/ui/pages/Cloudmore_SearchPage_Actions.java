package com.cloudmore.ui.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.common.subtyping.qual.Bottom;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cloudmore.utils.ScreenshotUtils;
public class Cloudmore_SearchPage_Actions extends Cloudmore_SearchPage{

	private static final Logger log =  LogManager.getLogger(Cloudmore_SearchPage_Actions.class);
	private static JavascriptExecutor js;
	final int TIMEOUT=120;

	public Cloudmore_SearchPage_Actions(){

		wait=new WebDriverWait(driver,Duration.ofSeconds(TIMEOUT));
		js = (JavascriptExecutor)driver;

	}

	/**
	 * This method checks if search is successfully complete by checking 
	 * the 'Search results for <searchText>' message is displayed on search Page after search is complete.
	 * @param searchString
	 * @return
	 */
	public boolean verifySearchResultsNotification(String searchString) {
		try {
		//	acceptCookies();
			log.info("Verifying Search Results Notification...");
			test.info("Verifying Search Results Notification...");

			String resultNotification=".//*[text()='Search results for ']/span [text()='searchText']";
			resultNotification=resultNotification.replace("searchText", searchString);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(resultNotification),searchString));
			String message=driver.findElement(By.xpath(resultNotification)).getText();
			log.info(message + ": is displayed on screen");
			test.info(message + ": is displayed on screen");
			test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("SearchResults"+timestamp()+".png"));
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	} 

	/**
	 * Scroll to the end of page and take several screenshots in the sequence
	 * Works for both Desktop/Mobile screen versions
	 * @return
	 */
	public boolean scrollTillEndAndtakeFullPageScreenshots() {
		
		try {
		int offset=250;

		//Scroll to the bottom
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
        long endOfPage=(Long) js.executeScript("return window.pageYOffset");  //1695
        //Scroll to the Top of the Page
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");	
		long scrollStart=0;
		long scrollEnd=scrollStart+offset;
		

		while(scrollEnd<endOfPage){		
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("SearchResultsSequence_"+timestamp()+".png"));
			Thread.sleep(2000);
			js.executeScript("window.scrollBy("+scrollStart+","+scrollEnd+")");
			Thread.sleep(2000);
			//Move by 300 pixels for next screenshot
			scrollStart=scrollEnd;
			scrollEnd=scrollEnd+offset;
		}
	     //Scroll to the Top of the Page after the test
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");	
		
		return true;
		}
		catch(Exception e) {
			test.info("An Exception has occured" +e);
			log.info("An exception has occured"+e);
			return false;

		}
				
	}
/**
 * Check number of search results returned for a string.
 * @return
 */
	public int checkNumberOfSearchResultsReturned() {
		try {
			acceptCookies();
			List<WebElement> searchResults= driver.findElements(SEARCH_RESULTS); 
			
			test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("SearchResults_"+timestamp()+".png"));
			int i;
			if(searchResults.size()>0) {
			
				log.info("\n\n Total Search Results returned : : "+searchResults.size());
				test.info("Total Search Results returned : : "+searchResults.size());

				//Take Screenshot
				Thread.sleep(2000);
				test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("SearchResultsSequence_"+timestamp()+".png"));
			
				//Take FullPage Screenshots to cover all Search Results
				scrollTillEndAndtakeFullPageScreenshots();			
				
				//Resize Window to mobile screen and take screenshot
				log.info("Resize Window to mobile screen and take screenshot");
				test.info("Resize Window to mobile screen and take screenshot");
				driver.manage().window().setSize(new Dimension(133, 5372));
				Thread.sleep(2000);
				
				//Take FullPage Screenshots to cover all Search Results
				scrollTillEndAndtakeFullPageScreenshots();
			
				//Maximize browser window
				log.info("\n Maximize browser Window after taking screenshot\n");
				driver.manage().window().maximize();
				Thread.sleep(2000);

				log.info("************* SEARCH RESULTS FOUND : "+searchResults.size());
				test.info("************* SEARCH RESULTS FOUND : "+searchResults.size());
				return searchResults.size();

			}
			else if(searchResults.size()==0 && driver.findElements(NO_RESULTS_FOUND).size()==1) {
				log.info("\n\n*************** NO SEARCH RESULTS FOUND ************\n\n");
				test.info("*************** NO SEARCH RESULTS FOUND ************");
				return 0;
			}
			return -1;	

		}

		catch(Exception e) {
			e.printStackTrace();
			log.info("\n An Exception has occured : "+e);
			return -1;
		}

	} 



	/**
	 * Performs search operation and also
	 * 1. Check notification on Screen after search is complete.
	 * 2. Check number of search results returned.
	 * 
	 * @param searchString
	 * @return
	 */
	public int getSearchResults(String searchString) {

		try {
			acceptCookies();
			// Optional check : null  and blank string - Remove if not required
			if(searchString.trim().isEmpty())
				return -1;

			test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("MENU ITEMS"+timestamp()+".png"));
			if(SEARCH_TEXTBOX.isDisplayed()) {
				log.info("\nSearch Textbox is displayed on screen \n");
				test.info("Search Textbox is displayed on screen ");


				//Clear Search text box
				SEARCH_TEXTBOX.clear();

				//Enter search text in textbox
				SEARCH_TEXTBOX.sendKeys(searchString);
				log.info("\nEnter search string : "+searchString+" in Search Textbox \n");
				test.info("Enter search string : "+searchString+" : in Search Textbox \n");
				test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("SearchTextbox_"+timestamp()+".png"));

				//Click on Search button
				SEARCH_BUTTON.click();
				log.info("\n Clicked on Search Button \n");
				test.info("Clicked on Search Button");
				test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("SearchButtonClicked_"+timestamp()+".png"));


				//Verify search is complete and results are displayed on screen
				verifySearchResultsNotification(searchString);

				//get number of Search results displayed on screen
				return checkNumberOfSearchResultsReturned();
			}


			return -1;
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("***** An exception has occured during Search operation : "+ e);
			return -1;
		}
	} 


	public void resizeWindow(WebDriver driver) {
		//Dimension dimension = new Dimension(800, 600);	
		driver.manage().window().setSize(new Dimension(800, 621));

	}

	public void acceptCookies() {
		try {
			if(driver.findElements(COOKIES_MESSAGE).size()==1) {
				test.info("Cookies Message is Displayed on Screen");
				Thread.sleep(4000);
				ACCEPT_COOKIES.click();
				test.info("Clicked on Accept Cookies");

			}
		}
		catch(Exception e) {
			e.printStackTrace();
			test.info("An Exception has occured"+e);
			log.info("********************An Exception has occured"+e);
		}
	}


}
