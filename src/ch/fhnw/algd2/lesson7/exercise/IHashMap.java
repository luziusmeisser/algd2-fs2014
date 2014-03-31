// Created by Luzius on 31.03.2014

package ch.fhnw.algd2.lesson7.exercise;

/**
 * Exercise 7:
 * 
 * Implement your own hashmap (you may copy code from the theory sheet). You can choose
 * the way of dealing with collisions.
 * 
 * Initialize the HashMap with a size of 1000. You can assume that this is enough and do
 * not need to implement resize.
 */
public interface IHashMap {
	
	public void put(String key, String value);
	
	public String get(String key);

}
