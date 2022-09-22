package genericLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
* This will contain all wait related utility methods.
*
* @author kumar sanu
*/
public class WaitUtils {
 public final int explicitWaitDefault =IPathConstant.EXPLICIT_WAIT_TIME;
 public final int implicitWaitDefault=IPathConstant.IMPLICIT_WAIT_TIME;
 
 
 public void implicitWait(final WebDriver driver) {
	 driver.manage().timeouts().implicitlyWait(implicitWaitDefault, TimeUnit.SECONDS);
 }
 
 public void WaitUntilElementVisibility(AndroidDriver driver,MobileElement element) {
		WebDriverWait wait=new WebDriverWait(driver,explicitWaitDefault);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
 

 
}