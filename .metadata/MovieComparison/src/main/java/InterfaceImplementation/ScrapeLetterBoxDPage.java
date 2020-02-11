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
import Locators.IMDBLocators;
import Locators.LetterBoxDLocators;

public class ScrapeLetterBoxDPage implements IScrapePage{

	static Logger log = Logger.getLogger(ScrapeLetterBoxDPage.class.getName());
	HashMap<String, String> letterboxdMap = new HashMap<String,String>();
	
	public HashMap<String,String> scrapeAndStore(String url){
		
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			log.info(String.format("Connected to '%s'",url));
			Elements elms = doc.getElementsByAttributeValueContaining(LetterBoxDLocators.ROOT_KEY, LetterBoxDLocators.ROOT_KEY_VALUE);
			for (Element elm : elms) {				
				letterboxdMap.put(fetchMovieTitle(elm),fetchMovieReleaseYear(elm));	

			}
			return letterboxdMap;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("JSOUP Connection could not be established!");
		}

	}
	
	public String fetchMovieTitle(Element elm){
				
		return elm.attr("data-film-name");
	}
	
	public String fetchMovieReleaseYear(Element elm) {
		
		return elm.attr("data-film-release-year");
	}
}

