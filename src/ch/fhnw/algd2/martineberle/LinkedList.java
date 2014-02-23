// Created by Martin Eberle on 17.02.2014

package ch.fhnw.algd2.martineberle;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T>{
Node2 head = new Node2();
Node2 tail;

	@Override
	public boolean add(Object e) {
		boolean addSuccess = false;
		if(head == null){
			head = new Node2(e);
			tail = head;
			addSuccess = true;
		}
		else{
			Node2 tmp = new Node2(e);
			tmp.next = head;
			head = tmp;
			addSuccess = true;
		}
		
		return addSuccess;
	}
	
	@Override
	public Iterator<T> iterator() {
		
		
		return new Iterator<T>(){
			Node2 curr = head;
			Node2 prev;
			
			@Override
			public boolean hasNext() {
				return (curr.next != null);
				}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				if(hasNext()){
					prev = curr;
					curr = curr.next;
					return (T) curr.val;
				}
				else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				if(head != tail){
					prev.next = curr.next;
					curr = prev;
				}
				else{
					head = null;
					tail = null;
					}
			}
		};
	}

}

class Node2{
	Object val;
	Node2 next;
	
	public Node2(){
		this.val = null;
		this.next = null;
	}

	public Node2(Object o){
		this.val = o;
		this.next = null;
	}
	
}