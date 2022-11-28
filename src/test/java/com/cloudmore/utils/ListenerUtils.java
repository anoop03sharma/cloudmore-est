package com.cloudmore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.IReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenerUtils implements ITestListener {
    public static ExtentReports report = ReportUtils.initExtent();
    public static ExtentTest test;
    public static String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH_mm_ss").format(new Date());
    public static String screenshot;
   
    public void onTestStart(ITestResult result) {
       
        String classname = result.getTestClass().getName();
         test = report.createTest(classname)
                 .createNode(result.getMethod().getMethodName())
                 .assignAuthor(System.getProperty("user.name"));
    }

    // Log passed tests in extent report
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"Test Case: "+result.getMethod().getMethodName()+ " is passed.");
    }
   
    // Log failed tests in extent report

    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        test.log(Status.FAIL,"Test Case: "+result.getMethod().getMethodName()+ " is failed.");
        
        try {
            screenshot = ScreenshotUtils.capturescreen("Test_Failed_"+timeStamp+".png");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        test.info("Take Screenshot");
        test.addScreenCaptureFromPath(screenshot);
    }
    
    // Log skipped tests in extent report
   
    public void onTestSkipped(ITestResult result) {
        test.skip(result.getThrowable());
        test.log(Status.SKIP,"Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
        try {
            screenshot = ScreenshotUtils.capturescreen("Test_Skipped_"+timeStamp+".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(screenshot);
    }


    //flush Output to Extent report
    
    public void onFinish(ITestContext context) {
        report.flush();
    }
    
    

}
