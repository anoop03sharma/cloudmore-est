package com.cloudmore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtils {
	
	 public static String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH_mm_ss").format(new Date());

	    public static ExtentReports extent;
	    public static ExtentSparkReporter spark;
	    
	    //Initialize ExtentReports 
	    public static ExtentReports initExtent() {
	    	 extent = new ExtentReports();
	    	 //Save Extent report here
	         spark = new ExtentSparkReporter("ExtentReports/extentreport_" + timeStamp + ".html");
	         extent.attachReporter(spark);
	         extent.setSystemInfo("OS", System.getProperty("os.name"));
	         extent.setSystemInfo("JVM", System.getProperty("java.runtime.version"));	         
	    
	         return extent;
	    	
	    }

}
