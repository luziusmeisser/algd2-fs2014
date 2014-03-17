// Created by Luzius on Mar 17, 2014

package ch.fhnw.algd2.lesson5.exercise;

public class AVLTree {
	
	private AbstractAVLNode first;
	
	public AVLTree(AbstractAVLNode first){
		this.first = first;
		assert first != null;
	}
	
	public void insert(String value){
		first.insert(value);
		first = first.ensureBalance();
	}

	@Override
	public String toString(){
		return first.toString();
	}
	
}
