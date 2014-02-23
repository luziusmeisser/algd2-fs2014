// created by lukas.musy on 17.02.2014

package ch.fhnw.algd2.lukasmusy;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
	
	private LLElement<T> top = new LLElement<T>(null);

	@Override
	public boolean add(T e) {
		LLElement<T> tmp = new LLElement<T>(e);
		tmp.setNext(top.getNext()); 
		top.setNext(tmp);
		
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>(){
			private LLElement<T> current = top;
			private LLElement<T> prev = null;
			
			@Override
            public boolean hasNext() {
                return current.getNext() != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                prev = current;
                current = current.getNext();
                return current.getElement();
            }

            @Override
            public void remove() {
                if (prev == null) {
                    throw new IllegalStateException();
                } else {
                    prev.setNext(current.getNext());
                    current = prev;
                    prev = null;
                }
            }
			
		};
	}
	
}
	
	
	class LLElement<T> {
		private T value;
		private LLElement<T> next = null;
		
		public LLElement(T e) {
			this.value = e;
			
		}
		
		public LLElement<T> getNext() {
			return next;
		}

		public void setNext(LLElement<T> e) {
			this.next = e;
		}

		public T getElement() {
			return value;
		}

		public void setElement(T e) {
			this.value = e;
		}
		
	

}
