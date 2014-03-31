package ch.fhnw.algd2.larskessler;

import java.util.ArrayList;
import java.util.Collections;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {
	
	private ArrayList<String> strings = new ArrayList<String>();
	
	private int getChild(int i, char c) {
		int index = i << 1;
		return index = c == 'l' ? index + 1 : index + 2; 
	}
	
	private int getParent(int i) {
		return (i-1) >> 1;
	}
	
	private void swap(int index1, int index2) {
		Collections.swap(strings, index1, index2);
	}
	
	private void siftUp(int toSiftUpIndex) {
		if(toSiftUpIndex > 0) {
			int parentIndex = getParent(toSiftUpIndex);
		
			String toSiftUp = strings.get(toSiftUpIndex);
			String parent = strings.get(parentIndex);
		
			if(toSiftUp.compareTo(parent) > 0) {
				swap(parentIndex, toSiftUpIndex);
				siftUp(parentIndex);
			}
		}
	}
	
	private void siftDown(int toSiftDownIndex) {
		int left = getChild(toSiftDownIndex, 'l');
		int right = getChild(toSiftDownIndex, 'r');
		int variant = 0;

		String toSiftDown = strings.get(toSiftDownIndex);
		
		String leftChild = null;
		String rightChild = null;
		
		if(left < strings.size() && left >= 0) {
			leftChild = strings.get(left);
			variant++;
		}
		
		if(right < strings.size() && right >= 0) {
			rightChild = strings.get(right);
			variant++;
		}
		
		switch(variant) {
		case 1:
			if(toSiftDown.compareTo(leftChild) < 0) {
				swap(toSiftDownIndex, left);
				siftDown(left);	
			}
			
			// finished
			break;
		case 2:
			int biggerChild;
			
			// find bigger child
			if(leftChild.compareTo(rightChild) < 0) {
				biggerChild = right;
			} else {
				biggerChild = left;
			}
			
			if(toSiftDown.compareTo(strings.get(biggerChild)) < 0) {
				swap(toSiftDownIndex, biggerChild);
				siftDown(biggerChild);
			}
			
			// nothing to do
			break;
		default:
			// nop: nothing to do because there are no children
		}
	}

	@Override
	public synchronized void offer(String s) {		
		// add string and sift last position to top
		strings.add(s);
		siftUp(strings.size()-1);
	}

	@Override
	public String peek() {
		if(strings.isEmpty()) {
			return null;
		}
		
		return strings.get(0);
	}

	@Override
	public synchronized String poll() {
		if(strings.isEmpty()) {
			return null;
		}
		
		// save first and remove
		String first = strings.get(0);
		strings.remove(0);
		
		if(strings.size() > 1) {
			// add last element to position 0 and remove last position
			strings.add(0, strings.get(strings.size()-1));
			strings.remove(strings.size()-1);
			// sift down new first
			siftDown(0);
		}
		
		// return deleted element
		return first;
	}
	
	public int getSize() {
		return strings.size();
	}
	
	public String getValue(int position) {
		return strings.get(position);
	}
}
