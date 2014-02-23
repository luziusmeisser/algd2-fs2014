// Created by Marco on 22.02.2014

package ch.fhnw.algd2.marcogaiffi;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

	private Node first = new Node(null);

	@Override
	public boolean add(T e) {
		Node newone = new Node(e);
		newone.next = first.next;
		first.next = newone;
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node previous;
			Node current = first;

			public boolean hasNext() {
				return current.next != null;
			}

			@Override
			public T next() {
				if (!hasNext())
					throw new NoSuchElementException();
				previous = current;
				current = current.next;
				return current.value;
			}

			@Override
			public void remove() {
				if (previous == null)
					throw new IllegalStateException();
				previous.next = current.next;
				current = previous;
				previous = null;
			}
		};
	}

	private class Node {
		T value;
		Node next = null;

		public Node(T e) {
			this.value = e;
		}
	}
}
