package com.cloudmore.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtils {
	private static final Logger log =  LogManager.getLogger(CommonUtils.class);

	
	public static String currentDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy-HH_mm_ss");
	    Calendar cal = Calendar.getInstance();
	    String date = dateFormat.format(cal.getTime());
	    return date;
	}
	
	// To be used to capture Screenshots not as part of Extent Reports but as a separate feature
	public static void captureScreenShot(WebDriver driver) {
		log.info("Capturing Screenshot.....");
		 //Take the screenshot
		String screenShotPath="./Screenshots/"; 
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //Copy the file to a location and use try catch block to handle exception
        try {
           FileUtils.copyFile(screenshot, new File(screenShotPath+currentDateTime()+".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	

}
