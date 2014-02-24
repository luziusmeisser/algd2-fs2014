// Created by Florian Fankhauser on Feb 17, 2014

package ch.fhnw.algd2.florianfankhauser.lesson1;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
	private Entry<T> currentEntity = null;

	@Override
	public void push(T o) {
		currentEntity = new Entry<T>(o, currentEntity);
	}

	@Override
	public T pop() throws EmptyStackException {
		if (currentEntity == null) {
			throw new EmptyStackException();
		} else {
			T value = currentEntity.value;
			currentEntity = currentEntity.previous;
			return value;
		}
	}
	
	private class Entry<X> {
		private X value;
		private Entry<X> previous;
		
		private Entry(X value, Entry<X> previous) {
			this.value = value;
			this.previous = previous;
		}
	}
	
}