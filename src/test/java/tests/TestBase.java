package tests;

import java.util.Properties;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import Environments.Environment;
import base.Base;
import base.AppActions;
import base.ElementActions;
import driver.TargetType;
import utils.AppiumServerHandler;
import utils.JsonFileHandler;
import utils.MyLogger;
import utils.PropertiesFileHandler;

public class TestBase extends Base {
	private Environment cfg;
	private static PropertiesFileHandler propfile;
	private JsonFileHandler jsonfilehandler;
	private static Properties prop;

	@BeforeSuite(alwaysRun = true)
	public void ReadingFiles() {
		MyLogger.info("Reading config Property file");
		propfile = new PropertiesFileHandler();
		prop = propfile.loadProperties("config");
		
		MyLogger.info("Reading Data Json files");
		jsonfilehandler = new JsonFileHandler();
		logindata = jsonfilehandler.loadJson("LoginData");
		errordata = jsonfilehandler.loadJson("ErrorData");
		productdata = jsonfilehandler.loadJson("ProductData");
		productdetailsdata = jsonfilehandler.loadJson("ProductDetailsData");
	}

	@Parameters({"platformName","deviceName"})
	@BeforeTest
	public void InitializeEnvironment(String platformname,String devicename) {
		// Update Environment parameters
		MyLogger.info("Update Environment parameters using owner library");
		cfg = ConfigFactory.create(Environment.class);

		//Update deviceconfig file
		MyLogger.info("Update device config parameters" + platformname+"_"+devicename);
		setDeviceConfig(propfile.loadProperties(platformname+"_"+devicename));
		
		setAppiumServerHandler(new AppiumServerHandler());
		getAppiumServerHandler().startserver(Integer.parseInt(prop.getProperty("androidDriverPort")));

		// save the environment variable into threadlocal
		MyLogger.info("save the environment variable into threadlocal");
		setEnvironment(cfg);
		// make new class from targettype class and get environment and pass the
		// environment to it
		// initialize target class to choose to work locally or remotely
		MyLogger.info("initialize target class to choose to work locally or remotely");
		TargetType targettype = new TargetType(getEnvironment().gettarget(), getDeviceConfig());

		// Set the driver
		MyLogger.info("Set the driver");
		setDriver(targettype.createDeviceDriverInstance());

		// initialize the driver actions and pass the driver appiumdriver to the class
		MyLogger.info("initialize the ElementActions and BrowserActions and pass the driver appiumdriver to the class");
		setElementActions(new ElementActions(getDriver()));
		setBrowserActions(new AppActions(getDriver()));
	}

	@BeforeMethod
	public void InitializeEnvironment()
	{
		getAppActions().activateApp(prop.getProperty("androidAppPackage"));
	}
	
	@AfterMethod(alwaysRun = true)
	public void TearDown() {
		MyLogger.info("Close App");
		getAppActions().terminateApp(prop.getProperty("androidAppPackage"));
	}
}
