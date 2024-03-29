// Created by Luzius on 31.03.2014

package ch.fhnw.algd2.lesson8;

/**
 * Exercise 8:
 * 
 * HashMap with rehashing.
 * 
 * Similar to exercise 7, we implement a hash map, but this time with re-hashing instead of
 * using linked lists. Also, we want to support removes as well as resize this time.
 * 
 * Visualization: http://www.cs.usfca.edu/~galles/visualization/ClosedHash.html
 * (Unlike this visualization, we do not allow multiple identical keys.)
 */
public interface IHashMap2 {
	
	public void put(String key, String value);
	
	public String get(String key);

	public String remove(String key);

}
