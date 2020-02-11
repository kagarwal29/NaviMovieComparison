package NaviQAProject.MovieComparison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.testng.Assert;
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
	
	ArrayList<String> list = new ArrayList<String>();
	
	
	@BeforeTest
	public void setup() {
		CoreFactory.setBrowser(Browser.Chrome.toString());
		list.add("LetterBoxD");
		list.add("IMDB");
	}
	
	@Test(testName = "SingleDirectorDifferentSiteComparison",
	          dataProvider = "DirectorName",
	          dataProviderClass = DirectorMovieComparisonDataProvider.class)
	    public void movieComparison(String dName){
		Map<String, HashMap<String,String>> mpMaps=new HashMap<String, HashMap<String,String>>();
			for (String siteName:list) {
				IListMovies siteObject=CoreFactory.getSiteObject(siteName);		
				String url = siteObject.searchMoviesByDirector(dName);
				IScrapePage scrape = CoreFactory.getPageObject(siteName);
				HashMap<String,String> map=scrape.scrapeAndStore(url);
				mpMaps.put(siteName, map);
			}
			if (null!=mpMaps)
			new CompareMovieList(mpMaps);
	}
	
@AfterTest
public void compareAndReportResult() {	
	CoreFactory.getWebDriverInstance().quit();	
	}
}
