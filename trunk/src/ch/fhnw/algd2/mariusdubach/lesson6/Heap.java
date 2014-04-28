//Created by Marius Dubach 24.03.2014

package ch.fhnw.algd2.mariusdubach.lesson6;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap{
	
	private int elements = 0;
	private String[] storage = new String[301];

	@Override
	synchronized public void offer(String s) {
		storage[elements] = s;	//füge s am Ende ein
		siftUp(elements);
		elements++;	
	}

	@Override
	synchronized public String peek() {
		return storage[0];		
	}

	@Override
	synchronized public String poll() {
		if(storage[0] != null){
			String ret = storage[0];
			storage[0] = storage[elements-1];
			storage[elements-1] = null;
			elements--;
			siftDown(0);	
			return ret;
		}
		return null;
	}
	
	synchronized private void siftDown(int rootIndex){
		if(storage[getLeftChild(rootIndex)] != null && storage[getRightChild(rootIndex)] != null){
			int compFactor = storage[getLeftChild(rootIndex)].compareTo(storage[getRightChild(rootIndex)]); 
			if(compFactor < 0 ){
				if(storage[rootIndex].compareTo(storage[getLeftChild(rootIndex)]) > 0){
					String tmp = storage[getLeftChild(rootIndex)];
					storage[getLeftChild(rootIndex)] = storage[rootIndex];
					storage[rootIndex] = tmp;
					siftDown(getLeftChild(rootIndex));
					return;
				}else{
					return;
				}
			}else if(compFactor > 0){
				if(storage[rootIndex].compareTo(storage[getRightChild(rootIndex)]) > 0){
					String tmp = storage[getRightChild(rootIndex)];
					storage[getRightChild(rootIndex)] = storage[rootIndex];
					storage[rootIndex] = tmp;
					siftDown(getRightChild(rootIndex));
					return;
				}else{
					return;
				}
			}
		}		
		if(storage[getLeftChild(rootIndex)] != null){
			if(storage[rootIndex].compareTo(storage[getLeftChild(rootIndex)]) > 0){
				String tmp = storage[getLeftChild(rootIndex)];
				storage[getLeftChild(rootIndex)] = storage[rootIndex];
				storage[rootIndex] = tmp;
				siftDown(getLeftChild(rootIndex));
			}
		}else if(storage[getRightChild(rootIndex)] != null){
			if(storage[rootIndex].compareTo(storage[getRightChild(rootIndex)]) > 0){
				String tmp = storage[getRightChild(rootIndex)];
				storage[getRightChild(rootIndex)] = storage[rootIndex];
				storage[rootIndex] = tmp;
				siftDown(getRightChild(rootIndex));
			}
		}
	}
	
	
	synchronized private void siftUp(int rootIndex){
		if( getLeftChild((rootIndex -1) / 2) == rootIndex ){ //node to siftUp is on the left side of the parent node
			int parentIndex;
			if(rootIndex == 1){
				parentIndex = 0;
			}else{
				parentIndex = (rootIndex -1)/2;
			}			
			if(storage[parentIndex] != null){
				if(storage[parentIndex].compareTo(storage[rootIndex]) > 0){
					String tmp = storage[rootIndex];
					storage[rootIndex] = storage[parentIndex];
					storage[parentIndex] = tmp;
					siftUp(parentIndex);
				}
			}
			return;
		}else if( getRightChild((rootIndex -2 ) / 2) == rootIndex ){ //node to siftUp is on the right side of the parent node
			int parentIndex;
			if(rootIndex == 1){
				parentIndex = 0;
			}else if(rootIndex < 1){
				return;
			}else{
				parentIndex = (rootIndex -2)/2;
			}			
			if(storage[parentIndex] != null){
				if(storage[parentIndex].compareTo(storage[rootIndex]) > 0){
					String tmp = storage[rootIndex];
					storage[rootIndex] = storage[parentIndex];
					storage[parentIndex] = tmp;
					siftUp(parentIndex);
				}
			}
			return;			
		}
	}
	
	private int getLeftChild(int rootIndex){
		return rootIndex * 2 + 1;
	}
	
	private int getRightChild(int rootIndex){
		return rootIndex * 2 + 2;
	}

}
