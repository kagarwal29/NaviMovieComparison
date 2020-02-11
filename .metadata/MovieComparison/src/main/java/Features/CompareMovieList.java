package Features;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;

import Reporting.ConsolePrintInTabularFormat;

public class CompareMovieList {

	/*
	 * Method to compare list of movies directed by a director across two different website.
	 * Output 
	 * 1) List of movies Common between two Website i.e IMDB and LetterBoxD
	 * 2) List of movies present only on IMDB
	 * 3) List of movies present only on LetterBoxD
	 * 4) List of movies whose title are same but year of release is different.
	 */
	public CompareMovieList(Map<String, HashMap<String, String>> mpMaps) {

	    if (null!=mpMaps) {
		MapDifference<String, String> diff = Maps.difference(mpMaps.get("IMDB"), mpMaps.get("LetterBoxD"));
	    ConsolePrintInTabularFormat table = new ConsolePrintInTabularFormat();
	    String[] columnNames = {"Title","ReleaseDate"};
	    
	    Map<String,String> commonMovies = diff.entriesInCommon();
	    System.out.println(String.format("\n============Movies in Common between IMDB and LetterBoxD (Count - %d)=============",
	    		commonMovies.size()));
	    table.convertResultInTabularFormat(columnNames,convertMapToObjectArray(commonMovies));
	    
	    Map<String,String> imdbMovies = diff.entriesOnlyOnLeft();
	    System.out.println(String.format("\n============Movies only in IMDB (Count - %d)=============",imdbMovies.size()));
	    table.convertResultInTabularFormat(columnNames,convertMapToObjectArray(imdbMovies));
	    
	    Map<String,String> letterboxdMovies = diff.entriesOnlyOnRight();
	    System.out.println(String.format("\n============Movies only in LetterBoxD (Count - %d)=============",letterboxdMovies.size()));
	    table.convertResultInTabularFormat(columnNames,convertMapToObjectArray(letterboxdMovies));
	    
	    Map<String,ValueDifference<String>> diffDates = diff.entriesDiffering();
	    System.out.println(String.format("\n============Movies with same Title but Unmatching Release Date (Count - %d)=============",diffDates.size()));
	    table.convertResultInTabularFormat(columnNames,convertValueDifferenceMapToObjectArray(diffDates));
	    System.out.println("\n\n");
	    }

	}
	
	/*
	 * Convert Map<String,String> into Object Array to be used by j-text-utils library 
	 */
	public Object[][] convertMapToObjectArray(Map<String,String> map){
			
		Object[][] arr = new Object[map.size()][2];
		Set<Entry<String, String>> entries = map.entrySet();
		Iterator<Entry<String, String>> entriesIterator = entries.iterator();

		int i = 0;
		while(entriesIterator.hasNext()){

		    Map.Entry mapping = (Map.Entry) entriesIterator.next();

		    arr[i][0] = mapping.getKey();
		    arr[i][1] = mapping.getValue();

		    i++;
		}
		return arr;
	}
	
	/*
	 * Convert Map<String,ValueDifference<String>> into Object Array to be used by j-text-utils library
	 */
	public Object[][] convertValueDifferenceMapToObjectArray(Map<String,ValueDifference<String>> map){
		
		Object[][] arr = new Object[map.size()][2];
		Set entries = map.entrySet();
		Iterator entriesIterator = entries.iterator();

		int i = 0;
		while(entriesIterator.hasNext()){

		    Map.Entry mapping = (Map.Entry) entriesIterator.next();

		    arr[i][0] = mapping.getKey();
		    arr[i][1] = mapping.getValue();

		    i++;
		}
		return arr;
	}
}
