// Created by Marco on 17.03.2014

package ch.fhnw.algd2.marcogaiffi;

import ch.fhnw.algd2.lesson5.exercise.AbstractAVLNode;


public class AVLNode extends AbstractAVLNode{

	//initial height = 1
	public AVLNode(String value) {
		super(value);
	}

	@Override
	protected AbstractAVLNode createNode(String value) {
		return new AVLNode(value);
	}

	@Override
	protected int calculateHeight() {
		return Math.max(getLeftHeight(), getRightHeight() + 1);
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		if(getBalance() > 1){
			//left right case
			if(getLeftChild().getBalance() == -1){
				setLeft(getLeftChild().rotateLeft()); //reduce to left left case
			}
			//left left case
			return rotateRight();
		}else if (getBalance() < -1){
			//right left case
			if(getRightChild().getBalance() == 1){ //reduce to right right case
				setRight(getRightChild().rotateRight());
			}
			//right right case
			return rotateLeft();
		}
		else{
			return this;
		}
	}
	
	@Override
	public AbstractAVLNode rotateLeft() {
		AbstractAVLNode right = getRightChild();
		this.setRight(getRightChild().getLeftChild());
		right.setLeft(this);
		return right;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		AbstractAVLNode left = getLeftChild();
		this.setRight(getRightChild().getLeftChild());
		left.setRight(this);
		return left;
	}
}