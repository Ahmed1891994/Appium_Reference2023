package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;
import utils.MyLogger;

public abstract class DeviceFactory {
	protected AppiumDriver driver;
	protected abstract AppiumDriver Driversetup();
	
	public AppiumDriver getDriver()
	{
		MyLogger.info("Check if driver null");
		if(driver == null)
		{
			MyLogger.info("driver is null then setup driver");
			driver = Driversetup();
		}	
		return driver;
	}

	@SuppressWarnings("rawtypes")
	protected abstract BaseOptions getOptions();
}
