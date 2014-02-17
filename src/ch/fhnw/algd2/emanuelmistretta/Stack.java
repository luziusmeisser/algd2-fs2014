// Created by Emanuel Mistretta on 17.02.2014

package ch.fhnw.algd2.emanuelmistretta;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {

    private StackEntry currentEntry = null;

    @Override
    public void push(T o) {
	currentEntry = new StackEntry(currentEntry, o);
    }

    @Override
    public T pop() throws EmptyStackException {
	if (currentEntry == null) {
	    throw new EmptyStackException();
	}
	
	StackEntry result = currentEntry;
	currentEntry = currentEntry.previous;
	return result.value;
    }

    public class StackEntry {
	StackEntry previous;
	T value;

	public StackEntry(StackEntry current, T o) {
	    this.previous = current;
	    this.value = o;
	}
    }

}
