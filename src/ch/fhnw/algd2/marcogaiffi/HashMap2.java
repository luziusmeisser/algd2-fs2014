// Created by Marco on 05.05.2014
//inspired by Emanuel Mistretta

package ch.fhnw.algd2.marcogaiffi;

import ch.fhnw.algd2.lesson8.IHashMap2;

/**
 * Exercise 8:
 * 
 * HashMap with rehashing.
 * 
 * Similar to exercise 7, we implement a hash map, but this time with re-hashing
 * instead of using linked lists. Also, we want to support removes as well as
 * resize this time.
 * 
 * Visualization: http://www.cs.usfca.edu/~galles/visualization/ClosedHash.html
 * (Unlike this visualization, we do not allow multiple identical keys.)
 */
public class HashMap2 implements IHashMap2 {

	int map_size;
	Element[] map;
	int counter;

	public HashMap2() {
		this(999);
	}

	public HashMap2(int size) {
		this.map_size = size;
		this.map = new Element[this.map_size];
		this.counter = 0;
	}

	@Override
	public void put(String key, String value) {
		if (checkSize()) {
			resizeMap();
			int hashCode = generateHashCode(key);
			while (true) {
				if (map[hashCode] == null || map[hashCode].Key.equals(key)) {
					break;
				}
				hashCode = generateNextHashCode(hashCode);
			}
		}
	}

	@Override
	public String get(String key) {
		int hashCode = findElement(key);
		return (hashCode < 0) ? null : this.map[hashCode].value;
	}

	@Override
	public String remove(String key) {
		int hashCode = findElement(key);
		if(hashCode<0){
			return null;
		}
		map[hashCode].deleted=true;
		return map[hashCode].value;
	}

	private void resizeMap(){
		HashMap2 tempMap = new HashMap2(this.map_size * 2);
		for(Element element : this.map){
			if(element != null && !element.deleted){
				tempMap.put(element.Key, element.value);
			}
		}
		this.map=tempMap.map;
		this.map_size=tempMap.map_size;
		this.counter=tempMap.counter;
	}
	
	// according to HashMap
	private int generateHashCode(String value) {
		return (value.hashCode() & 0x7fffffff) % this.map.length;
	}

	private int generateNextHashCode(int hashCode) {
		return (++hashCode & 0x7fffffff) % this.map.length;
	}

	int findElement(String value) {
		int hashCode = generateHashCode(value);
		while (true) {
			if (map[hashCode] == null) {
				return -1;
			} else if (map[hashCode].Key.equals(value)) {
				return hashCode;
			}
			hashCode = generateNextHashCode(hashCode);
		}
	}

	boolean checkSize() {
		this.counter++;
		if (((this.map_size) / this.counter) <= 1) {
			return true;
		}
		return false;
	}

	private class Element {
		private Element next;
		private String value;
		private boolean deleted = false;

		public final String Key;

		public Element(String key, String value) {
			this.Key = key;
			this.value = value;
		}

		public Element next() {
			return this.next;
		}
	}

}
