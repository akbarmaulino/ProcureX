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

CustomKeywords.'auth.Login.loginAs'('admin')

WebUI.click(findTestObject('Sidebar/sidebar_Budget plan'))

WebUI.click(findTestObject('Object Repository/Sidebar/sidebar_Master AHS'))

WebUI.click(findTestObject('Object Repository/Master AHS/button_Add Master AHS'))

WebUI.setText(findTestObject('Object Repository/Master AHS/input_Description AHS Information'), 'Keramik/HT (Konvensional)')

WebUI.click(findTestObject('Object Repository/Master AHS/input_AHS Category'))

WebUI.setText(findTestObject('Object Repository/Master AHS/searchColumn'), 'Struktur & Arsitek Bangunan')

WebUI.sendKeys(findTestObject('Object Repository/Master AHS/searchColumn'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Object Repository/Master AHS/input_Select a sub category'))

WebUI.setText(findTestObject('Object Repository/Master AHS/searchColumn'), 'Pekerjaan Bekisting')

WebUI.sendKeys(findTestObject('Object Repository/Master AHS/searchColumn'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Object Repository/Master AHS/button_UOM'))

WebUI.setText(findTestObject('Object Repository/Master AHS/searchColumn'), 'M2 - Meter Persegi')

WebUI.sendKeys(findTestObject('Object Repository/Master AHS/searchColumn'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Object Repository/Master AHS/button_Create New'))

WebUI.click(findTestObject('Object Repository/Master AHS/button_Add More'))

WebUI.click(findTestObject('Master AHS/Main Material/AHS_Item'))

String ahs_item = 'Item'

TestObject AHS_Item = new TestObject()

AHS_Item.addProperty('xpath', ConditionType.EQUALS, "//div[@role='option' and normalize-space(.)='$ahs_item']")

WebUI.click(AHS_Item)

WebUI.click(findTestObject('Master AHS/Main Material/AHS_Item_Description'))

String ahs_item_description = 'Keramik HT'

TestObject AHS_Item_Description = new TestObject()

AHS_Item_Description.addProperty('xpath', ConditionType.EQUALS, "//div[@role='option' and normalize-space(.)='$ahs_item_description']")

WebUI.click(AHS_Item_Description)

WebUI.click(findTestObject('Master AHS/Main Material/Item_SubCategory'))

String ahs_sub_kategori = 'Keramik/HT 50-60 cm'

TestObject AHS_Sub_Category = new TestObject()

AHS_Sub_Category.addProperty('xpath', ConditionType.EQUALS, "//div[@role='option' and normalize-space(.)='$ahs_sub_kategori']")

WebUI.click(AHS_Sub_Category)

