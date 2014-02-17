// Created by Kevin Wieser on 17.02.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class MyStack<T> implements IStack<T> {

	private Element current = null;

	private class Element<T> {
		private Element next;
		private T value;

		public Element(T o, Element next) {
			this.value = o;
			this.next = next;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public void setNext(Element next) {
			this.next = next;
		}

		public Element getNext() {
			return next;
		}

		public T getValue() {
			return value;
		}
	}

	@Override
	public void push(T o) {
		current = new Element(o, current);
	}

	@Override
	public T pop() throws EmptyStackException {

		if (current == null) {
			throw new EmptyStackException();
		}

		Element tmp = current.getNext();
		Element tmp2 = current;

		if (tmp == null) {
			current = null;
		} else {
			current = tmp;
		}

		return (T) tmp2.getValue();

	}
}
