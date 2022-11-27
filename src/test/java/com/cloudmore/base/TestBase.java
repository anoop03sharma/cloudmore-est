package com.cloudmore.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.cloudmore.utils.FileReaderUtils;
import com.cloudmore.utils.ListenerUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase extends ListenerUtils {

	//	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	protected static WebDriver  driver;
	protected static ChromeOptions chromeOptions;
	public WebDriverWait wait;

	protected static  Logger log =  LogManager.getLogger(TestBase.class);

	@BeforeMethod
	public void Setup() throws Exception{
		driver=initializeDriverWithBrowser();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(FileReaderUtils.getDataFromApplicationProp("cloudmoreURL"));
		driver.manage().window().maximize();

	}

	public static WebDriver initializeDriverWithBrowser() throws Exception
	{
		chromeOptions = new ChromeOptions();		
		String browser =FileReaderUtils.getDataFromApplicationProp("browser");
		System.out.println("Browser initialized as :"+browser);

		if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver=new ChromeDriver( chromeOptions);
			log.info("Chrome Browser is launched...");			
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			log.info("Browser is launched...");			
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.iedriver().setup();
			driver=new EdgeDriver();
			log.info("Browser is launched...");			

		}
		else
		{
			System.out.println(browser+" Didn't matched any browser specified in properties file");
			log.info("Browser is launched...");	

		}
		return driver;
	}

    public static WebDriver getDriver(){
        
        return driver;
    }


    @AfterMethod
    public void closeBrowser() {
    
		driver.close();
		log.info("\n\n************Close Browser Window ********\n");
		test.info("Close Browser Window");

	
    }
    
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		log.info("************Test Complete ********");
		test.info("Test Complete");


	}

}
