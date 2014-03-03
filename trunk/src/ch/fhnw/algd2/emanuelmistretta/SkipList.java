// Created by Marco on 24.02.2014
// Groupwork Marco Gaiffi & Emanuel Mistretta

package ch.fhnw.algd2.emanuelmistretta;

import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {

	final static int MaxLevel = 20;
	private Element<T> head = new Element<T>(MaxLevel, null);

	//add new element
	@SuppressWarnings("unchecked")
	@Override
	public void add(T item) {
		Element<T>[] updatearr = new Element[MaxLevel];
		Element<T> temp = head;
		
		if (head == null) {
			head = new Element<T>(MaxLevel, item);
		}else{
			//write update array content
		    	//Go through each level and each item on level
			for(int i = MaxLevel -1; i>=0; i--){
				while(temp.neighbors[i] != null && temp.neighbors[i].value.compareTo(item) < 0){
					temp = temp.neighbors[i];
				}
				updatearr[i] = temp;
			}
			//insert new element and set neighbors according to update array
			Element<T> newelement = new Element<T>(item);
			for(int i = 0; i< newelement.getLevel(); i++){
				newelement.neighbors[i] = updatearr[i].neighbors[i];
				updatearr[i].neighbors[i] = newelement;
			}
		}
	}

	@Override
	public T removeFirst() {
		if(head.neighbors[0] == null){
			throw new NoSuchElementException();
		}
		Element<T> temp = head.neighbors[0];
		int count = head.neighbors[0].getLevel();
		for (int i = 0; i < count; i++){
			if(head.neighbors[i] == temp){
				head.neighbors[i] = temp.neighbors[i];
			}
		}
		return temp.value;
	}

	@Override
	//1. Goes through each level
	//2. Checks if items is on level (while) -> updates counter
	public int countStepsTo(T item) {
		Element<T> temp = head;
		int counter = 0;
		for(int i = MaxLevel - 1; i>=0; i--){
			while(temp.neighbors[i] != null && temp.neighbors[i].value.compareTo(item) < 0){
				temp = temp.neighbors[i];
				counter++;
			}
		}
		return counter;
	}

	private static class Element<T> {
		private T value;
		private Element<T>[] neighbors;

		@SuppressWarnings("unchecked")
		private Element(T value) {
			this.value = value;
			int level = calculateLevel();
			this.neighbors = new Element[level];
		}
		
		@SuppressWarnings("unchecked")
		//Const for diff levels
		private Element(int level, T value) {
			this.value = value;
			this.neighbors = new Element[level];
		}

		//generate random level <= MaxLevel
		private int calculateLevel() {
			Random rand = new Random();
			int level = 0;
			boolean coin = true;
			while (coin && level <= MaxLevel) {
				level++;
				coin = rand.nextBoolean();
			}
			return level;
		}
		
		private int getLevel() {
			return neighbors.length - 1;
		}

	}

}
