// Created by Marco on 30.03.2014
//inspired by Emanuel Mistretta

package ch.fhnw.algd2.marcogaiffi;

import java.util.ArrayList;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

/**
 * Exercise 6: implement a heap using an array. Note that the heap must be
 * thread-safe! The Java equivalent is java.util.PriorityBlockingQueue .
 */
public class Heap implements IHeap {

	ArrayList<String> data;

	public Heap() {
		data = new ArrayList<String>();
	}

	// Add item to heap
	@Override
	public synchronized void offer(String s) {
		data.add(s);
		siftUp(0);
	}

	// returns the first element without removing,
	// returns null if no element left
	@Override
	public String peek() {
		if (data.size() == 0) {
			return null;
		}
		return data.get(0);
	}

	// removes and returns the first element,
	// returns null if no element left
	@Override
	public synchronized String poll() {
		if (data.size() == 0) {
			return null;
		}
		if (data.size() == 1) {
			return data.remove(0);
		}
		String result = data.get(0);
		String lastElement = data.remove(data.size()-1);
		data.set(0, lastElement);
		siftDown(0);
		return result;
	}
	
	private void siftUp(int index){
		if(index == 0){
			return;
		}
		if(data.get(index).compareTo(data.get(getParent(index))) < 0){
			swap(index, getParent(index));
			siftUp(getParent(index));
		}
	}
	
	private void siftDown(int index){
		String parent = data.get(index);
		String left = getLeft(index) < data.size() ? data.get(getLeft(index)) : "" + (char) 255;
		String right = getRight(index) < data.size() ? data.get(getRight(index)) : "" + (char) 255;
		
		if(parent.compareTo(left) <= 0 && parent.compareTo(right) <= 0){
			return;
		}else if(left.compareTo(right) <= 0){
			swap(index, getLeft(index));
			siftDown(getLeft(index));
		}else{
			swap(index, getRight(index));
			siftDown(getRight(index));
		}
	}
	
	private void swap(int A, int B){
		String a = data.get(A);
		data.set(A, data.get(B));
		data.set(B, a);
	}

	private int getLeft(int index){
		return index*2+1;
	}
	
	private int getRight(int index){
		return index*2+1;
	}
	
	private int getParent(int index){
		return (index-1)/2;
	}
}
