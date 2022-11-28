Pre-requisites to run this project
-----------------------------------
 - Framework is built on JDK 1.8
 - System must have Maven installed { use for reference - https://phoenixnap.com/kb/install-maven-windows }
 - System must have TestNg plugin installed.
 - System must have Selenium Webdriver installed { use for reference -https://www.javatpoint.com/selenium-webdriver-installation }
 - Import solution as 'Existing Maven Project' in Eclipse.
 - Add the "Selenium WebDriver " dependencies as "External JARs" in the project
( download from - https://www.selenium.dev/downloads/ { Selenium for Java (version - 4.6.0)) }
- Setup MAVEN_HOME and JAVA_HOME System environment variables.
- All the dependencies in pom.xml must be downloaded.


There is a separate configuration for  both Log4j2 and Extent Reports.
-------------------------------------------------------------------------------------------
For adding logs via : Log4j 2 use : 			
 - log.info("Add message here")
 - A rolling log4j2 File is configured. Limit is set to 10 MB >> After that a new Log file is created.
 - Log file is generated with name - app-info.log

For Extent Report use : 
------------------------
 - test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("AddNameOfPngFileHere"+timeStamp+".png")); //For Screenshots
 - test.info("Add message here")
 - Screenshot Location - \screenshot
 - ExtentReport Location - \ExtentReports



Important
----------
Run As > TestNG suite.

- Chrome Browser was used as preferred browser for tests while building the solution.
- Also tested on Edge browser
- Solution is created on Windows + Eclipse IDE 


