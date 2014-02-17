// Created by Stephen Randles 17.02.2014

package ch.fhnw.algd2.stephenrandles;

public class LinkedListItem<T> {
	private LinkedListItem<T> next;
	private T contents;

	public LinkedListItem(T contents) {
		this(contents, null);
	}
	
	public LinkedListItem(T contents, LinkedListItem<T> next) {
		this.contents = contents;
		this.next = next;
	}
	
	public T getContents() {
		return contents;
	}
	
	public LinkedListItem<T> getNext() {
		return next;
	}
	
	public void setContents(T contents) {
		this.contents = contents;
	}
	
	public void setNext(LinkedListItem<T> next) {
		this.next = next;
	}

}
