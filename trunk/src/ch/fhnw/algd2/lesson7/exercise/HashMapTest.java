// Created by Luzius on 31.03.2014

package ch.fhnw.algd2.lesson7.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest {

	private ArrayList<IHashMap> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

		// add your own implementation here
//        lists.add(new ch.fhnw.algd2.luzius.HashMap());
		lists.add(new ch.fhnw.algd2.stephenrandles.lesson07.HashMap());
		lists.add(new ch.fhnw.algd2.mariusdubach.lesson7.HashMap());
	}
	
	@Test
	public void testHashMap(){
		for (IHashMap map: lists){
			HashMap<String, String> control = new HashMap<>();
			Random rand = new Random(12);
			for(int i=0; i<500; i++){
				String key = Integer.toString(rand.nextInt(100000));
				String val = Long.toString(rand.nextLong());
				control.put(key, val);
				map.put(key, val);
			}
			for (String key: control.keySet()){
				assert control.get(key).equals(map.get(key));
			}
		}
	}
	
	
}
