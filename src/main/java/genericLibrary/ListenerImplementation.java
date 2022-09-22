package genericLibrary;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ListenerImplementation  implements ITestListener {
	public static ExtentReports extent;
	 public static ExtentTest test ;

	public void onTestStart(ITestResult result) {
		 test = extent.createTest(result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("passed");
		test.log(Status.PASS, "test passed");
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "test fail")
		.log(Status.FAIL,result.getThrowable());
		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getName()+"got skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		 extent = new ExtentReports();
       ExtentSparkReporter spark = new ExtentSparkReporter(".//report//report.html");
       extent.attachReporter(spark);
		
	}

	public void onFinish(ITestContext context) {
	extent.flush();
		
	}
	

}
