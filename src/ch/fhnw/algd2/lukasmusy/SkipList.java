//created by lukas.musy on Feb 24, 2014

package ch.fhnw.algd2.lukasmusy;

import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {
	private final static int MAX_LEVEL = 20;
	private final static Random RANDOM = new Random();

	private final Element<T> head = new Element<T>(null, MAX_LEVEL);
	
	@SuppressWarnings("unchecked")
	@Override
	public void add(T item) {
		Element<T>[] updates = new Element[MAX_LEVEL];
		Element<T> current = head;
		
		for(int i = MAX_LEVEL - 1; i >= 0; i--){
			while(current.pointers[i] != null && current.pointers[i].value.compareTo(item) < 0) {
				current = current.pointers[i];
			}			
			updates[i] = current;
		}
		
		Element<T> tmp = new Element<T>(item);
		for (int i = 0; i < tmp.getSize(); i++) {
			tmp.pointers[i] = updates[i].pointers[i];
			updates[i].pointers[i] = tmp;
		}
		
	}

	@Override
	public T removeFirst() {
		if (head.pointers[0] == null)
			throw new NoSuchElementException();
		
		Element<T> tmp = head.pointers[0];
		
		int next = head.pointers[0].getSize();
		for (int i = 0; i < next; i++)
			if(head.pointers[i] == tmp)
				head.pointers[i] = tmp.pointers[i]; 
		
		return tmp.value;
	}

	@Override
	public int countStepsTo(T item) {
        Element<T> current = head;
        int count = 0;
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (current.pointers[i] != null && current.pointers[i].value.compareTo(item) < 0) {
                current = current.pointers[i];
                count++;
            }
        }
        return count;
	}
	
	@SuppressWarnings("unchecked")
	static class Element<T> {
		private T value;
		private Element<T>[] pointers;
		
		public Element(T value, int level){
			this.pointers = new Element[level];
            this.value = value;
		}
		
		public Element(T value) {
			int level = 1;
			while (level < MAX_LEVEL && RANDOM.nextBoolean()){
				level++;
			}
			
			this.value = value;
			this.pointers = new Element[level];
		}
		
		public int getSize() {
			return pointers.length;
		}
	}
	
	
	

	
	

	
}
