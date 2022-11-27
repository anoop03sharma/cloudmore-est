package com.cloudmore.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cloudmore.base.TestBase;

public class ScreenshotUtils extends TestBase  {
	
	
	public static String capturescreen(String name)
	{
		TakesScreenshot takescreenshot = (TakesScreenshot) getDriver();
		File src = takescreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File("screenshots/"+name);
        try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}        
        return destination.getAbsolutePath();
	
	}

}
