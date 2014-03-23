// Created by Roman Gribi on 23.03.2014

package ch.fhnw.algd2.romangribi;

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
        return Math.max(getLeftHeight(), getRightHeight()) + 1;
    }

    @Override
    protected AbstractAVLNode ensureBalance() {
        if(getBalance() > 1) {
            // the left node is too high
            AbstractAVLNode left = getLeftChild();
            if(left.getBalance() == -1) {
                // left right rotation
                setLeft(left.rotateLeft());
            }
            return rotateRight();
        } else if(getBalance() < -1){
            // the right node is too high
            AbstractAVLNode right = getRightChild();
            if(right.getBalance() == 1) {
                // right left rotation
                setRight(right.rotateRight());
            }
            return rotateLeft();
        }
        // Is already balanced
        return this;
    }

    @Override
    public AbstractAVLNode rotateLeft() {
        AbstractAVLNode parent = getRightChild();
        if(parent == null) return this;
        
        setRight(parent.getLeftChild());
        parent.setLeft(this);
        return parent;
    }

    @Override
    public AbstractAVLNode rotateRight() {
        AbstractAVLNode parent = getLeftChild();
        if(parent == null) return this;
        
        setLeft(parent.getRightChild());
        parent.setRight(this);
        return parent;
    }

}
