// Created by Florian Fankhauser on Feb 17, 2014

package ch.fhnw.algd2.florianfankhauser.lesson1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<T> implements Iterator<T> {
	private boolean first;
	private LinkedListEntry<T> entry = null;
	private LinkedList<T> list;

	protected LinkedListIterator(LinkedList<T> linkedList) {
		this.list = linkedList;
		first = true;
	}

	@Override
	public boolean hasNext() {
		if (first) {
			return list.firstEntry != null;
		} else if (entry != null) {
			return entry.hasNext();
		}
		return false;
	}

	@Override
	public T next() {
		if (first) {
			entry = list.firstEntry;
			first = false;
		} else if (entry.next != null) {
			entry = entry.next;
		}
		if (entry != null) {
			return entry.value;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		if (entry == null) {
			throw new IllegalStateException();
		}
		list.remove(entry);
	}
}
