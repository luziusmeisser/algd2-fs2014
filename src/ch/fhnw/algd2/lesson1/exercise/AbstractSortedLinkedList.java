// Created by Luzius on 2014-02-17

package ch.fhnw.algd2.lesson1.exercise;

/**
 * Exercise 1.3
 * 
 * A linked list that is sorted.
 * When adding an item, it is automatically inserted at the right position.
 * 
 * You can assume that there are no null values in the list.
 * 
 */
public abstract class AbstractSortedLinkedList<T extends Comparable<T>> extends AbstractLinkedList<T> {
	
	/**
	 * Merges this list with the other list.
	 * If the other instanceof AbstractSortedLinkedList, this operation must be completed in O(n)
	 * Otherwise, you may use O(n log(n)) steps.
	 */
	public abstract void merge(AbstractLinkedList<T> other);

}
