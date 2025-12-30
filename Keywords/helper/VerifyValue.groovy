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



class VerifyValue {

	@Keyword
	def verifyValueExistsInColumn(int columnIndex, String expectedValue) {

		def driver = DriverFactory.getWebDriver()

		def cells = driver.findElements(
				By.xpath("//tbody/tr/td[${columnIndex}]")
				)

		assert cells.size() > 0 : "No data found in table column ${columnIndex}"

		boolean found = false

		cells.eachWithIndex { cell, index ->
			String actual = cell.getText().replaceAll('\\s+', ' ').trim()

			if (actual == expectedValue) {
				WebUI.comment("Value found at row ${index + 1}, column ${columnIndex}: ${actual}")
				found = true
				return 
			}
		}

		assert found : "Expected value '${expectedValue}' NOT FOUND in column ${columnIndex}"
	}

	@Keyword
	def verifyToastContains(String expectedMessage, int timeout = 5) {

		def driver = DriverFactory.getWebDriver()


		long endTime = System.currentTimeMillis() + (timeout * 1000)
		List<String> toastMessages = []

		while (System.currentTimeMillis() < endTime) {
			def toasts = driver.findElements(
					By.xpath("//div[@data-title]")
					)

			toastMessages = toasts.collect {
				it.getText().replaceAll('\\s+', ' ').trim()
			}.findAll { it }

			if (!toastMessages.isEmpty()) {
				break
			}
			Thread.sleep(300)
		}

		assert !toastMessages.isEmpty() : "No toast appeared within ${timeout} seconds"


		WebUI.comment("""
			[TOAST DEBUG]
			Total toast : ${toastMessages.size()}
			Messages    : ${toastMessages}
		""")


		boolean found = toastMessages.any {
			it.contains(expectedMessage)
		}

		assert found : """
		EXPECTED TOAST NOT FOUND
		Expected : ${expectedMessage}
		Actual   : ${toastMessages}
		"""
	}

	@Keyword
	def verifyMenuIsActiveWithIndicator(String menuName) {
	
		def driver = DriverFactory.getWebDriver()
	
		def activeMenu = driver.findElements(
			By.xpath("""
			//button[normalize-space()='${menuName}'
			and contains(@class,'text-red-600')
			and .//div[contains(@class,'bg-red-600')]]
			""")
		)
	
		assert activeMenu.size() == 1 : """
		Menu '${menuName}' is NOT active
		Expected:
		- text-red-600
		- active indicator bar
		"""
	
		WebUI.comment("Menu '${menuName}' is ACTIVE with indicator")
	}
}





