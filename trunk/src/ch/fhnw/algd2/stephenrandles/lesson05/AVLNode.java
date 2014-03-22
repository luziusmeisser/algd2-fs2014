// Created by Stephen Randles 17.03.2014

package ch.fhnw.algd2.stephenrandles.lesson05;

import ch.fhnw.algd2.lesson5.exercise.AbstractAVLNode;

public class AVLNode extends AbstractAVLNode {

	public AVLNode(String value) {
		super(value);
	}

	@Override
	protected AbstractAVLNode createNode(String value) {
		return new AVLNode(value);
	}

	@Override
	protected int calculateHeight() {
		int height = (this.getLeftHeight() > this.getRightHeight()) ? this.getLeftHeight() : this.getRightHeight();
		return height + 1;
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		if (getBalance() > 1) {			// Left-heavy 
			if (this.getLeftChild().getBalance() <= -1 ) {	// Left-right case
				this.setLeft(this.getLeftChild().rotateLeft());
			}			
			return rotateRight();
		} else if (getBalance() < -1) {	// Right-heavy
			if (this.getRightChild().getBalance() >= 1) {	// Right-left case
				this.setRight(this.getRightChild().rotateRight());
			}
			return rotateLeft();
		} else {
			return this;
		}
	}

	@Override
	public AbstractAVLNode rotateLeft() {
		AbstractAVLNode newRoot = this.getRightChild(); 
		this.setRight(newRoot.getLeftChild());
		newRoot.setLeft(this);
		
		return newRoot;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		AbstractAVLNode newRoot = this.getLeftChild(); 
		this.setLeft(newRoot.getRightChild());
		newRoot.setRight(this);
		
		return newRoot;
	}

}
