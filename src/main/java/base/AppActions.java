package base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Attachment;
import utils.MyLogger;

public class AppActions {
	private AppiumDriver driver;
	WebDriverWait wait;

	public AppActions(AppiumDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Terminate App
	public void terminateApp(String app_package) {
		MyLogger.info("terminate app using app_package : " + app_package);
		((AndroidDriver) driver).terminateApp(app_package);
	}

	// Install App
	public void installApp(String apk_name) {
		MyLogger.info("install app with name : " + apk_name);
		((AndroidDriver) driver).installApp(apk_name);
	}

	// Is app Installed
	public boolean isAppInstalled(String app_package) {
		MyLogger.info("check if app is installed with app package : " + app_package);
		return ((AndroidDriver) driver).isAppInstalled(app_package);
	}

	// run app in background
	public void runInBackgroundForTime(int millis) {
		MyLogger.info("run app in background for : " + millis + "millis");
		((AndroidDriver) driver).runAppInBackground(Duration.ofMillis(millis));
	}

	// Activate app
	public void activateApp(String app_package) {
		MyLogger.info("Activate app with app package : " + app_package);
		((AndroidDriver) driver).activateApp(app_package);
	}

	// Check App Status
	public String checkAppStatus(String app_package) {
		MyLogger.info("Check App Status with app package" + app_package);
		return ((AndroidDriver) driver).queryAppState(app_package).toString();
	}

	// Lock Mobile
	public void lockMobile() {
		MyLogger.info("Lock Mobile");
		((AndroidDriver) driver).lockDevice();
	}

	// Unlock Mobile
	public void unlockMobile() {
		MyLogger.info("Unlock Mobile");
		((AndroidDriver) driver).unlockDevice();
	}

	// PressKeys_Android
	public void PressKeys_Android(AndroidKey keys) {
		MyLogger.info("Enter keys");
		((AndroidDriver) driver).pressKey(new KeyEvent().withKey(keys));
	}

	// close App
	public void closeApp(String apppackage) {
		MyLogger.info("terminate App");
		((AndroidDriver) driver).terminateApp(apppackage);
	}

	// quit Session
	public void quitSession() {
		MyLogger.info("quit Session");
		((AndroidDriver) driver).quit();
	}

	// refresh App
	public void refreshApp(String app_package) {
		MyLogger.info("refresh App");
		terminateApp(app_package);
		activateApp(app_package);
	}

	// ************************************Screenshots***************************************
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] takeScreenShot(String TestMethodName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		MyLogger.info("Save screen shot");
		File source = ts.getScreenshotAs(OutputType.FILE);
		String time = java.time.LocalTime.now().toString().replace(":", "-").substring(0, 5);
		String date = java.time.LocalDate.now().toString();
		MyLogger.info("Save screen shot name with time -> " + time + " and date -> " + date);
		String destination = System.getProperty("user.dir") + File.separator + "ScreenShots" + File.separator + date
				+ "_" + time + File.separator + TestMethodName + "_" + ThreadLocalRandom.current().nextInt() + ".png";

		try {
			MyLogger.info("copy screen shot to destination place : " + destination);
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ts.getScreenshotAs(OutputType.BYTES);
	}

	public void startVideoRecording() {
		((CanRecordScreen) driver).startRecordingScreen();
		MyLogger.info("Start vedio recording");
	}

	public void stopVideoRecording(String TestMethodName) {
		String media = ((CanRecordScreen) driver).stopRecordingScreen();
		MyLogger.info("Stop video recording");
		String time = java.time.LocalTime.now().toString().replace(":", "-").substring(0, 5);
		String date = java.time.LocalDate.now().toString();
		String destination = System.getProperty("user.dir") + File.separator + "videos" + File.separator + date + "_"
				+ time;

		File videoDir = new File(destination);

		synchronized (videoDir) {
			if (!videoDir.exists()) {
				videoDir.mkdirs();
			}
		}

		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(
					videoDir + File.separator + TestMethodName + "_" + ThreadLocalRandom.current().nextInt() + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
			MyLogger.info("path for video is : " + destination + File.separator + TestMethodName + "_"
					+ ThreadLocalRandom.current().nextInt() + ".mp4");
		} catch (Exception e) {
			MyLogger.error("error during video capture" + e.toString());
		}

	}
}
