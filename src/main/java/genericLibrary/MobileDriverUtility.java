package genericLibrary;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class MobileDriverUtility {
	AndroidDriver driver;
	public MobileDriverUtility(AndroidDriver driver) {
		this.driver=driver;
	}
	LoggingUtils logger=new LoggingUtils();
	    WaitUtils wait=new WaitUtils();
	 
          
	/*
	 * this method handles seekBar
	 */
	public void handleSeekBar(MobileElement seekbar, int x, int y) {
		TouchAction action = new TouchAction(driver);
		action.press(ElementOption.element(seekbar)).waitAction().moveTo(PointOption.point(x, y)).release().perform();
	}
//---------------------------------------------------------------------------------------------------------------
/*
 * this method taps on particular element
 */
	public void tapOnElement(MobileElement element) {
		new TouchAction(driver).tap(ElementOption.element(element)).perform();
	    logger.log().info("performing tap action");
	}
	public void tapOnElement(MobileElement element,String s) {
		wait.WaitUntilElementVisibility(driver, element);
		new TouchAction(driver).tap(ElementOption.element(element)).perform();
	    logger.log().info("clicked on "+s);
	}

//--------------------------------------------------------------------------------------------------------------
	/*
	 * this method taps on particular coordinate
	 */
	public void tapOnCordinate(int x, int y) {
		new TouchAction(driver).tap(PointOption.point(x, y)).perform();
	}

//--------------------------------------------------------------------------------------------------------------
	public void scrollIniOS(String keyAttribute, String keyValue, String direction) {
		HashMap<String, Object> scrollObject = new HashMap<String, Object>();
		scrollObject.put("direction", direction);
		scrollObject.put(keyAttribute, keyValue);
		org.openqa.selenium.JavascriptExecutor scroll = (JavascriptExecutor) driver;
		scroll.executeScript("mobile:scroll", scrollObject);
	}

//--------------------------------------------------------------------------------------------------------------
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(IPathConstant.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		logger.log().info("applying implicit wait");
	}

	// --------------------------------------------------------------------------------------------------------------
	public void waitTillVisibility(MobileElement element) {
		WebDriverWait wait = new WebDriverWait(driver, IPathConstant.EXPLICIT_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	

	// --------------------------------------------------------------------------------------------------------------
	public void getOrientation() {
		driver.getOrientation();
	}
	// --------------------------------------------------------------------------------------------------------------

	public void setOrientationPortrait() {
		driver.rotate(ScreenOrientation.PORTRAIT);
	}
	// --------------------------------------------------------------------------------------------------------------

	public void setOrientationLandscape() {
		driver.rotate(ScreenOrientation.LANDSCAPE);
	}

	// --------------------------------------------------------------------------------------------------------------
	public void setCurrentLocation() {
		driver.setLocation(driver.location());
	}

	// --------------------------------------------------------------------------------------------------------------
	public void swipeByCordinates(int startx, int starty, int endx, int endy) {
		new TouchAction(driver).press(PointOption.point(startx, starty))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1000))).moveTo(PointOption.point(endx, endy))
				.release().perform();
	}
	// --------------------------------------------------------------------------------------------------------------

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	public void swipeScreen(Direction dir) {
		System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions
		final int ANIMATION_TIME = 200; // ms
		final int PRESS_TIME = 200; // ms
		int edgeBorder = 10; // better avoid edges
		PointOption pointOptionStart, pointOptionEnd;
		// init screen variables
		Dimension dims = driver.manage().window().getSize();
		// init start point = center of screen
		pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
		switch (dir) {
		case DOWN: // center of footer
			pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
			break;
		case UP: // center of header
			pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
			break;
		case LEFT: // center of left side
			pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
			break;
		case RIGHT: // center of right side
			pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction(driver).press(pointOptionStart)
					// a bit more reliable when we add small wait
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
					.perform();
		} catch (Exception e) {
			System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		// always allow swipe action to complete
		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
			// ignore
		}
	}

