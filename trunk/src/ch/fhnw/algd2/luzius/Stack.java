// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.luzius;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack implements IStack {

	private Entry current;

	public void push(Object o) {
		current = new Entry(current, o);
	}

	public Object pop() {
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
		Object value;

		public Entry(Entry current, Object o) {
			this.prev = current;
			this.value = o;
		}
	}

}
