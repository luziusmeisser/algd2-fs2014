// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.luzius;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {

	private Entry current;

	public void push(T o) {
		current = new Entry(current, o);
	}

	public T pop() {
		if (current == null) {
			throw new EmptyStackException();
		} else {
			try {
				return current.value;
			} finally {
				this.current = current.prev;
			}
		}
	}

	class Entry {
		Entry prev;
		T value;

		Entry(Entry current, T o) {
			this.prev = current;
			this.value = o;
		}
	}

}
