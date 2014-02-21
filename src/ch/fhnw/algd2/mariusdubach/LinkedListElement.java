//Created by Marius Dubach 17.02.2014

package ch.fhnw.algd2.mariusdubach;

public class LinkedListElement<T> {
	
	public T payload;
	public LinkedListElement nextElement;
	
	public LinkedListElement(T e){
		this.payload = e;
	}

}
