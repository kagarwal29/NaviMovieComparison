package DataProviders;

import org.testng.annotations.DataProvider;

public class DirectorMovieComparisonDataProvider {

	   @DataProvider(name = "DirectorAndWebsiteName")
	    public static Object[][] directorNameDataProviderMethod() {
	        
		   return new Object[][] {
				   {"Steven Spielberg","IMDB","id","director"},
				   {"Steven Spielberg","LetterBoxD","class","film-poster"}
		   };
	    }
}
