// Created by Yannick Augstburger on Mar 20, 2014
package ch.fhnw.algd2.yannickaugstburger;

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
		 if (getBalance() > 1) { 
	            // links
	            if (getLeftChild().getBalance() == -1) { 
	                // links/rechts
	                setLeft(getLeftChild().rotateLeft());
	            }
	            // links/links
	            return rotateRight();
	        } else if (getBalance() < -1) {
	            // rechts
	            if (getRightChild().getBalance() == 1) { 
	                // rechts/links
	                setRight(getRightChild().rotateRight());
	            }
	            // rechts/rechts
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
		this.setLeft(getLeftChild().getRightChild());
		left.setRight(this);
		return left;
	}

}
