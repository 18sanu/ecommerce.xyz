package genericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * this method will fetch data from property file
 */
public class FileUtility {
	public String getDataFromProperty(String key) throws IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.PROPERTY_FILE_PATH);
		Properties property = new Properties();
		property.load(fis);
		return property.getProperty(key);
	}

}
