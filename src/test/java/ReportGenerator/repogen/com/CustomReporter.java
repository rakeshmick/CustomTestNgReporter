package ReportGenerator.repogen.com;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class CustomReporter implements IReporter,ITestListener {
	private PrintWriter mOut;




	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		new File(outputDirectory).mkdirs();
		/*try {
			mOut = new PrintWriter(new BufferedWriter(new FileWriter(new File(
					outputDirectory, "custom-report.html"))));
		} catch (IOException e) {
			System.out.println("Error in creating writer: " + e);
		}*/
		startHtmlmain();

		print(" Suites run: " + suites.size());

		for (ISuite suite : suites) {

			startHtml();
			print("Suite: " + suite.getName());
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (String testName : suiteResults.keySet()) {
				print(" <br> <ul><li>  Test: " + testName);
				ISuiteResult suiteResult = suiteResults.get(testName);
				ITestContext testContext = suiteResult.getTestContext();
				print("    </li> <li>    Failed: " + testContext.getFailedTests().size());
				IResultMap failedResult = testContext.getFailedTests();
				Set<ITestResult> testsFailed = failedResult.getAllResults();
				for (ITestResult testResult : testsFailed) {
					print("            " + testResult.getName());
					print("                " + testResult.getThrowable());
				}
				IResultMap passResult = testContext.getPassedTests();
				Set<ITestResult> testsPassed = passResult.getAllResults();
				print(" </li> <li>         Passed: " + testsPassed.size());
				for (ITestResult testResult : testsPassed) {
					print(", Test Method:    "
							+ testResult.getName()
							+ ",    Took: "
							+ (testResult.getEndMillis() - testResult
									.getStartMillis()) + "ms");
				}
				IResultMap skippedResult = testContext.getSkippedTests();
				Set<ITestResult> testsSkipped = skippedResult.getAllResults();
				print("</li> <li>          Skipped: " + testsSkipped.size());
				for (ITestResult testResult : testsSkipped) {
					print(" </ul>           " + testResult.getName());
				}


			}
			endHtml();

		}
		endHtmlmain();
		mOut.flush();
		mOut.close();
	}

	private void print(String text) {
		System.out.println(text);

		mOut.println(text + " ");

	}

	private void startHtmlmain() {
		try {
			mOut = new PrintWriter(new BufferedWriter(new FileWriter(new File(
					//outputDirectory,
					"custom-report.html"))));
		} catch (IOException e) {
			System.out.println("Error in creating writer: " + e);
		}

		mOut.println("");
		mOut.println("<table>");
	}
	private void startHtml() {
		mOut.println("<head><style>table, td, th {border: 1px solid black;}table {border-collapse: collapse;width: 100%;}th {height: 50px;}</style></head>");
		mOut.println("<tr><td>");
		mOut.println("");
		mOut.println("");
		mOut.println("<div><h1>TestNG Html Report Example</h1></div>");		
		mOut.println("<div><p>");
		mOut.println("");

	}



	private void endHtml() {
		mOut.println("</p></div>");
		mOut.println("</td></tr>");

	}

	private void endHtmlmain() {
		mOut.println("</table>");
		mOut.println("</p></div>");
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub



	}


	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}


	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}


	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}


	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub




	}


	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub


	}
}