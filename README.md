Pre-requisites to run this project
 Framework is built on JDK 1.8
 System must have TestNg plugin installed
# Add the "Selenium WebDriver " dependencies as "External JARs" in the project
( download from - https://www.selenium.dev/downloads/ { Selenium for Java (version - 4.6.0)) }
# Environment variables (create Path as a UserVariable) must be set for MAVEN_HOME and Java
# All the dependencies in pom.xml must be downloaded.


#There is a separate configuration for  both Log4j2 and Extent Reports.
-------------------------------------------------------------------------------------------
For adding logs via : Log4j 2 use : 			
 - log.info("Add message here")
 - A rolling log4j2 File is configured. Limit is set to 10 MB >> After that a new Log file is created.

For Extent Report use : 
 - test.addScreenCaptureFromPath(ScreenshotUtils.capturescreen("AddNameOfPngFileHere"+timeStamp+".png")); //For Screenshots
 - test.info("Add message here")
 - Screenshot Location - \screenshot
 - ExtentReport Location - \ExtentReports



Important
==============
Run As > TestNG suite.

- Chrome Browser was used as preferred browser for tests while building the solution.
- Also tested on Edge browser


