package auth

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



public class Login {

	@Keyword
	def loginAs(String role) {

		if (!GlobalVariable.credentials.containsKey(role)) {
			throw new IllegalArgumentException("Role '${role}' tidak ditemukan di GlobalVariable.credentials")
		}

		def user = GlobalVariable.credentials[role]

		WebUI.setText(findTestObject('Object Repository/LoginPage/input_Email'), user.username)
		WebUI.setEncryptedText(findTestObject('Object Repository/LoginPage/input_Password'), user.password)
		WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

		WebUI.verifyElementPresent(findTestObject('Object Repository/LoginPage/toast_SuccessLogin'), 10)
	}
}
