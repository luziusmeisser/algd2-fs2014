// Created by Stephen Randles 17.02.2014

package ch.fhnw.algd2.stephenrandles;

import java.util.Iterator;
import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	private LinkedListItem<T> start = null;
	private LinkedListItem<T> end = null;

	@Override
	public boolean add(T e) {
		LinkedListItem<T> next = new LinkedListItem<T>(e);

		if (start == null) {
			start = next;
		} else {
			end.setNext(next);
			end = next;
		}
		
		return true;
	}

	@Override
	public Iterator<T> iterator() {		
		return new Iterator<T>() {
			LinkedListItem<T> previous = null;
			LinkedListItem<T> current = start;
			
			@Override
			public boolean hasNext() {	
				return (current.getNext() != null);
			}

			@Override
			public T next() {
				if (this.hasNext()) {
					previous = current;
					current = current.getNext();
				}
				return current.getContents();
			}

			@Override
			public void remove() {
				previous.setNext(current.getNext());
				current = previous;
			}
			
		};
	}
	

}
