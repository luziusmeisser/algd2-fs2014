// Created by Stephan Brunner on 17.03.2014

package ch.fhnw.algd2.stephanbrunner;

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
        if (getBalance() > 1) { 
            // The left column
            if (getLeftChild().getBalance() == -1) { 
                // The "Left Right Case"
                setLeft(getLeftChild().rotateLeft());
            }
            // The "Left Left Case"
            return rotateRight();
        } else if (getBalance() < -1) {
            // The right column
            if (getRightChild().getBalance() == 1) { 
                // The "Right Left Case"
                setRight(getRightChild().rotateRight());
            }
            // The "Right Right Case"
            return rotateLeft();
        }
        else 
            return this;
    }

    @Override
    public AbstractAVLNode rotateLeft() {
        AbstractAVLNode ret = getRightChild();
        this.setRight(getRightChild().getLeftChild());
        ret.setLeft(this);
        return ret;
    }

    @Override
    public AbstractAVLNode rotateRight() {
        AbstractAVLNode ret = getLeftChild();
        this.setLeft(getLeftChild().getRightChild());
        ret.setRight(this);
        return ret;
    }

}
