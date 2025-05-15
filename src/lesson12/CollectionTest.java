package lesson12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]	x = new int[1];
		
		for(int val : x) {
		}
		
		
		List<String>	l = new ArrayList<>();
		List<String>[]	la = new List[1];
		
		System.err.println("Size="+l.size());
		l.add((String)"vassya");
		System.err.println("Size="+l.size());
		System.err.println("val[0]="+(String)l.get(0));	// l[0]
		l.set(0, "petya");	// l[0] = "petya";
		l.add(0, "masha");
		
		for (Object item : l) {
			System.err.println("item="+item);
	//		l.add("sdfdf");
		}

		Iterator	it = l.iterator();
		while(it.hasNext()) {
			Object item = it.next();
			System.err.println("item="+item);
//			it.remove();
		}
		
		System.err.println("val[0]="+l.get(0));	// l[0]
		System.err.println("remove val[0]="+l.remove(0));	// l[0]
		System.err.println("Size="+l.size());
		
		Set<String>	set = new HashSet<>();
		
		System.err.println("=========== Size="+set.size());
		set.add("petya");
		set.add("vassya");
		set.add("vassya");
		System.err.println("Size="+set.size());
		System.err.println("Contains="+set.contains("vassya"));

		for (String item : set) {
			System.err.println("Item="+item);
		}
//		System.err.println("Contains="+set.remove("vassya"));
//		System.err.println("Contains="+set.remove("vassya"));

		Set<String>	newSet = Set.of("vassya", "masha");
		set.addAll(newSet);	// s1 U s2
		for (String item : set) {
			System.err.println("ADD="+item);
		}
		set.retainAll(newSet);	// s1 ^ s2
		for (String item : set) {
			System.err.println("INTERSECT="+item);
		}
		set.removeAll(Set.of("masha"));	// s1 - s2
		for (String item : set) {
			System.err.println("MINUS="+item);
		}
		
		Map<String,String>	map = new HashMap<>();
		
		System.err.println("```````````````Size="+map.size());
		System.err.println("Put="+map.put("vassya", "123456"));
		System.err.println("Put="+map.put("vassya", "654321"));
		System.err.println("Remove="+map.remove("vassya"));

		System.err.println("Put 2="+map.put("vassya", "13579"));
		System.err.println("Get="+map.get("vassya"));
		
		System.err.println("Contains="+map.containsKey("vassya"));
		System.err.println("Contains="+map.containsValue("13579"));

		for (String item : map.keySet()) {
			System.err.println("Key="+item);
		}
		for (String item : map.values()) {
			System.err.println("Value="+item);
		}
		for (Map.Entry<String,String> item : map.entrySet()) {
			System.err.println("Pair="+item.getKey()+" AND "+item.getValue());
		}
		Properties	prop = new Properties();	// Map<String,String>
		prop.getProperty("vassya","defaultVassya");
		prop.setProperty("vassya","defaultVassya");
		
		System.err.println(System.getProperty(""));
		
	}

}
