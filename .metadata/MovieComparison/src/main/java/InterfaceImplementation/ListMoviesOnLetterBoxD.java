package InterfaceImplementation;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		util.click(driver, By.cssSelector(LetterBoxDLocators.SEARCH_ICON));
		if (!util.compareString(driver.getCurrentUrl(), String.format("https://letterboxd.com/search/%s+%s/",dName.split(" ")[0],dName.split(" ")[1])))
			Assert.fail("LetterBoxD - Could not navigate to Search Page");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LetterBoxDLocators.DIR_NAME,dName))));
		util.click(driver, By.xpath(String.format(LetterBoxDLocators.DIR_NAME,dName)));
		if (!driver.getTitle().contains(dName))
			Assert.fail("LetterBoxD - Could not navigate to Director details page");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LetterBoxDLocators.ROLE_DROPDOWN)));
		util.click(driver, By.xpath(LetterBoxDLocators.ROLE_DROPDOWN));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LetterBoxDLocators.SELECT_DIRECTOR,dName.split(" ")[0].toLowerCase(),dName.split(" ")[1].toLowerCase()))));
		util.click(driver, By.xpath(String.format(LetterBoxDLocators.SELECT_DIRECTOR,dName.split(" ")[0].toLowerCase(),dName.split(" ")[1].toLowerCase())));
		return driver.getCurrentUrl();
		}
		catch (TimeoutException e) {
			throw new NoSuchElementException("LetterBoxD - Wait Timed Out! Could not locate Element");
		}
	}
}
