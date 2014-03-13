// Created by Stephen Randles 17.02.2014

package ch.fhnw.algd2.stephenrandles.lesson01;

import java.util.Arrays;
import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class StackWithArray<T> implements IStack<T> {
	final private int DEFAULT_SIZE = 100;
	final private double GROWTH_RATE = 2;
	
	private Object[] stack;
	private int pointer;
	
	public StackWithArray() {
		stack = new Object[DEFAULT_SIZE];
	}
	
	@Override
	public void push(T o) {
		pointer++;
		if (pointer == stack.length) {
			increaseStackSize();
		}		
		stack[pointer] = o;
	}

	@Override
	public T pop() throws EmptyStackException {
		if (pointer == 0 && stack[0] == null) {
			throw new EmptyStackException();
		}

		return (T)stack[pointer--];
	}
	
	private void increaseStackSize() {
		int newSize = (int)(stack.length * GROWTH_RATE);
		stack = Arrays.copyOf(stack, newSize);
	}
	
	
}
