// Created by Luzius on 24.03.2014

package ch.fhnw.algd2.luzius;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {

	private int pos;
	private String[] queue;

	public Heap() {
		this.queue = new String[1000];
		this.pos = 0;
	}

	@Override
	public synchronized void offer(String s) {
		queue[pos++] = s;
		siftUp(pos - 1);
	}

	private void siftUp(int child) {
		int parent = getParent(child);
		while (parent >= 0 && queue[child].compareTo(queue[parent]) < 0) {
			String earlier = queue[child];
			queue[child] = queue[parent];
			queue[parent] = earlier;
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
			return queue[0];
		}
	}

	@Override
	public synchronized String poll() {
		if (pos == 0) {
			return null;
		} else {
			String first = queue[0];
			queue[0] = queue[--pos];
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
			if (queue[parent].compareTo(queue[leftC]) > 0){
				String t = queue[leftC];
				queue[leftC] = queue[parent];
				queue[parent] = t;
			}
			return;
		} else {
			int lowerC = queue[leftC].compareTo(queue[rightC]) < 0 ? leftC : rightC;
			if (queue[parent].compareTo(queue[lowerC]) > 0){
				String t = queue[lowerC];
				queue[lowerC] = queue[parent];
				queue[parent] = t;
				siftDown(lowerC);
			}
		}
	}

}
