package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class GreedyAlgorithm {
	
	Map<String, HashSet<String>> maps = null;

	@Before
	public void prepare() {
		maps = new HashMap<>();
		HashSet<String> set1 = new HashSet<>();
		set1.add("BJ");
		set1.add("SH");
		set1.add("TJ");

		HashSet<String> set2 = new HashSet<>();
		set2.add("GZ");
		set2.add("BJ");
		set2.add("SZ");

		HashSet<String> set3 = new HashSet<>();
		set3.add("CD");
		set3.add("SH");
		set3.add("HZ");

		HashSet<String> set4 = new HashSet<>();
		set4.add("TJ");
		set4.add("SH");
		
		HashSet<String> set5 = new HashSet<>();
		set5.add("HZ");
		set5.add("DL");
		
		maps.put("K1", set1);
		maps.put("K2", set2);
		maps.put("K3", set3);
		maps.put("K4", set4);
		maps.put("K5", set5);
	}
	
	@Test
	public void testDemo() {
		HashSet<String> allCities = maps.values().stream().reduce(this::mergeHashSet).get();
		
		List<String> keyCollection = new ArrayList<String>();
		
		String maxKey  = "";
		int count = 0;
		int max   = 0;
		while(allCities.size()!=0) {
			maxKey  = "";
			count = 0;
			max   = Integer.MIN_VALUE;
			Iterator<String> keys = maps.keySet().iterator();
			while(keys.hasNext()) {
				String key = keys.next();
				HashSet<String> cities = maps.get(key);
				count = 0;
				for(String city : cities) {
					if(allCities.contains(city)) {
						count ++;
					}
				}
				if (count > max) {
					max = count;
					maxKey = key;
				}
			}
			allCities.removeAll(maps.get(maxKey));
			keyCollection.add(maxKey);
			maps.remove(maxKey);
		}
		System.out.println(keyCollection);
	}
	
	private HashSet<String> mergeHashSet(HashSet<String> s1, HashSet<String> s2) {
		HashSet<String> result = new HashSet<>(s1);
		result.addAll(s2);
		return result;
	}

}
