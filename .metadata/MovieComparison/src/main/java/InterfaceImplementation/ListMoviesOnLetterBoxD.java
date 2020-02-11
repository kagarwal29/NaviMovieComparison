package InterfaceImplementation;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
		WebElement elm;
		elm=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LetterBoxDLocators.SEARCH_BAR)));
		if (!elm.isDisplayed())
			Assert.fail("LetterBoxD - Could not locate Search Bar!");
		util.sendKeys(driver, By.id(LetterBoxDLocators.SEARCH_BAR), dName);
		util.sendKeys(driver, By.id(LetterBoxDLocators.SEARCH_BAR), Keys.ENTER);
		if (!util.compareString(driver.getCurrentUrl(), String.format("https://letterboxd.com/search/%s+%s/",dName.split(" ")[0],dName.split(" ")[1])))
			Assert.fail("LetterBoxD - Could not navigate to Search Page");
		elm=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LetterBoxDLocators.DIR_NAME,dName))));
		if (!elm.isDisplayed())
			Assert.fail("LetterBoxD - Could not locate Director Name on Search Results Page!");
		util.click(driver, By.xpath(String.format(LetterBoxDLocators.DIR_NAME,dName)));
		if (!util.compareString(driver.getCurrentUrl(), String.format("https://letterboxd.com/producer/%s-%s/",dName.split(" ")[0].toLowerCase(),dName.split(" ")[1].toLowerCase())))
			Assert.fail("LetterBoxD - Could not navigate to Director details page");
		elm=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LetterBoxDLocators.ROLE_DROPDOWN)));
		if (!elm.isDisplayed())
			Assert.fail("LetterBoxD - Could not locate Category Dropdown!");
		util.click(driver, By.xpath(LetterBoxDLocators.ROLE_DROPDOWN));
		elm=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LetterBoxDLocators.SELECT_DIRECTOR,dName.split(" ")[0].toLowerCase(),dName.split(" ")[1].toLowerCase()))));
		if (!elm.isDisplayed())
			Assert.fail("LetterBoxD - Could not locate 'Director' item from Category Dropdown!");
		util.click(driver, By.xpath(String.format(LetterBoxDLocators.SELECT_DIRECTOR,dName.split(" ")[0].toLowerCase(),dName.split(" ")[1].toLowerCase())));	
//			if (!util.compareString(driver.getCurrentUrl(), String.format("https://letterboxd.com/director/%s-%s/",dName.split(" ")[0].toLowerCase(),dName.split(" ")[1].toLowerCase())));
//				Assert.fail("LetterBoxD - Could not navigate to 'Movies By Director' details page");
		return driver.getCurrentUrl();
	}
}
