// Created by Martin Eberle on 24.03.2014

package ch.fhnw.algd2.martineberle;

import java.util.ArrayList;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap{
	public String[] speicher;
	public int index;
	public Heap(){
		speicher = new String[1000];
		index = 0;
	}

	@Override
	public synchronized void offer(String s) {
		speicher[index] = s;
		index++;
		siftUp(index-1);
	}

	@Override
	public String peek() {
		if(index == 0){
		return null;
		}
		else {
			return speicher[0];
		}
	}

	@Override
	public synchronized String poll() {
		if(index == 0){
			return null;
		}
		String temp = speicher[0];
		index--;
		speicher[0] = speicher[index];
		siftDown(0);
		return temp;
		
		
	}
	
	public synchronized int getLeftChild(int index){
	return (index * 2 + 1);
	}
	
	public synchronized int getRightChild(int index){
	return (index * 2 + 2);
	}
	
	public synchronized void siftUp(int childIndex){
		int parentIndex = (childIndex-1)/2;
		if(parentIndex < 0){
			return;
		}
		if(speicher[childIndex].compareTo(speicher[parentIndex]) < 0){
			String temp = speicher[childIndex];
			speicher[childIndex] = speicher[parentIndex];
			speicher[parentIndex] = temp;
			siftUp(parentIndex);
		}
	}
	
	public synchronized void siftDown(int nodeIndex){
		int left = getLeftChild(nodeIndex);
		int right = getRightChild(nodeIndex);
		int smaller = 0;
		if(left >= index){
			return; // no children(index bigger than pointer)
		}
		else if(right >= index){
			//only left child
			if(speicher[nodeIndex].compareTo(speicher[left]) > 0){
				//current is bigger than left child
				String temp = speicher[nodeIndex];
				speicher[nodeIndex] = speicher[left];
				speicher[left] = temp;
				//no recursive call needed as end of list
			}
		}
		else{
			if(speicher[left].compareTo(speicher[right]) < 0){
				//left is smaller
				smaller = left;
			}
			else {
				//right is smaller or equal to left
				smaller = right;
			}
			if(speicher[nodeIndex].compareTo(speicher[smaller]) > 0){
				//current is bigger than smaller child
				String temp = speicher[smaller];
				speicher[smaller] = speicher[nodeIndex];
				speicher[nodeIndex] = temp;
				//recursive call needed (not end of list)
				siftDown(smaller);
			}
			
		}
	}
}
