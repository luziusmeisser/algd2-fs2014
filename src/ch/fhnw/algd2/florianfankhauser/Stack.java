// Created by Florian Fankhauser on Feb 17, 2014

package ch.fhnw.algd2.florianfankhauser;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
	private Entity<T> currentEntity = null;

	@Override
	public void push(T o) {
		currentEntity = new Entity<T>(o, currentEntity);
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
	
	private class Entity<X> {
		private X value;
		private Entity<X> previous;
		
		private Entity(X value, Entity<X> previous) {
			this.value = value;
			this.previous = previous;
		}
	}
	
}