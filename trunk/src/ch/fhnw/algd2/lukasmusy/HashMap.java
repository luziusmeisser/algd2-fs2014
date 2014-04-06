//created by lukas.musy on Apr 6, 2014

package ch.fhnw.algd2.lukasmusy;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {
	private Element[] values;
	
	private final int ARRAY_SIZE = 1000;

	public HashMap() {
		values = new Element[ARRAY_SIZE]; 
	}

	@Override
	public void put(String key, String value) {
		int index = hash(key);
		
		Element e = values[index];
		if (e == null) {
			values[index] = new Element(key, value);
		} else {
			while (e.hasNext() && e.key != key) {
				e = e.next;
			}
			
			if (e.key.equals(key)) {
				e.value = value;
			} else {
				e.next = new Element(key, value);
			}
		}
	}

	@Override
	public String get(String key) {
		int index = hash(key);
		Element e = values[index];
		
		if (e == null) 		
			return null;
		
		while (e.hasNext() && e.key != key) {
			e = e.next;
		}
		
		if (e.key == key)	
			return e.value;
		else				
			return null;
	}
	
	private int hash(String key) {
		return (key.hashCode() & 0x7FFFFFFF) % values.length;	
	}
	
	
	
	class Element {
		String key;
		String value;
		Element next;
		
		public Element(String key, String value) {
			this.set(key,value);
		}
		
		void set(String key, String contents) {
			this.key = key;
			this.value = contents; 
		}
		
		boolean hasNext() {
			return next != null;
		}
		
	}

}
