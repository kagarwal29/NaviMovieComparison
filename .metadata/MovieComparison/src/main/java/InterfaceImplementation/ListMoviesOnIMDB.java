package InterfaceImplementation;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import CommonUtils.CommonActions;
import Factory.CoreFactory;
import Interfaces.IListMovies;
import Locators.IMDBLocators;

public class ListMoviesOnIMDB implements IListMovies {

	static Logger log = Logger.getLogger(ListMoviesOnIMDB.class.getName());
	private final String IMDB_HOMEPAGE="https://www.imdb.com/";
	WebDriver driver = CoreFactory.getWebDriverInstance();
	WebDriverWait wait = new WebDriverWait(driver,30);
	CommonActions util = new CommonActions();


	public String searchMoviesByDirector(String dName) {
		driver.navigate().to(IMDB_HOMEPAGE);
		WebDriverWait wait = new WebDriverWait(driver,30);
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(IMDBLocators.SEARCH_BAR)));
		util.sendKeys(driver, By.id(IMDBLocators.SEARCH_BAR), dName);
		log.info("Typed text to be searched!");
		util.click(driver, By.id(IMDBLocators.SEARCH_BUTTON));
		log.info("Clicked on Search Button!");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(IMDBLocators.DIR_NAME,dName))));
		util.click(driver, By.xpath(String.format(IMDBLocators.DIR_NAME,dName)));
		log.info("Clicked on Director Name!");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(IMDBLocators.DIRECTOR)));
		util.click(driver, By.xpath(IMDBLocators.DIRECTOR));
		log.info("Clicked on Director Link to filter movies by Director!");
		return driver.getCurrentUrl();
		}
		catch(Exception e){
            Assert.fail("Element not found");
            throw new RuntimeException("Element not found!");
        }
	
	}
}

