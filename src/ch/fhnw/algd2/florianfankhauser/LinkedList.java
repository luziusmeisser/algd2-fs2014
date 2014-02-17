// Created by Florian Fankhauser on Feb 17, 2014

package ch.fhnw.algd2.florianfankhauser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	private Entry<T> currentEntry = null;
	private Entry<T> firstEntry;

	@Override
	public boolean add(T e) {
		if (currentEntry == null) {
			currentEntry = new Entry<>(e);
			firstEntry = currentEntry;
		} else {
			Entry<T> entry = new Entry<T>(e);
			currentEntry.next = entry;
			currentEntry = entry;
		}
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(this);
	}
	
	private void remove(Entry<T> element) {
		if (firstEntry == element) {
			firstEntry = firstEntry.next;
		} else {
			Entry<T> curr = firstEntry;
			while (curr.hasNext()) {
				if (curr.next == element) {
					curr.next = curr.next.next;
					return;
				}
			}
		}
	}

	private class Entry<X> {
		private X value;
		private Entry<X> next;

		private Entry(X value) {
			this.value = value;
		}

		private boolean hasNext() {
			return next != null;
		}
	}

	public class LinkedListIterator<T> implements Iterator<T> {
		private Entry<T> entry = null;
		private LinkedList<T> list;

		private LinkedListIterator(LinkedList<T> list) {
			this.list = list;
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
				entry = (Entry<T>) list.firstEntry;
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
}
