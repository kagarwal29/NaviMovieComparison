package CommonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class CommonActions {

	/*
	 * Find element by element locator and click on it.
	 */
	public void click(WebDriver driver,By locator) {
		
		driver.findElement(locator).click();
		
	}
	
	/*
	 * Find element by locator and perform sendKeys.
	 */
	public void sendKeys(WebDriver driver,By locator, String text) {
		
		driver.findElement(locator).sendKeys(text);
		
	}
	
	/*
	 * Compare two Strings and return boolean
	 */
	public boolean compareString(String actual,String expected) {
		
		return actual.contains(expected);
		
	}
	
}
