package helper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By

class VerifyTableSorting {

	@Keyword
	def byColumnIndex(int columnIndex, String direction = 'ASC') {

		TestObject header = new TestObject("header_col_${columnIndex}")
		header.addProperty(
				'xpath',
				ConditionType.EQUALS,
				"(//th[contains(@class,'cursor-pointer')])[${columnIndex}]"
				)


		WebUI.click(header)
		WebUI.delay(1)

		if (direction.equalsIgnoreCase('DESC')) {
			WebUI.click(header)
			WebUI.delay(1)
		}

		def driver = DriverFactory.getWebDriver()

		def cells = driver.findElements(
				By.xpath("//tbody/tr/td[${columnIndex}]")
				)

		List<String> actual = cells.collect {
			it.getText().replaceAll('\\s+', ' ').trim()
		}

		assert actual.size() > 1 : "Not enough rows to verify sorting"


		WebUI.comment("""
		[SORT DEBUG]
		Column    : ${columnIndex}
		Direction : ${direction}
		Row count : ${actual.size()}
		First row : ${actual[0]}
		Sample    : ${actual.take(Math.min(3, actual.size()))}
		""")

		List<String> expected = new ArrayList<>(actual)

		if (direction.equalsIgnoreCase('ASC')) {
			Collections.sort(expected)
		} else if (direction.equalsIgnoreCase('DESC')) {
			Collections.sort(expected, Collections.reverseOrder())
		} else {
			throw new IllegalArgumentException("Direction must be ASC or DESC")
		}

		// ===== ASSERT =====
		assert actual == expected : """
			SORTING FAILED
			Column    : ${columnIndex}
			Direction : ${direction}
			Actual    : ${actual}
			Expected  : ${expected}
			"""
	}
}
