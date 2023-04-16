package driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.options.BaseOptions;
import utils.AppiumServerHandler;
import utils.MyLogger;
import utils.PropertiesFileHandler;

public class AndroidManager extends DeviceFactory{
	private Properties deviceconfig;
	private Properties generalconfig;
	private PropertiesFileHandler prophandler;
	private URL url = null;
	
	public AndroidManager(Properties deviceconfig)
	{
		this.deviceconfig = deviceconfig;
		prophandler = new PropertiesFileHandler();
		generalconfig = prophandler.loadProperties("config");
	}
	
	@Override
	public AppiumDriver Driversetup() {
		try {
			url = new URL(generalconfig.getProperty("androidDriverUrl"));
		} catch (MalformedURLException e) {
			MyLogger.error("Error can't set URL");
		}				
		return new AndroidDriver(url,getOptions());
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseOptions getOptions() {	
		UiAutomator2Options options = null;
		AppiumServerHandler appiumserverhandler = new AppiumServerHandler();
		int port = Integer.parseInt(deviceconfig.getProperty("SYSTEMPORT"));
		
		//if u are using real mobile just change Remove AVD i will handle it later
		try {
			appiumserverhandler.FreePort(port);
			options = new UiAutomator2Options()
					.setDeviceName(deviceconfig.getProperty("DEVICENAME"))
					.setAutomationName(deviceconfig.getProperty("AUTOMATIONNAME"))
					.setUdid(deviceconfig.getProperty("UDID"))
					.setSystemPort(port)
					.noReset()
					.setAppWaitActivity("*");
			if(Boolean.parseBoolean(System.getProperty("EMULATOR")))
			{	
				options.setAvd("DEVICENAME");
				options.setAvdLaunchTimeout(Duration.ofSeconds(1000));
			}
			
		} catch (Exception e) {
			MyLogger.error("Failed to add options for UiAutomator2Options");
		}
		
		MyLogger.info("Add options for Android");
        return options;
	}
}
