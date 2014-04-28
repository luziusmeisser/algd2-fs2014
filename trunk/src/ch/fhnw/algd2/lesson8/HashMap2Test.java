// Created by Luzius on 31.03.2014

package ch.fhnw.algd2.lesson8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class HashMap2Test {

	private ArrayList<IHashMap2> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

		// add your own implementation here
		lists.add(new ch.fhnw.algd2.florianfankhauser.lesson8.HashMap());
//		lists.add(new ch.fhnw.algd2.stephenrandles.lesson08.HashMap2());
//		lists.add(new ch.fhnw.algd2.luzius.HashMap2());
	}
	
	@Test
	public void testHashMap(){
		for (IHashMap2 map: lists){
			long t0 = System.nanoTime();
			HashMap<String, String> control = new HashMap<>();
			Random rand = new Random(12);
			for(int i=0; i<50000; i++){
				String key = Integer.toString(rand.nextInt(10000));
				String val = Long.toString(rand.nextLong());
				control.put(key, val);
				map.put(key, val);
			}
			for(int i=0; i<50000; i++){
				String key = Integer.toString(rand.nextInt(10000));
				String val = Long.toString(rand.nextLong());
				control.remove(key);
				map.remove(key);
			}
			for (String key: new ArrayList<String>(control.keySet())){
				assert control.remove(key).equals(map.remove(key));
			}
			long t1 = System.nanoTime();
			System.out.println(map.getClass().getName() + " completed test in " + ((t1 - t0)/1000000) + " ms");
		}
	}
	
	
}
