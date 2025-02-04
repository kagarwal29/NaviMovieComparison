package InterfaceImplementation;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import CommonUtils.CommonActions;
import Factory.CoreFactory;
import Interfaces.IListMovies;
import Locators.IMDBLocators;
import Locators.LetterBoxDLocators;

public class ListMoviesOnIMDB implements IListMovies {

	static Logger log = Logger.getLogger(ListMoviesOnIMDB.class.getName());
	private final String IMDB_HOMEPAGE="https://www.imdb.com/";
	WebDriver driver = CoreFactory.getWebDriverInstance();
	WebDriverWait wait = new WebDriverWait(driver,30);
	CommonActions util = new CommonActions();


	public String searchMoviesByDirector(String dName) {
		driver.navigate().to(IMDB_HOMEPAGE);
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(IMDBLocators.SEARCH_BAR)));
		util.sendKeys(driver, By.id(IMDBLocators.SEARCH_BAR), dName);
		util.click(driver, By.id(IMDBLocators.SEARCH_BUTTON));
		if (!util.compareString(driver.getCurrentUrl(), String.format("https://www.imdb.com/find?q=%s+%s&ref_=nv_sr_sm",dName.split(" ")[0],dName.split(" ")[1])))
			Assert.fail("IMDB - Could not navigate to Search Page");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(IMDBLocators.DIR_NAME,dName))));
		util.click(driver, By.xpath(String.format(IMDBLocators.DIR_NAME,dName)));
		if (!util.compareString(driver.getTitle(), String.format("%s - IMDb",dName)))
			Assert.fail("IMDB - Could not navigate to Director details page");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(IMDBLocators.DIRECTOR)));
		util.click(driver, By.xpath(IMDBLocators.DIRECTOR));
		if(!driver.getCurrentUrl().contains("#director"))
			Assert.fail("IMDB - Could not click on Director link");
		return driver.getCurrentUrl();
		}
		catch (TimeoutException e) {
			throw new NoSuchElementException("IMDB - Wait Timed Out! Could not locate Element");
		}
	
	}
}

