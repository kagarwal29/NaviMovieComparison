package DataProviders;

import org.testng.annotations.DataProvider;

public class DirectorMovieComparisonDataProvider {

	   @DataProvider(name = "DirectorName")
	    public static Object[][] directorNameDataProviderMethod() {	        
		   return new Object[][] {
				   {"Steven Spielberg"},
				   {"Christopher Nolan"}
		   };
	    }
}
