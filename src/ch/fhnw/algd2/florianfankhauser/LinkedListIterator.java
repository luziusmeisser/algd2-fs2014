// Created by Florian Fankhauser on Feb 17, 2014

package ch.fhnw.algd2.florianfankhauser;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<T> implements Iterator<T> {
	private LinkedListEntry<T> entry = null;
	private LinkedList<T> list;

	protected LinkedListIterator(LinkedList<T> linkedList) {
		this.list = linkedList;
	}

	@Override
	public boolean hasNext() {
		if (entry != null) {
			return entry.hasNext();
		}
		return false;
	}

	@Override
	public T next() {
		if (entry == null) {
			entry = (LinkedListEntry<T>) list.firstEntry;
		} else if (entry.next == null) {
			throw new NoSuchElementException();
		} else {
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
