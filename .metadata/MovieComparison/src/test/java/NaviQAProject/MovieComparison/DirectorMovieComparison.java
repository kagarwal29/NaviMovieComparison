package NaviQAProject.MovieComparison;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataProviders.DirectorMovieComparisonDataProvider;
import Enums.Browser;
import Factory.CoreFactory;
import Features.CompareMovieList;
import Interfaces.IListMovies;
import Interfaces.IScrapePage;

public class DirectorMovieComparison {
	
	static Logger log = Logger.getLogger(DirectorMovieComparison.class.getName());
	Map<String, HashMap<String,String>> mpMaps=new HashMap<String, HashMap<String,String>>();
	
	@BeforeTest
	public void setup() {
		CoreFactory.setBrowser(Browser.Chrome.toString());
	}
	
	@Test(testName = "SingleDirectorDifferentSiteComparison",
	          dataProvider = "DirectorAndWebsiteName",
	          dataProviderClass = DirectorMovieComparisonDataProvider.class)
	    public void movieComparison(String dName, String siteName, String rootNodeKey, String rootNodeValue){
		
		IListMovies siteObject=CoreFactory.getSiteObject(siteName);		
		String url = siteObject.searchMoviesByDirector(dName);
		IScrapePage scrape = CoreFactory.getPageObject(siteName);
		HashMap<String,String> map=scrape.scrapeAndStore(url, rootNodeKey, rootNodeValue);
		mpMaps.put(siteName, map);
	}
	
@AfterTest
public void compareAndReportResult() {
	new CompareMovieList(mpMaps);
	CoreFactory.getWebDriverInstance().quit();
}
}
