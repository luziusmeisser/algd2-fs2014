// Created by Florian Fankhauser on 04.04.2014

package ch.fhnw.algd2.florianfankhauser.lesson7;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {
	private static final int MAP_SIZE = 1000;
	private String[] elements = new String[MAP_SIZE];

	@Override
	public void put(String key, String value){
		int index = calculateIndex(key);
		if (elements[index] != null) {
			throw new IllegalStateException();
		}
		elements[index] = value;
	}

	@Override
	public String get(String key) {
		return elements[calculateIndex(key)];
	}
	
	private int calculateIndex(String key) {
//		return Math.abs(key.hashCode()) % 1000;
		long index = 1; 
		for (int i = 0; i < key.length(); i++) {
			index += key.charAt(i);
		}
		index = index % 1000;
		return (int) index;
	}
}
