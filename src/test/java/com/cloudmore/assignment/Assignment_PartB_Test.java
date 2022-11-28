package com.cloudmore.assignment;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cloudmore.base.TestBase;
import com.cloudmore.ui.pages.Cloudmore_HomePage;
import com.cloudmore.ui.pages.Cloudmore_HomePage_Actions;
import com.cloudmore.ui.pages.Cloudmore_SearchPage_Actions;
import com.cloudmore.utils.ScreenshotUtils;

/**
 ** @author Anoop 
 ** 1. Test to check CloudmoreLogo on HomePage.
 ** 2. Test to check specified Menu Options on HomePage.
 ** 3. Test to performs a search operation; fail test if search results is less than 3.
 ******** EXPECTED Results : 
 ******** LOGO CHECK and MENU ITEMS test should pass.
 ******** Search Test should fail for one of the strings shall return less search results than set passing criteria.
 ******* You can try with different search strings to check the results.
 **/
public class Assignment_PartB_Test extends TestBase {

	private static final Logger log =  LogManager.getLogger(Assignment_PartB_Test.class);
	

	/**
	 * Check CloudMore Logo is displayed on homepage
	 */

	
	@Test()
	public void testLogoExistence() {		
		try {
		      test.info("Clicked on Login");
		
			log.info("\n\n\n\n ************CHECK LOGO IS DISPLAYED ON HOMESCREEN ************\n");
			test.info(" CHECK LOGO IS DISPLAYED ON HOMESCREEN");

			Cloudmore_HomePage_Actions homepage=new Cloudmore_HomePage_Actions();
			Assert.assertTrue(homepage.checkCloudmoreLogo());	
		
			log.info("\n\n ************ LOGO IS DISPLAYED  ************\n");
			test.info(" LOGO IS DISPLAYED ON HOMESCREEN");



		}

		catch(Exception e) {
			e.printStackTrace();
			log.info("\n\n ****** An Exception has occured while checking LOGO on home page*******"+e);
		    test.fail("CHECK LOGO Test Failed");
		}
	}

	/**
	 * Check MenuItems are displayed.
	 **/
	@Test()
	public void checkMenuItems() {		
		try {
			log.info("\n\n\n\n ************CHECK MENU ITEMS ON HOMEPAGE ************\n");
			test.info("************CHECK MENU ITEMS ON HOMEPAGE ************");

			//Set List of MenuItems to be verified
			List<String> menuItems= new ArrayList<String>();
			menuItems.add("Solutions");
			menuItems.add("Products");
			menuItems.add("Resources");
			menuItems.add("Pricing");
			menuItems.add("Company");
			
			log.info("\n\n Following MenuItems should be displayed : \n"+menuItems);
			test.info("Following MenuItems should be displayed : "+menuItems);

			Cloudmore_HomePage_Actions homepage=new Cloudmore_HomePage_Actions();
			Assert.assertTrue(homepage.checkMenuItems(menuItems));
			log.info("\nALL Menu items are displayed on homepage");
			test.info("ALL Menu items are displayed on homepage");


		}

		catch(Exception e) {
			e.printStackTrace();
			log.info("\n\n ************** An Exception has occured************"+e);
			test.fail("An Exception has occured"+e);		}

	}

	/**
	 * Perform search Operation
	 **/
	@Test()
	public void testSearchFunction_pass() {		
		try {
			/* Number of results for test to qualify as passed is set as 3, 
			 */
			int passCriteria=3;
			String searchString="Solution"; //returns less than 3 results

			test.info("Fail Test case if Search results returned is less than : "+passCriteria);
			
			Cloudmore_HomePage_Actions homepage= new Cloudmore_HomePage_Actions();
			Assert.assertTrue(homepage.clickOnSearchButtonOnHomePage());
			
			test.info("Goto Search Page");
			Cloudmore_SearchPage_Actions searchpage = new Cloudmore_SearchPage_Actions();
			test.info("Perform search operation for : "+searchString);
			int results=searchpage.getSearchResults(searchString);
			test.info("Search Results returned is : "+results);
			Assert.assertTrue(results>passCriteria);	

		}

		catch(Exception e) {
			e.printStackTrace();
			log.info("\n\n ************** An Exception has occured************"+e);
			test.info("An Exception has occured"+e);
		}

	}
	
	@Test()
	public void testSearchFunction_fail() {		
		try {
			/* Number of results for test to qualify as passed is set as 3, 
			 */
			int passCriteria=3;
			String searchString="helluva"; //returns less than 3 results

			test.info("Fail Test case if Search results returned is less than : "+passCriteria);
			
			Cloudmore_HomePage_Actions homepage= new Cloudmore_HomePage_Actions();
			Assert.assertTrue(homepage.clickOnSearchButtonOnHomePage());
			
			test.info("Goto Search Page");
			Cloudmore_SearchPage_Actions searchpage = new Cloudmore_SearchPage_Actions();
			test.info("Perform search operation for : "+searchString);
			int results=searchpage.getSearchResults(searchString);
			test.info("Search Results returned is : "+results);
			Assert.assertTrue(results>passCriteria);			

		}

		catch(Exception e) {
			e.printStackTrace();
			log.info("\n\n ************** An Exception has occured************"+e);
			test.info("An Exception has occured"+e);		}

	}




}
