// Created by Kevin Wieser on 17.02.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class MyStack<T> implements IStack<T> {

	private CollectionElement<T> current = null;

	@Override
	public void push(T o) {
		current = new CollectionElement<T>(o, current);
	}

	@Override
	public T pop() throws EmptyStackException {

		if (current == null) {
			throw new EmptyStackException();
		}

		CollectionElement<T> old_element = current;

		if (current.getNext() == null) {
			current = null;
		} else {
			current = current.getNext();
		}

		return (T) old_element.getValue();

	}
}
