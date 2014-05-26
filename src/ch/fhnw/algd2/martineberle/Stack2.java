// Created by Martin Eberle on 11.04.2014

package ch.fhnw.algd2.martineberle;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack2<T> implements IStack<T>{
	Node4 head;
	
	@Override
	public void push(T o) {
		if(head.next == null){
			head = new Node4(o);
		}
		else{
			head = new Node4(o, head);
		}
		
	}

	@Override
	public T pop() throws EmptyStackException {
		Node4 temp = head;
		head = head.next;
		return (T)temp.val;
	}

}
class Node4{
	Object val;
	Node4 next;
	public Node4(Object o){
		this.val = o;
		this.next = null;
	}
	public Node4(Object o, Node4 next){
		this.val = o;
		this.next = next;
	}
}
