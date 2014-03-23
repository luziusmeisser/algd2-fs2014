// Created by Martin Eberle on 23.03.2014

package ch.fhnw.algd2.martineberle;

import ch.fhnw.algd2.lesson5.exercise.AbstractAVLNode;

public class AVLNode extends AbstractAVLNode {

    public AVLNode(String value) {
        super(value);
    }

    @Override
    public AbstractAVLNode createNode(String value) {
        return new AVLNode(value);
    }

    @Override
    public int calculateHeight() {
        return Math.max(getLeftHeight(), getRightHeight()) + 1;
    }

    @Override
    public AbstractAVLNode ensureBalance() {
        if(this.getBalance() >= 2) { //left-right = too big = left node too high
            if(this.getLeftChild().getBalance() == -1) { // left right case
                setLeft(this.getLeftChild().rotateLeft());
            }
            return rotateRight();
        } else if(getBalance() <= -2){ //left-right = too low = right node too high
            if(this.getRightChild().getBalance() == 1) { //right left case
                setRight(this.getRightChild().rotateRight());
            }
            return rotateLeft();
        }
        return this; //if not unbalanced, do nothing
    }
    
    @Override
    public AbstractAVLNode rotateRight() {
        
    	if(this.getLeftChild() == null) {
        	return this;
        }
    	AbstractAVLNode daddy = getLeftChild(); //create pointer to avoid removing by GBC
        this.setLeft(this.getLeftChild().getRightChild());
        daddy.setRight(this);
        return daddy;
    }
    
    @Override
    public AbstractAVLNode rotateLeft() {
    	
        if(this.getRightChild() == null){
        	return this;
        }
        AbstractAVLNode daddy = this.getRightChild(); //create pointer to avoid removing by GBC
        this.setRight(this.getRightChild().getLeftChild());
        daddy.setLeft(this);
        return daddy;
    }

}
