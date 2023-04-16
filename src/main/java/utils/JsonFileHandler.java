package utils;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonFileHandler {
	//load Json file from folder
	public JSONObject loadJson(String recourcename)
	{
		JSONObject logindataobject = null;
		InputStream inputstreamdata = null;
		try
		{
			inputstreamdata = getClass().getClassLoader().getResourceAsStream(recourcename + ".json");
			MyLogger.info("get as FileInputStream json file -> " + recourcename);
			JSONTokener tokener = new JSONTokener(inputstreamdata);
			logindataobject = new JSONObject(tokener);
			MyLogger.info("load json file in jsonobject -> " + recourcename);
			if(inputstreamdata!=null)
				inputstreamdata.close();
		}
		catch (Exception e) {
			MyLogger.error("error getting json file -> " + recourcename);
		}
		return logindataobject;
	}
}
