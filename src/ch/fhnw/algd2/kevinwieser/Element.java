// Created by Kevin Wieser on 22.02.2014

package ch.fhnw.algd2.kevinwieser;

public class Element<T> {
	private Element<T> next;
	private T value;

	public Element(T o, Element<T> next) {
		this.value = o;
		this.next = next;
	}

	public Element<T> getNext() {
		return next;
	}

	public T getValue() {
		return value;
	}
	
	public void setNext(Element<T> t) {
		this.next = t;
	}
	
}