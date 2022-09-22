package genericLibrary;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LoggingUtils {
	
	static Logger logger=LogManager.getLogger();
	public Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
		}
	 
	public void info(String message) {
		logger.info(message);
	}
	public void info(int message) {
		logger.info(message);
	}
	public void info(ArrayList<String> message) {
		logger.info(message);
	}
	public void error(String message) {
		logger.error(message);
	}
	public void error(Exception message) {
		logger.error(message);
	}
	

}
