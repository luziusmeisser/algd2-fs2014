package ch.fhnw.algd2.larskessler;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
	
	// save reference in stack
	private StackElement top = null;

	@Override
	public void push(T o) {
		// add element
		top = new StackElement(top, o);
	}

	@Override
	public T pop() throws EmptyStackException {
		// return top element
		if(isEmpty() == true) {
			throw new EmptyStackException();
		} else {
			T toReturn = top.value;
			this.top = this.top.prev;
			
			return toReturn;
		}
	}
	
	public boolean isEmpty() {
		return this.top == null;
	}
	
	// inner class StackElement
	class StackElement {
		protected StackElement prev;
		protected T value;
		
		// constructor
		public StackElement(StackElement element, T value) {
			// set StackElement to previous Element
			this.prev = element;
			
			// set value to new value
			this.value = value;
		}
	}
	
}