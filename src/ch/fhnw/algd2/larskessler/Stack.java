package ch.fhnw.algd2.larskessler;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
	
	private Element element;
	
	@Override
	public void push(T o) {
		// push element to list
		element = new Element(element, o);
	}

	@Override
	public T pop() throws EmptyStackException {
		// check if stack is popable
		if(element == null) {
			throw new EmptyStackException();
		}
		
		T tmp = element.current;
		this.element = element.last;
		return tmp;
	}

	public class Element {
		
		Element last;
		T current;

		public Element(Element current, T next) {
			this.last = current;
			this.current = next;
		}
	}
}