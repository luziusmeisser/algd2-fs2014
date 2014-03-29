// Created by Kevin Wieser on 28.03.2014
package ch.fhnw.algd2.kevinwieser;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {
	
	private String[] heap;
	private int pos_free;
	
	public Heap(int size) {
		assert size > 0;
		heap = new String[size];
		pos_free = 0;
	}
	
	
	/**
	 * Adds an item to the heap.
	 */
	@Override
	public void offer(String s) {
		if (pos_free == 0) {
			heap[pos_free] = s;
			pos_free++;
		} else {
			if (pos_free < heap.length) {
				heap[pos_free] = s;
				siftUp(pos_free);
				pos_free++;
			} else {
				resizeArray();
				offer(s);
			}
		}
	}

	
	/**
	 * Returns the first item without removing it.
	 * Returns null if there is no element left.
	 */
	@Override
	public String peek() {
		return heap[0];
	}

	
	/**
	 * Removes and returns the first item.
	 * Returns null if there is no element left.
	 */
	@Override
	public String poll() {
		String tmp = null;
		if (pos_free > 0) {
			tmp = heap[0];
			heap[0] = heap[pos_free-1];
			siftDown(0);
			pos_free--;
		}
		
		return tmp;
	}
	
	
	private void siftDown(int pos) {
		String child_left = null;
		String child_right = null;
		int pos_left = pos * 2 + 1;
		int pos_right = pos * 2 + 2;
		
		if (!checkIndexOutOfBound(pos_left)) {
			child_left = heap[pos * 2 + 1];
		}
		if (!checkIndexOutOfBound(pos_right)) {
			child_right = heap[pos * 2 + 2];
		}
		
		if(child_left != null && child_right != null) {
			// bestimme nun den grösseren Sohn:
			if (child_left.compareTo(child_right) > 0) {
				// ist akutelle pos kleiner als linker sohn? 
				if(heap[pos].compareTo(child_left) < 0) {
					swap(pos, pos * 2 + 1);
					siftDown(pos * 2 + 1);
				}
			} else {
				// ist akutelle pos kleiner als rechter sohn? 
				if(heap[pos].compareTo(child_right) < 0) {
					swap(pos, pos * 2 + 2);
					siftDown(pos * 2 + 2);
				}
			}
		} else if (child_left != null) {
			if(heap[pos].compareTo(child_left) < 0) {
				swap(pos, pos * 2 + 1);
				//siftDown(pos * 2 + 1);
			}
		
		} else if (child_right != null) {
			// Darf gar nie vorkommen....
			if(heap[pos].compareTo(child_right) < 0) {
				swap(pos, pos * 2 + 2);
				// siftDown(pos * 2 + 2);
			}
		}
	}
	
	private void siftUp(int pos) {
		// pos muss grösser sein als 0, da sonst IndexOutOfBound
		if (pos > 0) {
			String parent = heap[(pos - 1) / 2];
			if (parent != null) {
				if (heap[pos].compareTo(parent) > 0) {
					// Aktuelle pos ist grösser als parent -> swap
					swap(pos, (pos - 1) / 2);
					siftUp((pos - 1) / 2); // siftUp bei Parent
				}
			}
		}
	}
	
	private void resizeArray() {
		int length = heap.length;
		String[] heap2 = new String[2*length];
		for (int i = 0; i < heap.length; i++) {
			heap2[i] = heap[i];
		}
		heap = heap2;
	}
	
	private void swap(int pos1, int pos2) {
		// pos1 mit pos2 vertauschen
		String tmp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = tmp;
	}
	
	private boolean checkIndexOutOfBound(int pos) {
		try {
			String s = heap[pos];
			return false;
		} catch (IndexOutOfBoundsException e) {
			return true;
		}
	}

}
