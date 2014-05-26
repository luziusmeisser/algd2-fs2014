// Created by Martin Eberle on 11.04.2014

package ch.fhnw.algd2.martineberle;

import java.util.Iterator;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList2<T> extends AbstractLinkedList<T> {
	Node3 head;

	@Override
	public boolean add(T e) {
		if(this.head == null){
			head = new Node3(e);
			return true;
		}
		else if(head != null){
			this.head = new Node3(e, head);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>(){
			Node3 curr = head;
			Node3 prev = null;
			
			@Override
			public boolean hasNext() {
				if(curr != null && curr.next != null){
					return true;
				}
				else if(curr.next == null || curr == null){
					return false;
				}
				return false;
			}
			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				if(hasNext()){
					prev = curr;
					curr = curr.next;
				}
				return (T) curr.val;
			}
			@Override
			public void remove() {
				if(hasNext()){
					prev.next = curr.next;
				}
				else {
					prev.next = null;
				}
				
			}
		};
	}

}
class Node3{
	Object val;
	Node3 next;
	public Node3(){
		this.val = null;
		this.next = null;
	}
	public Node3(Object o){
		this.val = o;
		this.next = null;
	}
	public Node3(Object o, Node3 head){
		this.val = o;
		this.next = head;
	}
}
