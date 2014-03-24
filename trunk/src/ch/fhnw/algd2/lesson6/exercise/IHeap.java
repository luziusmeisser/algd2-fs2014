// Created by Luzius on 24.03.2014

package ch.fhnw.algd2.lesson6.exercise;

/**
 * Exercise 6: implement a heap using an array.
 * Note that the heap must be thread-safe!
 * The Java equivalent is java.util.PriorityBlockingQueue .
 */
public interface IHeap {

	/**
	 * Adds an item to the heap.
	 */
	public void offer(String s);
	
	/**
	 * Returns the first item without removing it.
	 * Returns null if there is no element left.
	 */
	public String peek();
	
	/**
	 * Removes and returns the first item.
	 * Throws NoSuchElementException when no element left.
	 */
	public String poll();
	
}
