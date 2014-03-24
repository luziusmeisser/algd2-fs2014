// Created by Stephen Randles 24.03.2014

package ch.fhnw.algd2.stephenrandles.lesson06;

import java.util.ArrayList;
import java.util.Collections;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {
	ArrayList<String> heap;

	public Heap() {
		heap = new ArrayList<>();
	}

	@Override
	public synchronized void offer(String s) {
		heap.add(s);
		moveUp(heap.size()-1);
	}

	@Override
	public String peek() {
		if (heap.size() > 0) {
			return heap.get(0);
		} else {
			return null;
		}
	}

	@Override
	public synchronized String poll() {
		if (heap.size() == 0) return null;
		
		String top = heap.get(0);
		heap.remove(0);
		
		if (heap.size() > 1) {
			// Take last element to the the root
			heap.add(0, heap.get(heap.size()-1));
			heap.remove(heap.size()-1);
	
			// Move new root to correct position
			moveDown(0);
		}
		
		return top;
	}
	
	private void moveUp(int i) {
		if (i == 0) return;
		
		int parentIndex = indexOfParent(i);
		// Move element up the tree if larger than parent
		if (isLarger(i, parentIndex)) {
			Collections.swap(heap, i, parentIndex);
			moveUp(parentIndex);
		}
	}
	
	private void moveDown(int i) {
		// Check if has child
		if (indexOfLeftChild(i) < heap.size() || indexOfRightChild(i) < heap.size()) {
			int moveDownToIndex=0;
			
			if (indexOfLeftChild(i) < heap.size() && indexOfRightChild(i) < heap.size()) {
				if (isLarger(indexOfLeftChild(i), indexOfRightChild(i))) {
					moveDownToIndex = indexOfLeftChild(i);
				} else {
					moveDownToIndex = indexOfRightChild(i);
				}
			} else if (indexOfLeftChild(i) < heap.size() && isSmaller(i, indexOfLeftChild(i))) {
				moveDownToIndex = indexOfLeftChild(i);
			} else if (indexOfRightChild(i) < heap.size() && isSmaller(i, indexOfRightChild(i))) {
				moveDownToIndex = indexOfRightChild(i);
			}
			
			if (moveDownToIndex != 0) {
				Collections.swap(heap, i, moveDownToIndex);
				moveDown(moveDownToIndex);	
			}			
		}		
	}
	
	//
	// Helpers
	//
	
	private int indexOfParent(int i) {
		return (i-1) / 2;
	}
	
	private int indexOfLeftChild(int i) {
		return 2*i + 1;
	}
	
	private int indexOfRightChild(int i) {
		return 2*i + 2;
	}
	
	private boolean isLarger(int indexA, int indexB) {
		return heap.get(indexA).compareTo(heap.get(indexB)) > 0;
	}
	
	private boolean isSmaller(int indexA, int indexB) {
		return heap.get(indexA).compareTo(heap.get(indexB)) < 0;
	}

}
