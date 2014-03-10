//Created by Marius Dubach 24.02.2014

package ch.fhnw.algd2.mariusdubach.lesson2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T>{
	
	private static final int MAXHEIGHT = 10;
	
	private ArrayList<LinkedList<T>> myLists = new ArrayList<>();
	private int height;
	
	public SkipList(){
		for(int i=0; i<MAXHEIGHT; i++){
			myLists.add(new LinkedList<T>());
		}

	}

	public int getSize(){
		return this.myLists.get(0).size();
	}

	@Override
	public void add(T item) {
		
		height = getLevel();

		for(int i=0; i<height; i++){
			LinkedList<T> tmp = myLists.get(i);

			ListIterator<T> iter = tmp.listIterator();
			T elemOne = null;
			T elemTwo = null;
			while(true){
				if((Integer) item == 0){
					myLists.get(i).add(0, item);
					break;
				}
				if(tmp.size() > 1){
					if(elemOne==null && elemTwo == null){
						elemOne = iter.next();
						elemTwo = iter.next();
					}										
					if(elemTwo != null){
						if(item.compareTo(tmp.get(0))<0){
							iter.previous();
							iter.previous();
							iter.add(item);
							break;
						}
						if(elemOne.compareTo(item)<0 && elemTwo.compareTo(item) >= 0){
							iter.previous();
							iter.add(item);
							break;
						}
					}else{
						if(elemOne.compareTo(item)<0 && elemTwo==null){
							iter.add(item);
							break;
						}else{
							iter.add(item);
							break;
						}
					}
				}else{
					if(tmp.size() == 0){
						tmp.add(item);
					}else if(tmp.size() == 1){
						if(tmp.get(0).compareTo(item)<=0){
							tmp.add(1, item);
						}else{
							tmp.add(0, item);
						}
					}
					break;
				}
				if(elemTwo != null){
					elemOne = elemTwo;
					if(iter.hasNext()){
						elemTwo = iter.next();
					}else{
						elemTwo=null;
					}
				}
			}
		}
	}
	
	@Override
	public T removeFirst() {
		if(myLists.get(0).size() == 0){
			throw new NoSuchElementException();
		}else{
			return myLists.get(0).poll();
		}
	}
	
	public int countStepsTo(T item){
		int counter = 0;
		int iterMem = 0;
		for(int i=myLists.size()-1; i>=0; i--){
			ListIterator<T> iter = myLists.get(i).listIterator(iterMem);
			T mem;
			while(iter.hasNext()){
				mem = iter.next();				
				if(mem.compareTo(item) == 0){
					return counter;
				}else if(mem.compareTo(item) > 0){
					counter++;
					iterMem = iter.nextIndex() -1 ;
					break;
				}
			}			
		}
		return counter;
	}

	
	private int getLevel(){
		height=1;
		while (Math.random() < 0.5 && height < MAXHEIGHT) {
			height++;
		}
		return height;
	}
	
}
