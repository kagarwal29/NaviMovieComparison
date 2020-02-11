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
	 * Find element by Id and perform sendKeys.
	 */
	public void sendKeys(WebDriver driver,By locator, String text) {
		
		driver.findElement(locator).sendKeys(text);
		
	}
	
	/*
	 * Find element by Id and perform keyboard action.
	 */
	public void sendKeys(WebDriver driver,By locator, Keys key) {
		
		driver.findElement(locator).sendKeys(key);
		
	}
	
	/*
	 * Find element by Id and perform keyboard action.
	 */
	public boolean compareString(String actual,String expected) {
		
		return actual.contains(expected);
		
	}
	
}
