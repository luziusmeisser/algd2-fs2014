//Created by Marius Dubach 20.03.2014

package ch.fhnw.algd2.mariusdubach.lesson5;

import ch.fhnw.algd2.lesson5.exercise.AbstractAVLNode;

public class AVLNode extends AbstractAVLNode{

	public AVLNode(String value) {
		super(value);
	}

	@Override
	protected AbstractAVLNode createNode(String value) {
		return new AVLNode(value);
	}

	@Override
	protected int calculateHeight() {
		return Math.max(getLeftHeight(), getRightHeight()) + 1;
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		
		if(this.getBalance() == 2){
			if(this.getLeftChild().getBalance() == -1){
				this.setLeft(this.getLeftChild().rotateLeft());
			}
			return this.rotateRight();
		}else if(this.getBalance() == -2){
			if(this.getRightChild().getBalance() == 1){
				this.setRight(this.getRightChild().rotateRight());
			}
			return this.rotateLeft();
		}else{
			return this;
		}
	}

	@Override
	public AbstractAVLNode rotateLeft() {
		AbstractAVLNode aRight = this.getRightChild();
		this.setRight(aRight.getLeftChild());
		aRight.setLeft(this);
		return aRight;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		AbstractAVLNode aLeft = this.getLeftChild();
		this.setLeft(aLeft.getRightChild());
		aLeft.setRight(this);
		return aLeft;
	}

}
