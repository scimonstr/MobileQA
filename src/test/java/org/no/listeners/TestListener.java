package org.no.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void onTestStart(ITestResult iTestResult) {
        logger.info("Test \"{}\" started", iTestResult.getName());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Test \"{}\" passed", iTestResult.getName());
    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.info("Test \"{}\" failed", iTestResult.getName());
        logger.info("Failure reason: {}", iTestResult.getThrowable());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        //no action
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //no action
    }

    public void onStart(ITestContext iTestContext) {
        logger.info("Test \"{}\" started", iTestContext.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        //no action
    }
}
