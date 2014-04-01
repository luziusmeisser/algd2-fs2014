package ch.fhnw.algd2.stephenrandles.lesson07;

import static org.junit.Assert.*;
import org.junit.*;

public class HashMapTest {
	HashMap arr;
	
	@Before
	public void setUp() {
		arr = new HashMap();
	}
	
	@Test
	public void testDoubleKeys() {
		String key1 = "1";
		String value1 = "Eins";
		
		String key2 = "71443";
		String value2 = "Zwei";
		
		arr.put(key1, value1);
		arr.put(key2, value2);
		
		assertTrue(arr.get(key1).equals(value1));
		assertTrue(arr.get(key2).equals(value2));
	}
	
	@Test
	public void testNonexistantKey() {
		String invalidKey = "BLABLABLA"; 
		assertTrue(arr.get(invalidKey) == null);
	}

}
