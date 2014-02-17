// Created by Martin Eberle on 17.02.2014

package ch.fhnw.algd2.martineberle;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T>{
Node<T> head;

public Stack(){
	
}

@Override
public void push(Object o) {
	if(head == null){
		this.head = new Node<T>(o);
	}
	else{
		Node<T> tmp = new Node<T>(o);
		tmp.next = head;
		head = tmp;
	}
}

@Override
public T pop() throws EmptyStackException {
	if(head == null){
		throw new EmptyStackException();
	}
	Node<T> tmp = new Node<T>(head.val);
//	if(head.next == null){
//		throw new EmptyStackException();
//	}
	head = head.next;
	return (T)tmp.val;
	}
}
