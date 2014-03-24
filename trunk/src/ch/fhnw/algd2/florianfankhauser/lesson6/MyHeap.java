package ch.fhnw.algd2.florianfankhauser.lesson6;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class MyHeap implements IHeap {
	private int nextPos = 0;
	private String[] heap = new String[500];
	

	@Override
	public synchronized void offer(String s) {
		if (nextPos > heap.length / 2) {
			resizeHeap();
		}
		heap[nextPos] = s;	
		checkUp(nextPos);
		nextPos++;
	}
	
	private void checkUp(int i) {
		if (i > 0) {
			int p = (i-1) / 2;
			if (heap[p].compareTo(heap[i]) > 0) {
				switchElements(i, p);
				checkUp(p);
			}
		}
	}

	@Override
	public String peek() {
		return heap[0];
	}

	@Override
	public synchronized String poll() {
		if (heap[0] != null) {
			nextPos--;
			String val = heap[0];
			heap[0] = heap[nextPos];
			heap[nextPos] = null;
			checkDown(0);
			return val;
		}
		return null;
	}
	
	private void checkDown(int i) {
		int cl = i * 2 + 1;
		int cr = i * 2 + 2;
		if (!(heap[cl] == null)) {
			if (heap[cr] == null || heap[cl].compareTo(heap[cr]) < 0) {
				switchElements(i, cl);
				checkDown(cl);
			} else {
				switchElements(i, cr);
				checkDown(cr);
			}
		}
	}
	
	private void switchElements(int a, int b) {
		String valA = heap[a];
		heap[a] = heap[b];
		heap[b] = heap[a];
	}
	
	private void resizeHeap() {
		String[] newHeap = new String[(int) (heap.length * 2)];
		for (int i = 0; i < heap.length; i++) {
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}
}
