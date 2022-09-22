package genericLibrary;

import java.io.File;

public interface IPathConstant {
	int IMPLICIT_WAIT_TIME=20;
	int EXPLICIT_WAIT_TIME=20;
	int FLUENT_WAIT_TIME=20;
	int POLLING_PERIOD=250;
	int THREAD_SLEEP=10000;
	int RETRY_LIMIT=5;
	
	String PROPERTY_FILE_PATH="./src/test/resources/propertyFile.properties";
	String EXCEL_FILE_PATH="."+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"ExcelData.xlsx";
	
	String DEVICE_NAME="deviceName";
	String UDID="udid";
	String PLATFORM_VERSION="platformVersion";
	String PLATFORM_NAME="platformName";
	String URL_KEY="url";
	
	
	String SHEET_NAME="AppDetails";
	
	
}
