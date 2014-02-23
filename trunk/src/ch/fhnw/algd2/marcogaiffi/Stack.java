// Created by Marco on 17.02.2014

package ch.fhnw.algd2.marcogaiffi;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T>{

	Entry<T> current = null;
	
	@Override
	public void push(T o) {
		Entry<T> entry = new Entry<T>(o);
		entry.next = current;
		current = entry;
	}

	@Override
	public T pop() throws EmptyStackException {
		if(current == null){
			throw new EmptyStackException();
		}else{
			try{
				return current.value;
			}finally{
				current = current.next;
			}
		}
	}
	
	@SuppressWarnings("hiding")
	private class Entry<T>{
		private Entry<T> next;
		private T value;
		
		public Entry(T value){
			this.value = value;
		}
	}

}
