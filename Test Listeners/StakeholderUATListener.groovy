import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.annotation.*
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.testcase.TestCaseFactory


class StakeholderUATListener {

    static List results = []
    static Map currentData = [:]
	
	@BeforeTestCase
	def beforeCases() {
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GlobalVariable.base_url)
	}
	
    @BeforeTestCase
    def beforeTestCase(TestCaseContext context) {

        def title = context.getTestCaseId()
                           .tokenize('/')
                           .last()

        currentData = [
            testCaseTitle : title,
            expected      : getExpectedFromDescription(context),
            actual        : "",
            status        : "",
            screenshot    : ""
        ]
    }

    @AfterTestCase
    def afterTestCase(TestCaseContext context) {

        currentData.status = context.getTestCaseStatus()
        try {
            currentData.screenshot = WebUI.takeScreenshot()
        } catch (Exception e) {
            currentData.screenshot = ""
        }
        if (currentData.status == 'PASSED') {
            currentData.actual = 'System behaves as expected'
        } else {
            currentData.actual = 'System does not behave as expected'
        }

        results.add(currentData.clone())
    }

    @AfterTestSuite
    def afterTestSuite(def context) {

        def html = new StringBuilder()
        html.append("""
        <html>
        <head>
          <title>UAT Automation Report</title>
          <style>
            body { font-family: Arial, sans-serif; }
            h2 { color: #2c3e50; }
            table { border-collapse: collapse; width: 100%; }
            th, td {
              border: 1px solid #ccc;
              padding: 8px;
              vertical-align: top;
            }
            th {
              background: #2c3e50;
              color: white;
            }
            .PASSED { color: green; font-weight: bold; }
            .FAILED { color: red; font-weight: bold; }
            img {
              max-width: 300px;
              border: 1px solid #999;
            }
          </style>
        </head>
        <body>

        <h2>UAT Automation Test Report</h2>

        <table>
          <tr>
            <th>Test Case</th>
            <th>Expected Result</th>
            <th>Actual Result</th>
            <th>Status</th>
            <th>Evidence</th>
          </tr>
        """)

        results.each {
            html.append("""
            <tr>
              <td>${it.testCaseTitle}</td>
              <td>${it.expected}</td>
              <td>${it.actual}</td>
              <td class="${it.status}">${it.status}</td>
              <td>
                ${it.screenshot ? 
                  "<a href='${it.screenshot}' target='_blank'><img src='${it.screenshot}' /></a>" 
                  : "N/A"}
              </td>
            </tr>
            """)
        }

        html.append("""
        </table>
        </body>
        </html>
        """)

        new File("Reports/UAT_Stakeholder_Report.html").text = html.toString()
    }
	
    static String getExpectedFromDescription(TestCaseContext context) {

        def tc = TestCaseFactory.findTestCase(context.getTestCaseId())

        if (tc != null && tc.getDescription()) {
            return tc.getDescription().trim()
        }

        return "Expected behavior as defined in test case"
    }
}

