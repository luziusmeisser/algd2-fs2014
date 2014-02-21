//Created by Marius Dubach 17.02.2014

package ch.fhnw.algd2.mariusdubach;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.*;

public class Stack<T> implements IStack<T>{
	
	private int lastObjectPosition=-1;
	private Object[] storage = new Object[10000];
	
	private void resizeArrayIfNecessary(){
		if(lastObjectPosition == storage.length-1){
			Object[] newArr = new Object[storage.length+10000];
			for(int i=0; i<storage.length; i++){
				newArr[i] = storage[i];
			}
			storage = newArr;
		}
	}

	@Override
	public void push(T o) {
		lastObjectPosition++;
		storage[lastObjectPosition] = o;
		resizeArrayIfNecessary();		
	}

	@Override
	public T pop() throws EmptyStackException {
		if(lastObjectPosition == -1)throw new EmptyStackException();
		T tmp = (T) storage[lastObjectPosition];
		lastObjectPosition--;
		return tmp;
	}
	
	

}
