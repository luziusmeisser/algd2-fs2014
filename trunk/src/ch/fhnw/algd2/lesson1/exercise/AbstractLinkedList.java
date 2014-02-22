// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.lesson1.exercise;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * Exercise 1.2: Extend AbstractSingleLinkedList with a non-abstract class. Use a linked list as data structure.
 */
public abstract class AbstractLinkedList<T> extends AbstractCollection<T> {

	@Override
	public abstract boolean add(T e);

	@Override
	public abstract Iterator<T> iterator();

	@Override
	public int size() {
		Iterator<T> iter = iterator();
		int count = 0;
		while (iter.hasNext()) {
			count++;
            iter.next();
		}
		return count;
	}

}
