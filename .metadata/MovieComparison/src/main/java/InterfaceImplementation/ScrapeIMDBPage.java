package InterfaceImplementation;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.Assert;

import Interfaces.IScrapePage;


public class ScrapeIMDBPage implements IScrapePage{

	static Logger log = Logger.getLogger(ScrapeIMDBPage.class.getName());
	HashMap<String,String> imdbMap = new HashMap<String,String>();

	
	public HashMap<String,String> scrapeAndStore(String url, String key, String value){
		
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			log.info(String.format("Connected to '%s'",url));
			Elements elms = doc.getElementsByAttributeValueStarting(key, value);

			for (Element elm : elms) {				
				if (elm.getElementsByAttributeValue("class", "filmo-episodes").isEmpty()) {					
					imdbMap.put(fetchMovieTitle(elm),fetchMovieReleaseYear(elm));
				}
			}
			return imdbMap;
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("IMDB Page could not be scraped");
			throw new RuntimeException("IMDB Page could not be scraped");
		}

	}
	
	public String fetchMovieTitle(Element elm){
		
		return elm.getElementsByAttributeValueStarting("href", "/title/").html();
	}
	
	public String fetchMovieReleaseYear(Element elm) {
		
		return elm.getElementsByAttributeValue("class", "year_column").html().substring(6);
	}
}
