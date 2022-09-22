package com.ecommerce.xyz.testscript;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import genericLibrary.BaseClass;
import genericLibrary.MobileDriverUtility;
import genericLibrary.WaitUtils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import pomRepository.JdPom;

public class JustDial extends BaseClass {
	@Test
	public void book() throws InterruptedException {
		WaitUtils wait = new WaitUtils();
		MobileDriverUtility mobile = new MobileDriverUtility(driver);
		JdPom j = new JdPom(driver);
		
		mobile.tapOnElement(j.getAgreebtn(),"agree button");
		mobile.tapOnElement(j.getSkipbtn(),"skip button");
		mobile.tapOnElement(j.getCancelBtn(),"cancel button");
		mobile.swipeScreenLarge();
        mobile.uiScrollTillText("Hotel & Stay");		
		mobile.uiScrollHorizontal("com.justdial.search:id/recycler_view", "Top Hotels");
		mobile.tapOnElement(j.getTopHotels());
		mobile.tapOnElement(j.getLeelaPalace());
		mobile.tapOnElement(j.getBookRoom());
        mobile.uiScrollTillText("android.widget.NumberPicker","3");
        mobile.tapOnElement(j.getOkButton(),"okbutton");
		

	}


}
