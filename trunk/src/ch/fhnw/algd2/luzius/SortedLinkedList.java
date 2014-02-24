// Created by Luzius on 17.02.2014

package ch.fhnw.algd2.luzius;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;
import ch.fhnw.algd2.lesson1.exercise.AbstractSortedLinkedList;

public class SortedLinkedList<T extends Comparable<T>> extends AbstractSortedLinkedList<T> {

	private Entry root = null;

	@Override
	public void merge(AbstractLinkedList<T> other) {
		if (other instanceof AbstractSortedLinkedList<?>) {
			AbstractSortedLinkedList<T> o = (AbstractSortedLinkedList<T>) other;
			Entry prev = null;
			Entry current = root;
			for (T elem : o) {
				if (root == null) {
					root = new Entry(null, elem);
					prev = root;
					current = root.next;
				} else {
					while (current != null && current.value.compareTo(elem) < 0) {
						prev = current;
						current = current.next;
					}
					Entry add = new Entry(current, elem);
					if (prev == null) {
						root = add;
					} else {
						prev.next = add;
					}
					prev = add;
				}
			}
		} else {
			SortedLinkedList<T> res = mergeUnsorted(other);
			merge(res);
		}
	}

	private SortedLinkedList<T> mergeUnsorted(AbstractLinkedList<T> other) {
		ArrayList<SortedLinkedList<T>> lists = new ArrayList<>();
		for (T elem : other) {
			SortedLinkedList<T> sll = new SortedLinkedList<>();
			sll.add(elem);
			lists.add(sll);
		}
		while (lists.size() >= 2) {
			ArrayList<SortedLinkedList<T>> nextList = new ArrayList<>();
			for (int i = 0; i < lists.size(); i += 2) {
				if (i == lists.size() - 1) {
					nextList.add(lists.get(i));
				} else {
					SortedLinkedList<T> l1 = lists.get(i);
					SortedLinkedList<T> l2 = lists.get(i + 1);
					l1.merge(l2);
					nextList.add(l1);
				}
			}
			lists = nextList;
		}
		SortedLinkedList<T> res = lists.get(0);
		return res;
	}

	@Override
	public boolean add(T e) {
		if (root == null || e.compareTo(root.value) < 0) {
			root = new Entry(root, e);
		} else {
			Entry prev = root;
			Entry current = root.next;
			while (true) {
				if (current == null || e.compareTo(current.value) < 0) {
					prev.next = new Entry(current, e);
					break;
				} else {
					prev = current;
					current = current.next;
				}
			}
		}
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Entry prev = null;
			private Entry current = root;
			private Entry prevprev = null;

			public boolean hasNext() {
				return current != null;
			}

			public T next() {
				if (hasNext()) {
					T v = current.value;
					this.prevprev = prev;
					this.prev = current;
					this.current = current.next;
					return v;
				} else {
					throw new NoSuchElementException();
				}
			}

			public void remove() {
				if (prev == null) {
					throw new IllegalStateException();
				} else if (prevprev == null) {
					root = current;
				} else {
					prevprev.next = current;
					prev = null;
				}
			}
		};
	}

	class Entry {
		Entry next;
		T value;

		public Entry(Entry root, T value) {
			this.next = root;
			this.value = value;
		}

	}

}
