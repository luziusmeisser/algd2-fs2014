// created by lukas.musy on 17.02.2014

package ch.fhnw.algd2.lukasmusy;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
	private Element<T> current = null;
	
	@Override
	public void push(T o) {
		current = new Element<T>(o, current);
		
	}

	@Override
	public T pop() throws EmptyStackException {

			if(current!=null) {
				Element<T> tmp = current; 
				current = tmp.getNext();
				return tmp.getElement();
			}
			else {
				throw new EmptyStackException();
			}
				
	}
	
}

class Element<T> {
	private T value;
	private Element<T> next;
	
	public Element(T o, Element<T> e) {
		this.value = o;
		this.next = e;
	}
	
	public Element<T> getNext() {
		return next;
	}

	public void setNext(Element<T> e) {
		this.next = e;
	}

	public T getElement() {
		return value;
	}

	public void setElement(T e) {
		this.value = e;
	}
	

	
	
}
