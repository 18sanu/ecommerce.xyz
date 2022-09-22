package genericLibrary;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import genericLibrary.ExcelUtility;
import genericLibrary.FileUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {
	
	AppiumDriverLocalService service;
	public AndroidDriver driver;
	LoggingUtils logger=new LoggingUtils();
	
	
	@BeforeSuite
	public void startServer() {
	//service=AppiumDriverLocalService.buildDefaultService();
   // service.start();
    logger.log().info("server started");
   // log.info("server started");
	}
	@AfterSuite
	public void stopServer() {
	 // service.stop();
	logger.log().info("server stopped");
	}
	
	
	
	 
	@BeforeClass
	public void launchApplication() throws IOException {
		
		FileUtility f = new FileUtility();
		WaitUtils wait = new WaitUtils();
		String deviceName=f.getDataFromProperty(IPathConstant.DEVICE_NAME);
		String platformName=f.getDataFromProperty(IPathConstant.PLATFORM_NAME);
		String udid=f.getDataFromProperty(IPathConstant.UDID);
		String platformVersion=f.getDataFromProperty(IPathConstant.PLATFORM_VERSION);
		String url1=f.getDataFromProperty(IPathConstant.URL_KEY);
		
		ExcelUtility excel = new ExcelUtility();
		String appPackage=excel.getDataFromExcel(IPathConstant.SHEET_NAME, 1, 1);
		String appActivity=excel.getDataFromExcel(IPathConstant.SHEET_NAME, 1, 2);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		cap.setCapability(MobileCapabilityType.UDID, udid);
		cap.setCapability(MobileCapabilityType.NO_RESET, false);
		cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        cap.setCapability("appPackage",appPackage);
		cap.setCapability("appActivity",appActivity);
		URL url = new URL(url1);
		//for opening the app
		 driver = new AndroidDriver(url, cap);
		 wait.implicitWait(driver);
		 logger.log().info("app started successfully");
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
		logger.log().info("app closed");
	}

}
