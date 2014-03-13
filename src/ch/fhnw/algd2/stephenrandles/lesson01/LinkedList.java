// Created by Stephen Randles 17.02.2014

package ch.fhnw.algd2.stephenrandles.lesson01;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	private LinkedListItem<T> start = null;
	private LinkedListItem<T> end = null;

	@Override
	public boolean add(T e) {
		LinkedListItem<T> next = new LinkedListItem<T>(e);

		if (start == null) {
			start = next;
			end = next;
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
			LinkedListItem<T> current = null;
			LinkedListItem<T> next = start;
			
			@Override
			public boolean hasNext() {
				return (next != null);
			}

			@Override
			public T next() {
				if (this.hasNext()) {
					previous = current;
					current = next;
					next = current.getNext();
					return current.getContents();
				}
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				if (current == null){
					throw new IllegalStateException("remove() has been called before next() or has already been called on the current item.");
				}
				if (previous == null) {
					start = current.getNext();
				} else {
					previous.setNext(next);
				}
				current = null;
			}
			
		};
	}
	
	class LinkedListItem<E> {
		private LinkedListItem<E> next;
		private E contents;

		public LinkedListItem(E contents) {
			this(contents, null);
		}
		
		public LinkedListItem(E contents, LinkedListItem<E> next) {
			this.contents = contents;
			this.next = next;
		}
		
		public E getContents() {
			return contents;
		}
		
		public LinkedListItem<E> getNext() {
			return next;
		}
		
		public void setContents(E contents) {
			this.contents = contents;
		}
		
		public void setNext(LinkedListItem<E> next) {
			this.next = next;
		}

	}
	
}
