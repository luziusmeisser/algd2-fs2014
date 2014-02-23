package ch.fhnw.algd2.larskessler;

import java.util.Iterator;
import java.util.NoSuchElementException;
import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	
	private LinkedListElement<T> first = null;
	private LinkedListElement<T> last = null;
	
	private int size = 0;

	@Override
	public boolean add(T e) {
		// create new list element
		LinkedListElement<T> next = new LinkedListElement<T>(e);
		
		// if this is the first element, set first and last to next
		// else push element to the end of the list
		// increment size anyway
		if(first == null) {
			first = next;
			last = next;
		} else {
			last.next = next;
			last = next;
		}
		
		this.size++;
		return true;
	}
	
	public int size() {
		return this.size;
	}
	
	public void decrement() {
		this.size--;
	}
	
	public boolean isEmpty() {
		return (first == null) && (last == null); 
	}
	
	public void clear() {
		this.first = null;
		this.last = null;
	}

	@Override
	public Iterator<T> iterator() {
		// create new iterator
		return new Iterator<T>() {
			
			private LinkedListElement<T> prev = null;
			private LinkedListElement<T> current = null;
			private LinkedListElement<T> next = first;
			
			// go to next element and return the value of the new current element
			public T next() {
				if(this.hasNext() == true) {
					prev = current;
					current = next;
					next = current.next;
					return current.value;
				} 
				throw new NoSuchElementException();			
			}
			
			public void remove() {
				// nothing to remove if list is empty
				// else there was only one element
				if (current == null) {
					throw new IllegalStateException("Nothing to remove...");
				} else if(current.prev == null) {
					first = current.next;
					decrement();
				} else {
					prev.next = next;
					decrement();
				}
				current = null;
			}
			
			public boolean hasNext() {
				return next != null;
			}
		};
	}
	
	class LinkedListElement<T> {
		protected LinkedListElement<T> prev;
		protected LinkedListElement<T> next;
		protected T value;
		
		// constructor saves reference in value
		public LinkedListElement(T value) {
			this.value = value;
		}
		
		// returns true if there is a next element
		public boolean hasNext() {
			return this.next != null;
		}
		
		public boolean hasPrev() {
			return this.prev != null;
		}
	}
}
