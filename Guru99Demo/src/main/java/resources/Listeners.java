package resources;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
	Logger log = LogManager.getLogger(Listeners.class);

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
//		try {
//
//			base.getScreenshot(result.getName());
//
//		} catch (IOException e) {
//
//			log.error(e.toString());
//		}

	}

	public void onTestFailure(ITestResult result) {

		try {

			base.getScreenshot(result.getName());

		} catch (IOException e) {

			log.error(e.toString());
		}

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

}
