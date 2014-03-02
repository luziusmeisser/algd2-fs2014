package ch.fhnw.algd2.martineberle;
import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T>{
Element<T> head;
//Element<T> tail;
//int height;
static final int max = 20;

public SkipList(){
//	height = 0;
}

	public void updateHeight(Element<T> newElem){
//		if(newElem.pointer.length > height){
//			height = newElem.pointer.length;
//		}
	}
	
	@Override
	public void add(T item) {
		Element<T> temp = new Element<T>(item);
		if(head == null){
			head = temp;
			temp = null;
		} else {
			//insertElement(findInsertPosition(temp));
			sortedInsert(temp);
			//Find Insert Position - Done!
			//Create new Element and Calculate pointer lvl			-Done!
			//Insert into list aka Update pointers in before and after Elements
		}
	}
	public void sortedInsert(Element<T> newElement){
		Element<T> curr = head;
		if(head == null || newElement.val.compareTo(head.val) < 0){ //if new element is smaller than head, create new head.
			Element<T> tmp = new Element<T>(newElement.val, max);
			for(int i = 0; i < max; i++){
				tmp.pointer[i] = head;
			}
			head = tmp;
			return;
		}
		//while(true){
			if(curr.val.compareTo(newElement.val) < 0){ //if newElement is bigger or equal than actual compare position
				int j = 1;
				while(curr.pointer.length - j >= 0){
					if(curr.pointer[curr.pointer.length-j] != null && (curr.pointer[curr.pointer.length-j].val).compareTo(newElement.val) < 0){ //and if newElement is bigger than next element on current pointerlevel
						curr = curr.pointer[curr.pointer.length-j]; //iterate curr to next element
						j = 1;
					}
					else if (curr.pointer[curr.pointer.length-j] == null && curr.pointer.length-j <= newElement.pointer.length-1){ //if current pointerlvl is smaller than newElement and has no pointer jet, add newElement
						curr.pointer[curr.pointer.length-j] = newElement;
					}
					
					if(curr.pointer.length-j <= newElement.pointer.length-1){ //connect all pointerlvls from curr to newElement
							while(curr.pointer.length-j >=0){
							newElement.pointer[curr.pointer.length-j] = curr.pointer[curr.pointer.length-j]; //copy pointer from previous element to newElement
							curr.pointer[curr.pointer.length-j] = newElement;
							j++;
							}
							return;
					}
						j++; //if next element on current pointerlvl is smaller than newElement, reduce pointerlevel by 1
				}
				//return;
			}
			//else { throw new IllegalStateException("Error finding inserting position!"); //avoid infinite loop
			//}
		//}
	}
	
	public void insertElement(Element<T>[] addresses){
		//after pos found, element already inserted...
	}

	@SuppressWarnings("unchecked")
	@Override
	public T removeFirst() {
		if(this.head == null){
			throw new NoSuchElementException("List empty!");
		}
		Element<T> tmp = head;
		head = head.pointer[0];
		return tmp.val;
	}

	@Override
	public int countStepsTo(T item) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
class Element<T extends Comparable<T>>{
	public T val;
	public Element<T>[] pointer;
	
	@SuppressWarnings("unchecked")
	public Element(T value){
		this.val = value;
		int i = 0;
		Random r = new Random();
		boolean moreLinks = true;
		while(i < 20 && moreLinks){
			moreLinks = r.nextBoolean();
			i++;
		}
			this.pointer = new Element[i];
	}
	//separate constructor for first element with max pointer height
	@SuppressWarnings("unchecked")
	public Element(T value, int height){
		this.val = value;
		this.pointer = new Element[height];
	}
}