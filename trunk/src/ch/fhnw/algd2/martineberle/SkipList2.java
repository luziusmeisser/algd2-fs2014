// Created by Martin Eberle on 11.04.2014

package ch.fhnw.algd2.martineberle;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList2<T> implements ISkipList{
	SkipNode head = new SkipNode(null);
	static final int MAX_HEIGHT = 20;

	@Override
	public void add(Comparable item) {
		if(head == null){
			head = new SkipNode(item, MAX_HEIGHT);
		}
		else {
			//walk top level until next value to big
			//(if pointerlvl <= currpointersize, insert and continue)
			//lower level by one, walk until next level to big
			//repeat until level = 0
			//position found
			int level = MAX_HEIGHT-1;
			int steps = 0;
			SkipNode newNode = new SkipNode(item);
			SkipNode curr = head;
			while(level >= 0){
				while(curr.hasNext(level) && newNode.val.compareTo(curr.pointer[level].val) >= 0){
					curr = curr.pointer[level];
					steps++;
				}
				if(level < newNode.pointer.length){
					newNode.pointer[level] = curr.pointer[level];
					curr.pointer[level] = newNode;
				}
				level--;
			}
		}
	}

	@Override
	public Comparable removeFirst() {
		SkipNode temp = new SkipNode(head.pointer[0].val, MAX_HEIGHT);
		for(int i = head.pointer[0].pointer.length; i > 0; i--){
			temp.pointer[i-1] = head.pointer[0].pointer[i-1];
		}
		Comparable temporary = head.val;
		head = temp;
		return temporary;
	}

	@Override
	public int countStepsTo(Comparable item) {
		SkipNode curr = head;
		int level = MAX_HEIGHT-1;
		int steps = 0;
		while(level >= 0){
			while(curr.hasNext(level) && item.compareTo(curr.pointer[level].val) >= 0){
				curr = curr.pointer[level];
				steps++;
			}
			level--;
	}
		if(curr.val != null && curr.val.compareTo(item) <= 0){
			return steps;
		}
		else {
			return -1;
		}
}
	
class SkipNode<T extends Comparable>{
	Comparable val;
	SkipNode[] pointer;
	public SkipNode(Comparable o){
		int rand = (int)Math.random()*20;
		pointer = new SkipNode[rand];
	}
	public SkipNode(Comparable<T> o, int size){
		pointer = new SkipNode[20];
		val = o;
	}
	public boolean hasNext(int level){
		if(level <= this.pointer.length-1 && this.pointer[level] != null){
			return true;
		}
		else {
			return false;}
		
		}
	}
}
