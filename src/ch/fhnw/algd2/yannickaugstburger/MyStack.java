// Created by Yannick Augstburger on Feb 17, 2014

package ch.fhnw.algd2.yannickaugstburger;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class MyStack<T> implements IStack<T> {
	
	private Element top;
	
	class Element{
		Element previous;
		T value;

		Element(Element top, T o) {
			this.previous = top;
			this.value = o;
		}
	}
	
	@Override
	public void push(T o) {
		top = new Element(top, o);
		
	}

	@Override
	public T pop() throws EmptyStackException {
		if (top == null) {
			throw new EmptyStackException();
		} else {
			try {
				return top.value;
			} finally {
				this.top = top.previous;
			}
		}
		
	}

}
