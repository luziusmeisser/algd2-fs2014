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
			while(current.pointsTo[i] != null && current.pointsTo[i].value.compareTo(item) < 0) {
				current = current.pointsTo[i];
			}
			
			updates[i] = current;
		}
		
		Element<T> tmp = new Element<T>(item);
		for (int i = 0; i < tmp.getSize(); i++) {
			tmp.pointsTo[i] = updates[i].pointsTo[i];
			updates[i].pointsTo[i] = tmp;
		}
		
	}

	@Override
	public T removeFirst() {
		if (head.pointsTo[0] == null)
			throw new NoSuchElementException();
		
		Element<T> tmp = head.pointsTo[0];
		
		int next = head.pointsTo[0].getSize();
		for (int i = 0; i < next; i++)
			if(head.pointsTo[i] == tmp)
				head.pointsTo[i] = tmp.pointsTo[i]; 
		
		return tmp.value;
	}

	@Override
	public int countStepsTo(T item) {
        Element<T> current = head;
        int count = 0;
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (current.pointsTo[i] != null && current.pointsTo[i].value.compareTo(item) < 0) {
                current = current.pointsTo[i];
                count++;
            }
        }
        return count;
	}
	
	@SuppressWarnings("unchecked")
	static class Element<T> {
		private T value;
		private Element<T>[] pointsTo;
		
		public Element(T value, int level){
			this.pointsTo = new Element[level];
            this.value = value;
		}
		
		public Element(T value) {
			int level = 1;
			while (level < MAX_LEVEL && RANDOM.nextBoolean()){
				level++;
			}
			
			this.value = value;
			this.pointsTo = new Element[level];
		}
		
		public int getSize() {
			return pointsTo.length;
		}
	}
	
	
	

	
	

	
}
