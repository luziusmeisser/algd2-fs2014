// Created by Stephen Randles 28.04.2014
package ch.fhnw.algd2.stephenrandles.lesson08;
import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
	private Element[] values;
	private int fillState;
	
	private final int ARRAY_SIZE = 1000;
	private final int REHASH_PERCENTAGE = 85;
	private final double GROWTH_RATE = 2.0;

	public HashMap2() {
		values = new Element[ARRAY_SIZE];
		fillState = 0;
	}

	@Override
	public void put(String key, String value) {		
		int tries = 0;
		int potentialIndex = calcIndex(key, tries);
		
		while (potentialIndex < values.length
				&& values[potentialIndex] != null
				&& !values[potentialIndex].key.equals(key) 
				&& !values[potentialIndex].deleted)
		{
			++tries;
			potentialIndex = calcIndex(key, tries);
		}
		
		if (potentialIndex >= values.length || isResizeNecessary()) {
			resize();
		}
		
		if (values[potentialIndex] == null) {
			values[potentialIndex] = new Element();
		}
		
		values[potentialIndex].set(key, value);
		++fillState;
	}

	@Override
	public String get(String key) {	
		Element e = findElement(key);
		if (e != null) {
			return e.value;
		} else {
			return null;
		}		
	}

	private Element findElement(String key) {
		int tries = 0;
		int potentialIndex = calcIndex(key, tries);
		
		while (potentialIndex < values.length
				&& values[potentialIndex] != null
				&& !values[potentialIndex].key.equals(key))
		{
			++tries;
			potentialIndex = calcIndex(key, tries);
		}
		
		if (values[potentialIndex] != null && values[potentialIndex].key.equals(key)) {
			return values[potentialIndex];
		} else {
			return null;
		}
	}

	@Override
	public String remove(String key) {
		Element e = findElement(key);
		
		if (e != null) {
			e.deleted = true;
			--fillState;
			return e.value;
		} else {
			return null;
		}
	}
	
	private int calcIndex(String key, int tries) {
		int index = (key.hashCode() & 0x7FFFFFFF) % values.length;	// AND to ensure sign bit is low -> no negative key
		
		// Probing-dependant implementation here:
		index += tries;
		
		return index;
	}
	
	private int arrayFillPercentage() {
		return (fillState / values.length) * 100;
	}
	
	private boolean isResizeNecessary() {
		return arrayFillPercentage() > REHASH_PERCENTAGE;
	}
	
	private void resize() {
		Element[] old = values;
		int newSize = (int) (values.length * GROWTH_RATE);
		values = new Element[newSize];
		
		for (Element e : old) {
			if (e != null && !e.deleted)
				put(e.key, e.value);
		}
	}
	
	class Element {
		String key;
		String value;
		boolean deleted;
		
		public Element() {};
		
		public Element(String key, String value) {
			this.set(key,value);
		}
		
		void set(String key, String contents) {
			this.key = key;
			this.value = contents; 
		}		
	}

}
