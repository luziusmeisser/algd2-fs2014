// Created by Kevin Wieser on 22.02.2014

package ch.fhnw.algd2.kevinwieser;

public class CollectionElement<T> {
	private CollectionElement<T> next;
	private T value;
	
	public CollectionElement(T o) {
		this.value = o;
		this.next = null;
	}

	public CollectionElement(T o, CollectionElement<T> next) {
		this.value = o;
		this.next = next;
	}
	

	public CollectionElement<T> getNext() {
		return next;
	}

	public T getValue() {
		return value;
	}
	
	public void setNext(CollectionElement<T> t) {
		this.next = t;
	}
	
}