//--------------------------------------------------------------------------------------------------------------
    /**
     * Performs small swipe from the center of screen
     *
     * @param dir the direction of swipe
     * @version java-client: 7.3.0
     **/
    public void swipeScreenSmall(Direction dir) {
        System.out.println("swipeScreenSmall(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        // reduce swipe move into multiplier times comparing to swipeScreen move
        int mult = 5; // multiplier
        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) + (dims.height / 2) / mult);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) - (dims.height / 2) / mult);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point((dims.width / 2) - (dims.width / 2) / mult, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point((dims.width / 2) + (dims.width / 2) / mult, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreenSmall(): dir: '" + dir.toString() + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreenSmall(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }
    
  //-------------------------------------------------------------------------------------------------------------
    public void swipeScreenLarge() throws InterruptedException {
    	Dimension dimension = driver.manage().window().getSize();
		int x = dimension.getWidth() / 2;
		int yend = (int) (dimension.getHeight() * 0.1);
		int ystart = (int) (dimension.getHeight() * 0.9);
    	
		TouchAction t = new TouchAction(driver);
		t.press(PointOption.point(x, ystart)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(x, yend)).release().perform();
		Thread.sleep(2000);
        logger.log().info("performing scroll ");
	}
  //--------------------------------------------------------------------------------------------------------------
        
	public void swipeDownTillElement(String textAttribute) {
		TouchAction action = new TouchAction(driver);
		Dimension dimension = driver.manage().window().getSize();
		int starty = (int) (dimension.getHeight() * 0.9);
		int endy = (int) (dimension.getHeight() * 0.1);
		int x = dimension.getWidth() / 2;
		while (true) {
			try {
				driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + textAttribute + "\")"))
						.click();
				break;
			} catch (Throwable e) {
				action.press(PointOption.point(x, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(x, endy)).release().perform();
				continue;
			}
		}
	}

	// --------------------------------------------------------------------------------------------------------------
	public void hidekeyBoard() {
		HidesKeyboard hide = (HidesKeyboard) driver;
		hide.hideKeyboard();
	}

//--------------------------------------------------------------------------------------------------------------
	public void pressBack() {
		PressesKey p=(PressesKey)driver;
		p.pressKey(new KeyEvent(AndroidKey.BACK));
	}
	public void pressHome() {
		PressesKey p=(PressesKey)driver;
		p.pressKey(new KeyEvent(AndroidKey.HOME));
	}
	public void pressVolumeUP() {
		PressesKey p=(PressesKey)driver;
		p.pressKey(new KeyEvent(AndroidKey.VOLUME_UP));
	}
	public void presskeycode(AndroidKey key) {
		PressesKey p=(PressesKey)driver;
		p.pressKey(new KeyEvent(key));
	}
//--------------------------------------------------------------------------------------------------------------

	public void openNotification() {
		driver.openNotifications();
	}

	public List<AndroidElement> readNotificationsTitle() {

		List<AndroidElement> titles = driver.findElements(By.id("android:id/title"));
		return titles;
	}

	public List<AndroidElement> readNotificationsText() {

		List<AndroidElement> texts = driver.findElements(By.id("android:id/text"));
		return texts;
	}
	
//--------------------------------------------------------------------------------------------------------------

	public MobileElement readToastMsg() {
		return (MobileElement) driver.findElementByXPath("//android.widget.Toast");
	}
//--------------------------------------------------------------------------------------------------------------
    public MobileElement uiSelectorText(String textAttribute) {
    	return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textAttribute+"\")"));
	}
    
    public MobileElement uiSelectorTextContains(String textAttribute) {
    	return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\""+textAttribute+"\")"));
	}
    
    public MobileElement uiSelectorResourceId(String resourceId) {
    	return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+resourceId+"\")"));
	}
    
    public MobileElement uiSelectorContentDesc(String contentDesc) {
    	return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().descriptionContains(\""+contentDesc+"\")"));
	}
    
    public MobileElement uiSelectorClassName(String className,int index) {
    	System.out.println("method executed uiSelectorClassName");
    	return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+className+"\").index("+index+")"));
	}
    public void uiScrollTillText(String text) {
    	driver.findElement(
		MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollTextIntoView(\""+text+"\")"));
    }
    public void uiScrollTillText(String className,String text) {
    	driver.findElement(MobileBy.AndroidUIAutomator(
		"new UiScrollable(new UiSelector().className(\""+className+"\").scrollable(true)).scrollTextIntoView(\""+text+"\")"));
		
    }
    public void uiScrollHorizontal(String resourceId,String text) {
    	driver.findElement(MobileBy.AndroidUIAutomator(
    	"new UiScrollable(new UiSelector().resourceId(\""+resourceId+"\")"
    	+ ".scrollable(true)).setAsHorizontalList().scrollTextIntoView(\""+text+"\")"));
    			
    }
    
  

}
