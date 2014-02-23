// Created by Kevin Wieser on 22.02.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	
	// Head = Anker -> Mit Dummy Element
	private CollectionElement<T> head = new CollectionElement<T>(null); 
	

	@Override
	public boolean add(T e) {
		head = new CollectionElement<T>(e,head);
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private CollectionElement<T> m_current = head;
			private CollectionElement<T> m_returned;
			@Override
			public boolean hasNext() {
					return m_current.getNext() != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
					m_returned = m_current;
					m_current = m_current.getNext();
					return m_current.getValue();	// habe hier m_previous.value zurückgegeben
			}

			@Override
			public void remove() {
				if (m_returned == null) {
					throw new IllegalStateException();
				} 
					// löscht das momentane current
					m_returned.setNext(m_current.getNext());
					m_current = m_returned;
					m_returned = null;
				}
		};
	}
}
