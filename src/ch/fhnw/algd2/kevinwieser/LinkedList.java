// Created by Kevin Wieser on 22.02.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	
	private Element<T> head = null; // head = anker
	private Element<T> tail = null; 
	
	@Override
	public boolean add(T e) {
		if (head == null) {
			head = new Element<T>(e,null);
			tail = head;
			return true;
		} else {
			// setze beim letzten als next den neuen Wert
			tail.setNext(new Element<T>(e,null));
			// das neue Tail ist nun getNext()
			tail = tail.getNext();
			return true;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private Element<T> current = head;
			private Element<T> previous = null;
			
			@Override
			public boolean hasNext() {
					return current.getNext() != null;
			}

			@Override
			public T next() {
				if (hasNext()) {
					previous = current;
					current = current.getNext();
					return previous.getValue();
				} else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				if (previous == null || current == null) {
					throw new IllegalStateException();
				} else {
					// löscht das momentane current
					previous.setNext(current.getNext());
					current = previous;
					previous = null;
				}
				
			}	
		};
	}


}
