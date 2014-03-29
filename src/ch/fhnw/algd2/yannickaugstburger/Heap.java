// Created by Yannick Augstburger on Mar 29, 2014
package ch.fhnw.algd2.yannickaugstburger;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap{
	
	private int pos;
	private final String[] heap;
	private int lowerC;

	public Heap() {
		this.heap = new String[1000];
		this.pos = 0;
	}

	@Override
	public synchronized void offer(String s) {
		heap[pos++] = s;
		siftUp(pos - 1);
	}

	private void siftUp(int child) {
		int parent = getParent(child);
		while (parent >= 0 && heap[child].compareTo(heap[parent]) < 0) {
			String earlier = heap[child];
			heap[child] = heap[parent];
			heap[parent] = earlier;
			child = parent;
			parent = getParent(child);
		}
	}

	private int getParent(int child) {
		return (child - 1) / 2;
	}

	@Override
	public synchronized String peek() {
		if (pos == 0) {
			return null;
		} else {
			return heap[0];
		}
	}

	@Override
	public synchronized String poll() {
		if (pos == 0) {
			return null;
		} else {
			String first = heap[0];
			heap[0] = heap[--pos];
			siftDown(0);
			return first;
		}
	}

	private void siftDown(int parent) {
		int leftC = parent * 2 + 1;
		int rightC = leftC + 1;
		if (leftC >= pos){
			return;
		} else if (rightC >= pos){
			if (heap[parent].compareTo(heap[leftC]) > 0){
				String t = heap[leftC];
				heap[leftC] = heap[parent];
				heap[parent] = t;
			}
			return;
		} else {
			if(heap[leftC].compareTo(heap[rightC]) < 0){
				lowerC = leftC;
			}else{
				lowerC = rightC;
			}
			if (heap[parent].compareTo(heap[lowerC]) > 0){
				//swap 
				String t = heap[lowerC];
				heap[lowerC] = heap[parent];
				heap[parent] = t;
				siftDown(lowerC);
			}
		}
	}

}
