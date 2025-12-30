import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CustomKeywords.'auth.Login.loginAs'('admin')

WebUI.click(findTestObject('Sidebar/sidebar_Budget plan'))

WebUI.click(findTestObject('Sidebar/subMenu_Master Item'))

WebUI.click(findTestObject('MasterItemPage/tab/tab_item Category'))

WebUI.click(findTestObject('MasterItemPage/Item Category/icon_edit'))

WebUI.verifyElementPresent(findTestObject('MasterItemPage/Item Category/PopUp_Edit Detail'), 10)

TestObject toggleBtn = findTestObject('Object Repository/MasterItemPage/Item Category/toggle_Status')

String state = WebUI.getAttribute(toggleBtn, 'aria-checked')

if (state == 'true') {
    WebUI.comment('Toggle Active')

    WebUI.click(toggleBtn)
} else {
    WebUI.comment('Toggle sudah Inactive')
}

WebUI.click(findTestObject('MasterItemPage/Item Group/button_Save'))

CustomKeywords.'helper.VerifyValue.verifyToastContains'('Cannot deactivate Item Category because it is referenced in another table')


