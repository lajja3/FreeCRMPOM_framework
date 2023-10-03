/*
STEP 9 - b) after adding extent report dependancy u have to create an extent report class
in src/main/java -> com.qa.ExtentReportListener -> ExtentReporterNG.java
 ***Purpose of This extent report listener class is to listen each and every test case execution activity and to generate the report at the end 
 * @autor : Naveen Khunteta
 * no need to remember anything here 
 * this is the std template provided by extent report thus copy and paste the code
 */
package com.qa.ExtentReportListener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//this ExtentReporterNG class is implementing IReporter interface and it is from testNG
//thus u need listener from testNG to implement extent report
public class ExtentReporterNG implements IReporter { //this is the listener class
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {  //outputDirectory is test-output folder
		extent = new ExtentReports(outputDirectory + File.separator
				+ "FreeCRM_Extent.html", true); //Extent.html is the name of the extent report here u can give any name, here i have changed to FreeCRM_Extent.html

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults(); //fetching results

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS); //fetching passed test cases
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL); //failed test cases
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);//skipped test cases
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}