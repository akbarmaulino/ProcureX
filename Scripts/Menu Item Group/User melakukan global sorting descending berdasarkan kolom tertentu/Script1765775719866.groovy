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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import helper.VerifyTableSorting as VerifyTableSorting

CustomKeywords.'auth.Login.loginAs'('admin')

WebUI.click(findTestObject('Sidebar/sidebar_Master Data'))

WebUI.click(findTestObject('Sidebar/subMenu_Master Item'))

CustomKeywords.'helper.VerifyTableSorting.byColumnIndex'(1, 'DESC')

CustomKeywords.'helper.VerifyTableSorting.byColumnIndex'(2, 'DESC')

CustomKeywords.'helper.VerifyTableSorting.byColumnIndex'(3, 'DESC')

CustomKeywords.'helper.VerifyTableSorting.byColumnIndex'(4, 'DESC')

CustomKeywords.'helper.VerifyTableSorting.byColumnIndex'(5, 'DESC')

CustomKeywords.'helper.VerifyTableSorting.byColumnIndex'(6, 'DESC')

CustomKeywords.'helper.VerifyTableSorting.byColumnIndex'(7, 'DESC')

