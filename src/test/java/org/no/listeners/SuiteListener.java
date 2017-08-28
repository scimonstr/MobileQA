package org.no.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {
    Logger logger = LoggerFactory.getLogger(getClass());

    public void onStart(ISuite suite) {
        logger.info("Test execution in \"{}\" started", suite.getName());
    }

    public void onFinish(ISuite suite) {
        logger.info("Test execution in \"{}\" finished", suite.getName());
    }
}
