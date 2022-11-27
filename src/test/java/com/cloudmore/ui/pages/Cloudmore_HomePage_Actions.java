package com.cloudmore.ui.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cloudmore.utils.ScreenshotUtils;

/**
 * This Class contains all the operations/actions which could be performed in the Cloudmore website HomePage
 *
 */
//Set Timeout value for wait


public class Cloudmore_HomePage_Actions extends  Cloudmore_HomePage {
	
		private static final Logger log =  LogManager.getLogger(Cloudmore_HomePage_Actions.class);
		static WebDriverWait wait;
		final int TIMEOUT=120;
	
		public Cloudmore_HomePage_Actions(){
		
			wait=new WebDriverWait(driver,Duration.ofSeconds(TIMEOUT));

		
		}
		
		public void acceptCookies() {
			try {
				if(driver.findElements(COOKIES_MESSAGE).size()==1) {
					test.info("Cookies Message is Displayed on Screen");
					Thread.sleep(2000);
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

	
	public boolean checkCloudmoreLogo() {
		log.info("Checking Cloudmore Logo on HomePage...");
		test.info("Checking Cloudmore Logo on HomePage...");

		try {
			acceptCookies();
			//Wait for element to be displayed
			wait.until(ExpectedConditions.visibilityOf(CLOUDMORE_LOGO));

			Assert.assertTrue(CLOUDMORE_LOGO.isDisplayed());
			log.info("Cloudmore Logo is displayed on HomePage...");
			test.info("Cloudmore Logo is displayed on HomePage...");

			test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("CloudMoreLogoDisplayed_"+timeStamp+".png"));


			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("LOGO is not visible on HomePage till TIMEOUT");
			log.info("LOGO is not visible on HomePage till TIMEOUT");
			test.info("LOGO is not visible on HomePage till TIMEOUT");

			return false;
		}
	}
	/**
	 * checkMenuItems on the Cloudmore website Homepage.
	 * @param menuItems
	 * @return
	 */
	public boolean checkMenuItems(List<String> menuItems) {

		try {
			//Accept cookies if message is displayed
			acceptCookies();
			//Wait till one of the menu items is displayed on screen
			wait.until(ExpectedConditions.visibilityOf(SOLUTIONS_MENU_HOMEPAGE));

			if(SOLUTIONS_MENU_HOMEPAGE.isDisplayed()) {
				for(int i=0;i<menuItems.size();i++) {

					if(menuItems.get(i).equals("Solutions")) {
						Assert.assertTrue(SOLUTIONS_MENU_HOMEPAGE.isDisplayed());
						log.info("\n*****SOLUTIONS_MENU is displayed****");
						test.info("\n*****SOLUTIONS_MENU is displayed****");
					    test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("SOLUTIONS_MENU_"+timeStamp+".png"));


					}

					if(menuItems.get(i).equals("Product")) {
						Assert.assertTrue(PRODUCT_MENU_HOMEPAGE.isDisplayed());	
						log.info("\n******PRODUCT_MENU is displayed*******");
					    test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("PRODUCT_MENU_"+timeStamp+".png"));

					}

					if(menuItems.get(i).equals("Resources")) {
						Assert.assertTrue(RESOURCES_MENU_HOMEPAGE.isDisplayed());
						log.info("\n******RESOURCES_MENU is displayed********");
					    test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("RESOURCES_MENU_"+timeStamp+".png"));

					}


					if(menuItems.get(i).equals("Pricing")) {
						Assert.assertTrue(PRICING_MENU_HOMEPAGE.isDisplayed());
						log.info("\n*******PRICING_MENU is displayed***");
					    test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("PRICING_MENU_"+timeStamp+".png"));

					}

					if(menuItems.get(i).equals("Company")) {
						Assert.assertTrue(COMPANY_MENU_HOMEPAGE.isDisplayed());
						log.info("\n********COMPANY_MENU is displayed****");
					    test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("COMPANY_MENU_"+timeStamp+".png"));


					}

				}
				return true;
			}

			return false;

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("\n********MENU items verification failed, due to error :******** "+e);
			test.info("MENU items verification failed, due to error"+e);

			return false;
		}
	}

	/**
	 * Click on searchButtonOnHomePage to open Search page
	 * @return
	 */
	public boolean clickOnSearchButtonOnHomePage() {
		try {
			
		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));
			Assert.assertTrue(SEARCH_BUTTON.isDisplayed());
			SEARCH_BUTTON.click();
			log.info("\n\nClicked on Search Button on HomePage.....");
			wait.until(ExpectedConditions.visibilityOf(SEARCH_TEXTBOX));
			Assert.assertTrue(SEARCH_TEXTBOX.isDisplayed());
			log.info("\n\nSearch Textbox is displayed on clicking Search Button.....");
			test.info("Search Textbox is displayed on clicking Search Button.....");
			return true;

		}
		catch(Exception e) {
			log.info("\n******Error has occured while clicking on Search Button in Top Menu : "+e);
			test.info("Error has occured while clicking on Search Button in Top Menu : "+e);

			e.printStackTrace();
			return false;
	
		}
	
	}

	public boolean goToHomePage() {
		try {
			log.info("\n******GOTO HomePage*******");
			test.info("GOTO HomePage");

			wait.until(ExpectedConditions.elementToBeClickable(CLOUDMORE_LOGO));
			Assert.assertTrue(CLOUDMORE_LOGO.isDisplayed());
			CLOUDMORE_LOGO.click();
			log.info("Clicked on CloudMore Logo to go to homePage.....");
			test.info("Clicked on CloudMore Logo to go to homePage.....");

			return true;
			
			}
		catch(Exception e) {
			e.printStackTrace();
			log.info("\n*****An Exception has occured "+e);
			test.info("\n*****An Exception has occured "+e);


			return false;
		}
		
	}
	
	


}
