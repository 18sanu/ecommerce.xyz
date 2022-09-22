package genericLibrary;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImplementation implements IRetryAnalyzer{
	
	int retryLimit=IPathConstant.RETRY_LIMIT;
	int counter=0;
		public boolean retry(ITestResult result) {
			if(counter<retryLimit) {
				counter++;
				return true;
			}
			return false;
		}


}
