package book.ch12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapTest {
	
	void mList() {
		List<String> li = new ArrayList<>();
		li.add(0, "사과");
		li.add("포도");
		li.add("바나나");
		for(String fruit :  li) {
			System.out.println(fruit);
		}
	}
	
	void mMap() {
		Map<String, Object> m = new HashMap<>();
		m.put("one", "사과");
		m.put("two", "포도");
		m.put("three", "키위");
		
		Object keys[] = null;
		keys = m.keySet().toArray();
		for(int i=0; i<keys.length; i++) {
			String key = (String) keys[i];
			String val = String.valueOf(m.get(key));
			System.out.println(key + " " + val);
		}
	}

	public static void main(String[] args) {
		MapTest test = new MapTest();
		test.mList();
		test.mMap();
	}
}