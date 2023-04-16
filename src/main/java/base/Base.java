package base;

import java.util.Properties;

import org.json.JSONObject;
import Environments.Environment;
import io.appium.java_client.AppiumDriver;
import utils.AppiumServerHandler;
import utils.MyLogger;

public class Base{
	protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal<Environment> Environment = new ThreadLocal<Environment>();
	protected static ThreadLocal<ElementActions> elementactions = new ThreadLocal<ElementActions>();
	protected static ThreadLocal<AppActions> appactions = new ThreadLocal<AppActions>();
	protected static ThreadLocal<Properties> deviceconfig = new ThreadLocal<Properties>();
	protected static ThreadLocal<AppiumServerHandler> appiumserverhandler = new ThreadLocal<AppiumServerHandler>();
	public static JSONObject logindata;
	public static JSONObject errordata;
	public static JSONObject productdata;
	public static JSONObject productdetailsdata;
	
	protected void setDriver(AppiumDriver driver) {
		MyLogger.debug("save the driver to ThreadLocal variable");
		Base.driver.set(driver);
	}

	protected AppiumDriver getDriver() {
		MyLogger.debug("get the driver from ThreadLocal variable");
		return driver.get();
	}
	
	protected void setEnvironment(Environment env) {
		MyLogger.debug("save the Environment to ThreadLocal variable");
		Base.Environment.set(env);
	}

	protected Environment getEnvironment() {
		MyLogger.debug("get the Environment from ThreadLocal variable");
		return Base.Environment.get();
	}
	
	protected void setElementActions(ElementActions elementactions) {
		MyLogger.debug("save the ElementActions to ThreadLocal variable");
		Base.elementactions.set(elementactions);
	}

	protected ElementActions getElementActions() {
		MyLogger.debug("get the ElementActions from ThreadLocal variable");
		return Base.elementactions.get();
	}
	
	protected void setBrowserActions(AppActions appactions) {
		MyLogger.debug("save the app actions to ThreadLocal variable");
		Base.appactions.set(appactions);
	}

	protected AppActions getAppActions() {
		MyLogger.debug("get the app actions from ThreadLocal variable");
		return Base.appactions.get();
	}
	
	protected void setDeviceConfig(Properties deviceconfig) {
		MyLogger.debug("save the DeviceConfig to ThreadLocal variable");
		Base.deviceconfig.set(deviceconfig);
	}

	public Properties getDeviceConfig() {
		MyLogger.debug("get the DeviceConfig from ThreadLocal variable");
		return Base.deviceconfig.get();
	}
	
	protected void setAppiumServerHandler(AppiumServerHandler appiumserverhandler) {
		MyLogger.debug("save the AppiumServerHandler to ThreadLocal variable");
		Base.appiumserverhandler.set(appiumserverhandler);
	}

	public AppiumServerHandler getAppiumServerHandler() {
		MyLogger.debug("get the AppiumServerHandler from ThreadLocal variable");
		return Base.appiumserverhandler.get();
	}
}
