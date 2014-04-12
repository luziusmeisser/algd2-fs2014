// Created by Florian Fankhauser on 04.04.2014

package ch.fhnw.algd2.florianfankhauser.lesson7;

import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {
	private static final int MAP_SIZE = 1000;
	private MapElement[] elements = new MapElement[MAP_SIZE];

	@Override
	public void put(String key, String value){
		int index = calculateIndex(key);
		MapElement el = new MapElement(key, value);
		if (elements[index] != null) {
			el.next = elements[index];
		}
		elements[index] = el;
	}

	@Override
	public String get(String key) {
		int index = calculateIndex(key);
		MapElement el = elements[index];
		if (el.next != null) {
			return findExact(key, el).value;
		}
		return el.value;
	}
	
	private int calculateIndex(String key) {
		long index = 1; 
		for (int i = 0; i < key.length(); i++) {
			index += key.charAt(i);
		}
		index = index % 1000;
		return (int) index;
	}

	private MapElement findExact(String key, MapElement el) {
		if (el == null) throw new NoSuchElementException();
		if (el.key.equals(key)) return el;
		return findExact(key, el.next);
	}
	
	private class MapElement {
		private String key;
		private String value;
		private MapElement next;
		
		private MapElement(String key, String value) {
			this.value = value;
			this.key = key;
		}
	}
}