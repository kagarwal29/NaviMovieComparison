package Features;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;



public class Testing {

	public static void main(String[] args) {
	    Map<String, String> asia1 = new HashMap<String, String>();
	    asia1.put("Japan", "Tokyo");
	    asia1.put("South Korea", "Seoul");
	    asia1.put("India", "New Delhi");
	 
	    Map<String, String> asia2 = new HashMap<String, String>();
	    asia2.put("Japan", "Tokyo");
	    asia2.put("China", "Beijing");
	    asia2.put("India", "Delhi");
	 
	    MapDifference<String, String> diff = Maps.difference(asia1, asia2);
	    int var=1;
	    System.out.println(String.format("%d", var));
	 
	    System.out.println(diff.entriesDiffering().toString());
	    System.out.println(diff.entriesOnlyOnLeft());
	    System.out.println(diff.entriesOnlyOnRight());
	    assertFalse(diff.areEqual());

	}
}
