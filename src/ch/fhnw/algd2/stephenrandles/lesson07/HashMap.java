// Created by Stephen Randles 31.03.2014

package ch.fhnw.algd2.stephenrandles.lesson07;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {
	private String[] values;
	
	private final int ARRAY_SIZE = 1000;

	public HashMap() {
		values = new String[ARRAY_SIZE]; 
	}

	@Override
	public void put(String key, String value) {
		values[calcIndex(key)] = value;
	}

	@Override
	public String get(String key) {
		return values[calcIndex(key)];
	}
	
	private int calcIndex(String key) {
		return key.hashCode() % values.length;
	}

}
