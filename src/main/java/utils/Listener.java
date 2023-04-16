package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.Base;

public class Listener extends Base implements ITestListener {
	
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		MyLogger.error("Test Failed");
		MyLogger.error("Take Screen shot");
		getAppActions().takeScreenShot(arg0.getMethod().getMethodName());
		getAppActions().stopVideoRecording(arg0.getMethod().getMethodName());
		MyLogger.endTestCase(arg0.getName());
	}
	
	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		MyLogger.info("Test Skipped");
	}
	
	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		MyLogger.startTestCase(arg0.getName());
		getAppActions().startVideoRecording();
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		MyLogger.endTestCase(arg0.getName());

	}
}
