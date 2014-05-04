// Created by Kevin Wieser on 28.04.2014

package ch.fhnw.algd2.kevinwieser;

import ch.fhnw.algd2.lesson8.IHashMap2;

/**
 * Exercise 8:
 * 
 * HashMap with rehashing.
 * 
 * Similar to exercise 7, we implement a hash map, but this time with re-hashing instead of
 * using linked lists. Also, we want to support removes this time.
 * 
 * Initialize the HashMap with a size of 1000. You can assume that this is enough and do
 * not need to implement resize.
 */
public class HashMap2 implements IHashMap2 {
	
	
	private Element[] array = new Element[10000];
	private int counter;	// Anzahl Elemente
	
	public HashMap2() {
		counter = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = new Element(-1, null);
		}
	}
		

	private class Element {
		private int key; ///////////////// war String
		private String value; 
		private int state;		
		//
		// State:
		// 0 --> frei
		// 1 --> belegt
		// 2 --> entfernt
		//		
		public Element(int key, String value) {
			this.key = key;
			this.value = value;
			state = 0;		
		}
	}

	private int hash(int key) {
		// Hash Funktion
		return key % array.length;
	}
	
	
	private Element find(int key) {

		int j = 0;
		int i;
		int hash = hash(key);
		do {
			i = (hash - j % array.length + array.length) % array.length;
			j++;
		} while (array[i].state != 0 && array[i].key != key ); 
		
		if (array[i].state == 1) {
			return array[i];
		} else {
			return null;
		}
	}
	
	
	@Override
	public void put(String key, String value) {
		int def_key = Integer.parseInt(key);
		// key --> Value (anhand von Key wird der Platz berechnet)
		
		Element e = find(def_key);
	    if (e != null){
	      e.value = value;
	      return;
	    } else {
	    	int j = 0;
	    	int index;
	    	int hash = hash(def_key);

	    	while(true) {
	    		index = ((hash - j++ + array.length) % array.length);
	    		if (array[index].state != 1) {
	    			array[index].key = def_key;
	    			array[index].value = value;
	    			array[index].state = 1;
	    			counter++;
	    			return;
	    		}
	    	}
	    }

		
	}

	@Override
	public String get(String key) {
		int def_key = Integer.parseInt(key);
		Element e = find(def_key);
		if (e != null) {
			return e.value;
		}	
		return null;
	}

	@Override
	public String remove(String key) {
		int def_key = Integer.parseInt(key);
		Element e = find(def_key);
		if (e != null) {
			e.state = 2; // 2 = gelöscht
			counter--;
			return e.value;
		} else {
			return null;
		}
	}
	
}
