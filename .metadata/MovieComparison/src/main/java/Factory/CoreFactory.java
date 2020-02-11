package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import InterfaceImplementation.ListMoviesOnIMDB;
import InterfaceImplementation.ListMoviesOnLetterBoxD;
import InterfaceImplementation.ScrapeIMDBPage;
import InterfaceImplementation.ScrapeLetterBoxDPage;
import Interfaces.IListMovies;
import Interfaces.IScrapePage;

public class CoreFactory {

	private static String webBrowser="Chrome";
	private CoreFactory() {
		
	}
	
	private static WebDriver driver = null;	
	
	/*
	 * Set desktop browser for which automation will run
	 */
	public static void setBrowser(String browser){		
		webBrowser=browser;
	}
	
	/*
	 * Get Browser Name
	 */
	public static String getBrowser(){
		return webBrowser;
	}
	
	/*
	 * Function to instantiate browser specific driver
	 */
	private static void intializeWebDriver(){
		
		if (getBrowser().contentEquals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (getBrowser().contentEquals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else
			throw new RuntimeException(String.format("'%s' is not supported.", getBrowser())); 		
	}
	
	/*
	 * Method to return WebDriver instance
	 */
	public static WebDriver getWebDriverInstance() {
		
		  if(null==driver){
			  intializeWebDriver();
	        }
	        return driver;	
	}
	
	/*
	 * This method returns appropriate Site Object to traverse the website
	 */
	public static IListMovies getSiteObject(String siteName) {
		
		if (siteName.contentEquals("IMDB"))
			return new ListMoviesOnIMDB();
		else if (siteName.contentEquals("LetterBoxD"))
			return new ListMoviesOnLetterBoxD();
		else
			throw new RuntimeException(String.format("'%s' is not supported.", siteName)); 
	}
	
	/*
	 * This method returns appropriate Page Object based on site for scraping.
	 */
	public static IScrapePage getPageObject(String siteName) {
		
		if (siteName.contentEquals("IMDB"))
			return new ScrapeIMDBPage();
		else if (siteName.contentEquals("LetterBoxD"))
			return new ScrapeLetterBoxDPage();
		else
			throw new RuntimeException(String.format("'%s' is not supported.", siteName)); 
	}
}
