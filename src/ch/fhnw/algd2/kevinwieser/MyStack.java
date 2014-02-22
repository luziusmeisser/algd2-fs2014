// Created by Kevin Wieser on 17.02.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class MyStack<T> implements IStack<T> {

	private Element<T> current = null;

	private class Element<T> {
		private Element<T> next;
		private T value;

		public Element(T o, Element<T> next) {
			this.value = o;
			this.next = next;
		}

		public Element<T> getNext() {
			return next;
		}

		public T getValue() {
			return value;
		}
	}

	@Override
	public void push(T o) {
		current = new Element<T>(o, current);
	}

	@Override
	public T pop() throws EmptyStackException {

		if (current == null) {
			throw new EmptyStackException();
		}

		Element<T> old_element = current;

		if (current.getNext() == null) {
			current = null;
		} else {
			current = current.getNext();
		}

		return (T) old_element.getValue();

	}
}
