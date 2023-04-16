package base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Attachment;
import utils.MyLogger;

public class ElementActions {
	private AppiumDriver driver;
	WebDriverWait wait;

	public ElementActions(AppiumDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Keyboard simulation
	public void keyboardPress(Keys key) {
		Actions action = new Actions(driver);
		MyLogger.info("Enter Key : " + key.toString());
		action.sendKeys(key).perform();
	}

	// Get title from page
	public String TitleGet() {
		String result = driver.getTitle();
		MyLogger.info("Get Title String : " + result);
		return result;
	}

	// **********************************Mobile_Gestures******************************
	// long click on element
	public void longClickOn(By element) {
		MyLogger.info("Wait Element to be Visible and Clickable");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		MyLogger.info("Long Click on Element");
		driver.executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) driver.findElement(element)).getId(), "duration", 1000));
	}

	// click on element
	public void clickOn(By element) {
		MyLogger.info("Wait Element to be Visible and Clickable");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		MyLogger.info("Click on button or link Element");
		driver.executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) driver.findElement(element)).getId()));
	}

	// drag and drop an element
	public void dragAndDrop(By element, int offset_x, int offset_y, @Optional float speed_in_seconds) {
		MyLogger.info("Wait Element to be Visible and Clickable");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));

		// Get the location of the element
		Point elementLocation = getLocation(element);
		int end_x = elementLocation.getX() + offset_x;
		int end_y = elementLocation.getY() + offset_y;

		MyLogger.info("Drag and drop element to  on button or link Element");
		driver.executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) driver.findElement(element)).getId()), "endX", end_x,
				"endY", end_y, "speed", (int) (speed_in_seconds * 1000));
	}

	// zoomOut
	public void zoomOut(By element, float percent, @Optional float speed_in_seconds) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Apply Zoom out");
		((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) driver.findElement(element)).getId(), "percent",
						percent, "speed", (int) (speed_in_seconds * 1000)));
	}

	// zoomin
	public void zoomIn(By element, float percent, float speed_in_seconds) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Apply Zoom In");
		((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) driver.findElement(element)).getId(), "percent",
						checkPercentage(percent), "speed", (int) (speed_in_seconds * 1000)));
	}

	// swipe
	public void swipeToDirection(By element, String direction, float percent, @Optional float speed_in_seconds) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Apply swipe");

		direction = direction == null ? "down" : direction;

		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) driver.findElement(element)).getId(), "percent",
						checkPercentage(percent), "direction", direction, "speed", (int) (speed_in_seconds * 1000)));
	}

	// scroll
	public void scrollToDirection(By element, String direction, float percent, @Optional float speed_in_seconds) {
		// MyLogger.info("Wait Element to be Visible");
		// wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Apply scroll");

		direction = direction == null ? "down" : direction;

		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) driver.findElement(element)).getId(), "percent",
						checkPercentage(percent), "direction", direction, "speed", (int) (speed_in_seconds * 1000)));
	}

	public void swipeElementAndroidByText(String elementtxt) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + elementtxt + "\").instance(0))"));
	}

	// check number is percentage number is from 0 to 1
	public float checkPercentage(float percent) {
		return percent < 0 ? 0 : (percent > 1 ? 1 : percent);
	}

	// get location of an element
	public Point getLocation(By element) {
		WebElement wl = driver.findElement(element);
		MyLogger.info("Get location of an Element");
		Point elementLocation = wl.getLocation();
		MyLogger.info("location of Element is x=" + elementLocation.getX() + "y=" + elementLocation.getY());
		return elementLocation;
	}

	// hover on
	public void hoverOn(By element) {
		MyLogger.info("Wait Hoverer Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions action = new Actions(driver);
		MyLogger.info("Hover on Element");
		WebElement webelement = driver.findElement(element);
		action.moveToElement(webelement).perform();
		action.moveByOffset(0, 0).perform();
	}

	// select checkbox
	public void SelectCheckbox(By element) {
		MyLogger.info("Check if element is not selected");
		if (!driver.findElement(element).isSelected()) {
			MyLogger.info("element is not selected --> Select Element");
			driver.findElement(element).click();
		}
	}

	// deselect checkbox
	public void DeselectCheckbox(By element) {
		MyLogger.info("Check if element is selected");
		if (driver.findElement(element).isSelected()) {
			MyLogger.info("element is selected --> Unselect Element");
			driver.findElement(element).click();
		}

	}

	// select checkbox
	public void ActionClick(By element) {
		MyLogger.info("Wait Element to be Visible and Clickable");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions act = new Actions(driver);
		MyLogger.info("Click on button or link Element using Actions");
		act.moveToElement(driver.findElement(element)).click().perform();
	}

	public void ScrollToElement(By element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		MyLogger.info("Scroll To Element");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
	}

	// ****************************************DropBox************************************************
	// Select from dropBox
	public void selectByVisibleText(By element, String text) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Select Element in dropdown by visible text : " + text);
		Select dropbox = new Select(driver.findElement(element));
		dropbox.selectByVisibleText(text);
	}

	public void selectByValue(By element, String value) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Select Element in dropdown by Value : " + value);
		Select dropbox = new Select(driver.findElement(element));
		dropbox.selectByValue(value);
	}

	public void selectByIndex(By element, int index) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Select Element in dropdown by Index : " + index);
		Select dropbox = new Select(driver.findElement(element));
		dropbox.selectByIndex(index);
	}

	public String getFirstSelectiontxt(By element) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("getFirstSelection");
		Select dropbox = new Select(driver.findElement(element));
		return dropbox.getFirstSelectedOption().getText();
	}

	// ***************************************TextFields**********************************************
	// put text in field after clearing it
	public void textSet(By element, String text) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Clear text in Field");
		driver.findElement(element).clear();
		MyLogger.info("Put text in Field");
		driver.findElement(element).sendKeys(text);
	}

	// Get text from element
	public String textGet(By element) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		String result = driver.findElement(element).getText();
		MyLogger.info("Text Returned from element is : " + result);
		return result;
	}

	// **************************************WindowClosure***************************************
	public void closeCurrentWindow() {
		MyLogger.info("close Current Window");
		driver.close();
	}

	public void closeAllWindows() {
		MyLogger.info("close All Windows");
		driver.quit();
	}

	// ******************************************Cookies*****************************************
	public void deleteAllCookies() {
		MyLogger.info("delete All Cookies");
		driver.manage().deleteAllCookies();
	}

	// ***************************************CheckElements**************************************
	public boolean isDisplayed(By element) {
		boolean flag;
		MyLogger.info("Check If Element Is displayed");
		if (driver.findElement(element).isDisplayed()) {
			flag = true;
			MyLogger.info("Element Is displayed");
		} else {
			flag = false;
			MyLogger.info("Element Is Not displayed");
		}
		return flag;
	}

	public boolean isNotExist(By element) {
		boolean flag;
		MyLogger.info("Check If Element Is NotExist");
		if (driver.findElements(element).isEmpty()) {
			flag = true;
			MyLogger.info("Element Is NotExist");
		} else {
			flag = false;
			MyLogger.info("Element Is Exist");
		}
		return flag;
	}

	public boolean checkElementType(By element, String type) {
		boolean flag = false;
		String elementtype = driver.findElement(element).getAttribute("type");
		MyLogger.info("Check If Element type " + elementtype + " Is equal to given type " + type);
		if (elementtype.equals(type)) {
			MyLogger.info("Element types are equal");
			flag = true;
		} else {
			MyLogger.info("Element types aren't equal");
			flag = false;
		}
		return flag;
	}

	// ************************************Element
	// Attributes***************************************
	public String getCSSValue(By element, String parameter) {
		MyLogger.info("Wait Element to be Visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		String result = driver.findElement(element).getCssValue(parameter);
		MyLogger.info("Get Element CSS value for parameter " + parameter + " -> " + result);
		return result;
	}

	// *********************************Wait****************************************
	public void Wait_Implicit(int timer) {
		MyLogger.info("Wait implicitly for " + timer + " seconds ");
		new WebDriverWait(driver, Duration.ofSeconds(timer));
		MyLogger.info("Wait Done");
	}

	public void Wait_Explicit_Until_Visibility(By element) {
		MyLogger.info("Wait Explicitly Until element is visible");
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		MyLogger.info("Wait Done");
	}

	public void Wait_Explicit_Until_NonVisibility(By element) {
		MyLogger.info("Wait Explicitly Until element is invisible");
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(element)));
		MyLogger.info("Wait Done");
	}

	
}
