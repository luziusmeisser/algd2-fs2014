// Created by Kevin Wieser on 22.02.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	
	// Head = Anker -> Mit Dummy Element
	private Element<T> head = new Element(null,null); 
	
	@Override
	public boolean add(T e) {
		head = new Element<T>(e, head);
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private Element<T> m_current = head;
			private Element<T> m_returned = null;
			
			@Override
			public boolean hasNext() {
					if (m_current != null) {
					return m_current.getNext() != null;
					} else {
						return false;
					}
			}

			@Override
			public T next() {
				if (hasNext()) {
					m_returned = m_current;
					m_current = m_current.getNext();
					return m_returned.getValue();
				} else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				if (m_returned == null || m_current == null) {
					throw new IllegalStateException();
				} else {
					// löscht das momentane current
					m_returned.setNext(m_current.getNext());
					m_current = m_returned;
					m_returned = null;
				}
				
			}	
		};
	}


}
