// Created by Florian Fankhauser on Feb 17, 2014

package ch.fhnw.algd2.florianfankhauser;

import java.util.Iterator;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	private LinkedListEntry<T> currentEntry = null;
	protected LinkedListEntry<T> firstEntry;

	@Override
	public boolean add(T e) {
		if (currentEntry == null) {
			currentEntry = new LinkedListEntry<>(e);
			firstEntry = currentEntry;
		} else {
			LinkedListEntry<T> entry = new LinkedListEntry<T>(e);
			currentEntry.next = entry;
			currentEntry = entry;
		}
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(this);
	}
	
	public void remove(LinkedListEntry<T> element) {
		if (firstEntry == element) {
			firstEntry = firstEntry.next;
		} else {
			LinkedListEntry<T> curr = firstEntry;
			while (curr.hasNext()) {
				if (curr.next == element) {
					curr.next = curr.next.next;
					return;
				}
			}
		}
	}
}
