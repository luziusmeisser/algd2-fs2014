package ch.fhnw.algd2.martineberle;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T>{
Node<T> head;

public Stack(){
	this.head = new Node<T>();
	
}

@Override
public void push(Object o) {
	if(head.next == null){
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
	Node<T> tmp = new Node<T>(head.val);
	if(head.next == null){
		throw new EmptyStackException();
	}
	else{
		head = head.next;
		return (T)tmp.val;
	}
}

}
