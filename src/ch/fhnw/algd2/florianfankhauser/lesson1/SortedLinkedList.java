// Created by Florian Fankhauser on Feb 17, 2014

package ch.fhnw.algd2.florianfankhauser.lesson1;

import java.util.Iterator;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;
import ch.fhnw.algd2.lesson1.exercise.AbstractSortedLinkedList;

public class SortedLinkedList<T extends Comparable<T>> extends AbstractSortedLinkedList<T> {
	private LinkedList<T> list = new LinkedList<T>();
	
	@Override
	public void merge(AbstractLinkedList<T> other) {
		Iterator<T> oIt = other.iterator();
		while (oIt.hasNext()) {
			T value = oIt.next();
			add(value);
		}
	}

	@Override
	public boolean add(T e) {
		LinkedListEntry<T> entry = list.firstEntry;
		LinkedListEntry<T> newEntry = new LinkedListEntry<T>(e);
		if (entry == null) {
			list.firstEntry = newEntry;
		} else if (entry.value.compareTo(e) > 0) {
			list.firstEntry = newEntry;
			list.firstEntry.next = entry;
		} else {
			while (entry.hasNext() && entry.next.value.compareTo(e) < 0) {
				entry = entry.next;
			}
			newEntry.next = entry.next;
			entry.next = newEntry;
		}
		return true;
		
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
}