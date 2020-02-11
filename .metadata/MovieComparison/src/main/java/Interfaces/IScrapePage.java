package Interfaces;

import java.util.HashMap;
import org.jsoup.nodes.Element;

public interface IScrapePage {

	public HashMap<String,String> scrapeAndStore(String url, String key, String value);
	
	public String fetchMovieTitle(Element elm);
	
	public String fetchMovieReleaseYear(Element elm);
}
		