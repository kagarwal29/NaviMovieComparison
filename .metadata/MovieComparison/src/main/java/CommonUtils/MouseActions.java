package CommonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseActions {


	
	/*
	 * Hover over element.
	 */
	public void hoverOverElement(WebDriver driver,By location) {
		
		Actions action = new Actions(driver);
		WebElement target = driver.findElement(location);
		action.moveToElement(target);
		
	}
}
