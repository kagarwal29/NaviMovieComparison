package InterfaceImplementation;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import CommonUtils.CommonActions;
import Factory.CoreFactory;
import Interfaces.IListMovies;
import Locators.LetterBoxDLocators;

public class ListMoviesOnLetterBoxD implements IListMovies {

	static Logger log = Logger.getLogger(ListMoviesOnLetterBoxD.class.getName());
	private final String LETTERBOX_D_HOMEPAGE="https://letterboxd.com/";
	WebDriver driver = CoreFactory.getWebDriverInstance();
	WebDriverWait wait = new WebDriverWait(driver,30);
	CommonActions util = new CommonActions();
	

	public String searchMoviesByDirector(String dName) {
		driver.navigate().to(LETTERBOX_D_HOMEPAGE);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LetterBoxDLocators.SEARCH_BAR)));
			util.sendKeys(driver, By.id(LetterBoxDLocators.SEARCH_BAR), dName);
			log.info("Typed text to be searched!");
			util.sendKeys(driver, By.id(LetterBoxDLocators.SEARCH_BAR), Keys.ENTER);
			log.info("Clicked on Search!");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LetterBoxDLocators.DIR_NAME,dName))));
			util.click(driver, By.xpath(String.format(LetterBoxDLocators.DIR_NAME,dName)));
			log.info("Clicked on Director's Name!");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LetterBoxDLocators.ROLE_DROPDOWN)));
			util.click(driver, By.xpath(LetterBoxDLocators.ROLE_DROPDOWN));
			log.info("Clicked on Category Dropdown!");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LetterBoxDLocators.SELECT_DIRECTOR,dName.split(" ")[0].toLowerCase(),dName.split(" ")[1].toLowerCase()))));
			util.click(driver, By.xpath(String.format(LetterBoxDLocators.SELECT_DIRECTOR,dName.split(" ")[0].toLowerCase(),dName.split(" ")[1].toLowerCase())));	
			log.info("Clicked on 'Director' category"); 
			return driver.getCurrentUrl();
		}
		catch(Exception e){
	        Assert.fail("Element not found");
	        throw new RuntimeException("Element not found!");
		}
	}
}
