// Created by Stephen Randles 17.02.2014

package ch.fhnw.algd2.stephenrandles;
import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class StackWithNodes<T> implements IStack<T> {
	private StackItem top;
	
	public StackWithNodes() {
	}
	
	@Override
	public void push(T o) {
		top = new StackItem(o, top);
	}

	@Override
	public T pop() throws EmptyStackException {
		if (top == null) {
			throw new EmptyStackException();
		}
		T contents = top.getContents();
		top = top.getPrevious();
		return contents;
	}
	
	private class StackItem {
		private T contents;
		private StackItem previous;
		
		public StackItem(T contents, StackItem previous) {
			this.contents = contents;
			this.previous = previous;
		}
		
		public T getContents() {
			return this.contents;
		}
		
		public StackItem getPrevious() {
			return this.previous;
		}
	}
	
}
