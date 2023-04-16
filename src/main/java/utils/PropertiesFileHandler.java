package utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileHandler {
	//load properties file from folder
	public Properties loadProperties(String recourcename)
	{
		Properties prop = null;
		try
		{
			prop = new Properties();
			MyLogger.info("get as FileInputStream property file -> " + recourcename);
			InputStream fis = getClass().getClassLoader().getResourceAsStream(recourcename + ".properties");
			MyLogger.info("load property file -> " + recourcename);
			prop.load(fis);
		}
		catch (IOException e) {
			MyLogger.error("error getting property file -> " + recourcename);
		}
		return prop;
	}
}
