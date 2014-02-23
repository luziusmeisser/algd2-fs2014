// Created by Kevin Wieser on 22.02.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	
	// Head = Anker -> Mit Dummy Element
	private Element head = new Element(null); 
	
	
	class Element {
    	Element next;
    	T value;

    	public Element(Element head) {
    		this.next = head;
    	}
    }
	
	
	@Override
	public boolean add(T e) {
		head = new Element(head);
		head.value = e;
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private Element m_current = head;
			private Element m_returned = null;
			
			@Override
			public boolean hasNext() {
					return m_current.next != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
					m_returned = m_current;
					m_current = m_current.next;
					return m_returned.value;	
			}

			@Override
			public void remove() {
				if (m_returned == null) {
					throw new IllegalStateException();
				} 
					// l�scht das momentane current
					m_returned.next = m_current.next;
					m_current = m_returned;
					m_returned = null;
				}
		};
	}


}
