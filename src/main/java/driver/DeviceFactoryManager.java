package driver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import utils.MyLogger;


public class DeviceFactoryManager {
	private Properties deviceconfig;
	private DeviceFactory Android;
	//private DeviceFactory IOS =  new IosManager();
	private Map<String, DeviceFactory> map = new HashMap<>();
	
	public DeviceFactoryManager(Properties deviceconfig)
	{
		this.deviceconfig = deviceconfig;
		Android = new AndroidManager(deviceconfig);
		map.put("Android",Android);
	}
	
	public DeviceFactory getDeviceConfig()
	{
		String platformname = deviceconfig.getProperty("PLATFORMNAME");
		MyLogger.info("get the Device Manager from the parameter passed -> " + platformname);
		return map.get(platformname);

	}
}
