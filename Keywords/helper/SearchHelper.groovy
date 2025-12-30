package helper

import com.kms.katalon.core.annotation.Keyword
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.Keys
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement

class SearchHelper {

    @Keyword
    def inputSearchByIndex(int index, String searchText, boolean pressEnter = false) {

        def driver = DriverFactory.getWebDriver()

        String xpath = "(//input[@placeholder='Search...'])[${index}]"

        WebUI.comment("Search using index ${index} with text: ${searchText}")

        WebElement searchInput = driver.findElement(By.xpath(xpath))

        searchInput.clear()
        searchInput.sendKeys(searchText)

        if (pressEnter) {
            searchInput.sendKeys(Keys.ENTER)
        }
    }
	
	@Keyword
	def selectFilterByIndex(int index, String expectedText) {
	
	    def driver = DriverFactory.getWebDriver()
	
	    def dropdown = driver.findElement(
	        By.xpath("(//button[@aria-haspopup='menu'])[${index}]")
	    )
	    dropdown.click()
	

	    boolean menuOpened = false
	    for (int i = 0; i < 10; i++) {
	        if (driver.findElements(
	            By.xpath("//div[@role='menu' and @data-state='open']")
	        ).size() > 0) {
	            menuOpened = true
	            break
	        }
	        WebUI.delay(0.3)
	    }
	
	    assert menuOpened : "Dropdown menu tidak terbuka"
	
	    def options = driver.findElements(
	        By.xpath("//div[@role='menu']//div[.//span]")
	    )
	
	    assert options.size() > 0 : "Dropdown menu kosong"
	
	    boolean found = false
	
	    for (opt in options) {
	
	        String text = opt.findElement(By.xpath(".//span[1]")).getText().trim()
	        WebUI.comment("Option found: ${text}")
	
	        if (text.equalsIgnoreCase(expectedText)) {
	
	            opt.click()
	            WebUI.delay(0.3)
	

	            new Actions(driver)
	                .moveByOffset(5, 5)
	                .click()
	                .perform()
	
	            found = true
	            WebUI.comment("Toggled '${text}' on dropdown index ${index}")
	            break
	        }
	    }
	
	    assert found : "Option '${expectedText}' tidak ditemukan di dropdown index ${index}"
	}
	


}
