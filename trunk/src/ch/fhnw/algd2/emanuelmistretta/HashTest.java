// Created by Emanuel Mistretta on 06.04.2014

package ch.fhnw.algd2.emanuelmistretta;

import static org.junit.Assert.assertEquals;

public class HashTest {
    
    public static void main(String[] args) {

	HashMap map = new HashMap();
	
	System.out.println("Key1".hashCode());
	System.out.println("Key2".hashCode());
	
	map.put("Key1", "val1");
	map.put("Key1", "val2");
	
	System.out.println(map.get("Key1"));
	System.out.println(map.get("Key1"));
	
	
	System.out.println("FB".hashCode());
	System.out.println("EA".hashCode());
	
	 map.put("FB", "first");
	 map.put("Ea", "second");
	 
	 System.out.println(map.get("FB"));
	 System.out.println(map.get("Ea"));
	        

    }

}
