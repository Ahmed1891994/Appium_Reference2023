package driver;

import java.util.Properties;

import base.Base;
import io.appium.java_client.AppiumDriver;
import utils.MyLogger;

public class TargetType extends Base{
	private String target;
	private Properties deviceconfig;
	
	public TargetType(String target,Properties deviceconfig) {
		this.deviceconfig = deviceconfig;
		this.target = target;
	}

	public AppiumDriver createDeviceDriverInstance() {
		AppiumDriver appiumdriver = null;
        DeviceFactoryManager devicefactorymanager = new DeviceFactoryManager(deviceconfig);
        MyLogger.info("Choose Target depending on properties file -> " + target);
        switch (target) {
            case "LOCAL":
            	MyLogger.info("Create local driver");
            	appiumdriver = devicefactorymanager.getDeviceConfig().getDriver();
                break;
            case "REMOTE":
            	MyLogger.info("Create remote driver");
            	//RemoteManager remotemanager = new RemoteManager();
                //appiumdriver = remotemanager.createRemoteInstance(devicefactorymanager.get(deviceconfig).getCapabilities());
                break;
            default:     	
        }
        return appiumdriver;
    }
}
