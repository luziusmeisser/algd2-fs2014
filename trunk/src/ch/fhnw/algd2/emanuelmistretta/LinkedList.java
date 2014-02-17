// Created by Emanuel Mistretta on 17.02.2014

package ch.fhnw.algd2.emanuelmistretta;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

    private Item head = new Item(null);

    @Override
    public boolean add(T e) {
	head.value = e; //set previous item
	head = new Item(head); //set new head
	return true;
    }

    @Override
    public Iterator<T> iterator() {
	return new Iterator<T>() {

	    private Item previous;
	    private Item current = head;

	    public boolean hasNext() {
		return current.next != null;
	    }

	    public T next() {
		if (!hasNext())
		    throw new NoSuchElementException();
		
		previous = current;
		current = current.next;
		return current.value;
	    }

	    public void remove() {
		if (previous == null)
		    throw new IllegalStateException();

		previous.next = current.next;
		current = previous;
		previous = null;
	    }
	};
    }

    class Item {
	Item next;
	T value;

	public Item(Item head) {
	    this.next = head;
	}
    }
}
