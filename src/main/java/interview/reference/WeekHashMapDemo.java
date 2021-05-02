package interview.reference;

import java.util.Map;
import java.util.WeakHashMap;

import org.junit.Test;

public class WeekHashMapDemo {
	
	/**
	 * WeakHashMap 使用 gc 后，依然不会是null, 但内容清空
	 */
	@Test
	public void testDemo1() {
		Map<Integer, String> map = new WeakHashMap<>();
		Integer key = new Integer(1);
		String value = "hello";
		
		map.put(new Integer(2), "Hello World");
		map.put(key, value);
		System.out.println(map);
		
		key = null;
		System.out.println(map);
		
		System.gc();
		System.out.println(map);
	}
	
}
