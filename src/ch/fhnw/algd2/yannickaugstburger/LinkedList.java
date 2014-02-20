// Created by Yannick Augstburger on Feb 20, 2014
package ch.fhnw.algd2.yannickaugstburger;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T>{
	
	private Element head= new Element(null);
	
	class Element {
    	Element next;
    	T value;

    	public Element(Element head) {
    		this.next = head;
    	}
    }
    @Override
    public boolean add(T e) {
    	head.value = e; 
    	head = new Element(head);
    	return true;
    }

    @Override
    public Iterator<T> iterator() {
    	return new Iterator<T>() {
    		
    		private Element curr = head;
    		private Element prev;
    		
    		@Override
    		public boolean hasNext() {
    			return curr.next != null;
    		}

    		@Override
    		public T next() {
    			if (!hasNext()){
    				throw new NoSuchElementException();
    			}
    			prev = curr;
    			curr = curr.next;
    			return curr.value;
    		}	

    		@Override
    		public void remove() {
    			if (prev == null){
    				throw new IllegalStateException();
    			}
    			prev.next = curr.next;
    			curr = prev;
    			prev = null;
    		}
    	};
    }
}
