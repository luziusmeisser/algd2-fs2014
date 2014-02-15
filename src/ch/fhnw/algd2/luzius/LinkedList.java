// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.luzius;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

	private Entry root = new Entry(null);
	
	@Override
	public boolean add(T e) {
		root.value = e;
		root = new Entry(root);
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Entry prev;
			private Entry current = root;

			public boolean hasNext() {
				return current.next != null;
			}

			public T next() {
				if (hasNext()) {
					prev = current;
					current = current.next;
					return current.value;
				} else {
					throw new NoSuchElementException();
				}
			}

			public void remove() {
				if (prev == null){
					throw new IllegalStateException();
				} else {
					prev.next = current.next;
					current = prev;
					prev = null;
				}
			}
		};
	}

	class Entry {
		Entry next;
		T value;
		public Entry(Entry root) {
			this.next = root;
		}
	}

}
