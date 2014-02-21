//Created by Marius Dubach 17.02.2014

package ch.fhnw.algd2.mariusdubach;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.*;

public class LinkedList<T> extends AbstractLinkedList<T>{
	
	private LinkedListElement<T> first = new LinkedListElement<T>(null);
	private LinkedListElement<T> prev;
	private int size=0;
	private boolean same;
 	

	@Override
	public boolean add(T e) {
		if(first != null){
			LinkedListElement<T> tmp = first;
			while(tmp.nextElement != null){
				tmp = tmp.nextElement;
			}
			tmp.nextElement = new LinkedListElement<T>(e);
		}else{
			first = new LinkedListElement<T>(e);
		}
		size++;
		return true;
	}
	
	@Override
	public int size(){
		return size;
	}

	@Override
	public Iterator<T> iterator() {
				
		return new Iterator<T>() {			

			private LinkedListElement<T> sto = first;

			@Override
			public boolean hasNext() {
				if(first != null){						
					return sto.nextElement != null;
				}
				return false;
			}

			@Override
			public T next() {
				if(sto.nextElement != null){
					prev = sto;
					sto = sto.nextElement;
					return sto.payload;
				}else{
					throw new NoSuchElementException();
				}
				
			}

			@Override
			public void remove() {
				if (prev == null){
					throw new IllegalStateException();
				} else {
					prev.nextElement = sto.nextElement;
					sto = prev;
					prev = null;
					size--;
				}
			}
		};
	}

}
