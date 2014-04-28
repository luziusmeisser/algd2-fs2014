// Created by Kevin Wieser on 04.04.2014
package ch.fhnw.algd2.kevinwieser;

import java.util.ArrayList;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

/**
 * Exercise 7:
 * 
 * Implement your own hashmap (you may copy code from the theory sheet). You can choose
 * the way of dealing with collisions.
 * 
 * Initialize the HashMap with a size of 1000. You can assume that this is enough and do
 * not need to implement resize.
 */

public class HashMap implements IHashMap {
	
	
	// Problem -> Bei Hash
	private ArrayList<Element>[] array = new ArrayList[1000];

	@Override
	public void put(String key, String value) {
		
		int hash = Math.abs(key.hashCode() % array.length); // %1000 bleibe im 1000er Bereich
		if (array[hash] == null) {
			array[hash] = new ArrayList<Element>();
			array[hash].add(new Element(key, value));
		} else {
			array[hash].add(new Element(key, value));
		}
		
	}

	@Override
	public String get(String key) {
		int hash = Math.abs(key.hashCode() % array.length); // %1000 bleibe im 1000er Bereich
		if (array[hash] != null) {
			for (Element e: array[hash]) {
				if (e.key.equals(key)) {
					return e.value;
				}
			}
		}
		
		return null;
	}
	
	private class Element{
		
		Element(String key, String value) {
			this.key = key;
			this.value = value;
		}
		String key;
		String value;
	}
	

}
