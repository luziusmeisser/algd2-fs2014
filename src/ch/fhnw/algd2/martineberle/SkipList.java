package ch.fhnw.algd2.martineberle;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T>{
Element<T> head;
Element<T> tail;
int height;
static final int max = 20;

public SkipList(){
	height = 0;
}

	public void updateHeight(Element<T> newElem){
		if(newElem.pointer.length > height){
			height = newElem.pointer.length;
		}
	}
	
	@Override
	public void add(T item) {
		if(head == null){
			head = new Element<T>(item, max);
		} else {
			Element<T> temp = new Element<T>(item);
			insertElement(findInsertPosition(temp));
			//Find Insert Position - Done!
			//Create new Element and Calculate pointer lvl			-Done!
			//Insert into list aka Update pointers in before and after Elements
		}
	}
	public Element<T>[] findInsertPosition(Element<T> newElement){
		Element<T> curr = head;
		if(head == null || newElement.val.compareTo(head.val) < 0){ //if new element is smaller than head, create new head.
			Element<T> tmp = new Element<T>(newElement.val, max);
			for(int i = 0; i < max; i++){
				tmp.pointer[i] = head;
				head = tmp;
			}			
		}
		while(true){
			if(curr.val.compareTo(newElement.val) <= 0){ //if newElement is bigger or equal than actual compare position
				int j = 0;
				while(curr.pointer.length - j >= 0){
					if(curr.pointer[curr.pointer.length-j].val.compareTo(newElement.val) <= 0){ //and if newElement is bigger than next element on actual pointerlevel
						curr = curr.pointer[curr.pointer.length-j]; //iterate curr to highest next available element
					}
					else {
						if(curr.pointer.length-j <= newElement.pointer.length){
							newElement.pointer[curr.pointer.length-j] = curr.pointer[curr.pointer.length-j]; //copy pointer from previous element to newElement
							curr.pointer[curr.pointer.length-j] = newElement;
						}
						j++; //if next element on current pointerlvl is smaller than newElement, reduce pointerlevel by 1
					}
				}
							Element<T>[] insertPos = new Element[2];
							insertPos[0] = curr;
							insertPos[1] = newElement;
							return insertPos; //if pointerlvl 0 is reached and newElement is not bigger than next element, position to insert newElement found
			} else { throw new IllegalStateException("Error finding inserting position!"); //avoid infinite loop
			}
		}
	}
	
	public void insertElement(Element<T>[] addresses){
		
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countStepsTo(T item) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
class Element<T>{
	T val;
	Element<T> pointer[];
	public Element(T value){
		val = value;
		int i = 0;
		Random r = new Random();
		boolean moreLinks = true;
		while(i < 20 && moreLinks){
			moreLinks = r.nextBoolean();
			i++;
		}
			pointer = new Element[i];
	}
	//separate constructor for first/last element with max pointer height
	public Element(T value, int height){
		val = value;
		pointer = new Element[height];
	}
}