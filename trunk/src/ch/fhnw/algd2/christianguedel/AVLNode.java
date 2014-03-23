// Created by Christian Guedel on 23.03.2014

package ch.fhnw.algd2.christianguedel;

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
        int leftHeight = this.getLeftHeight();
        int rightHeight = this.getRightHeight();
        
        return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
    }

    @Override
    // reference: http://en.wikipedia.org/wiki/AVL_tree#Insertion
    protected AbstractAVLNode ensureBalance() {
        int balance = this.getBalance();
        if (balance > 1) {
            if (this.getLeftChild().getBalance() > -1) {
                return this.rotateRight();
            } else if (this.getLeftChild().getBalance() == -1) {
                setLeft(this.getLeftChild().rotateLeft());
                return this.rotateRight();
            }
        } else if (balance < -1) {
            if (this.getRightChild().getBalance() < 1) {
                return this.rotateLeft();
            } else if (this.getRightChild().getBalance() == 1) {
                setRight(this.getRightChild().rotateRight());
                return this.rotateLeft();
            }
        }
        
        return this;
    }

    @Override
    public AbstractAVLNode rotateLeft() {
        AbstractAVLNode node = this.getRightChild();
        if (node == null) return null;
        
        this.setRight(node.getLeftChild());
        node.setLeft(this);
        
        return node;
    }

    @Override
    public AbstractAVLNode rotateRight() {
        AbstractAVLNode node = this.getLeftChild();
        if (node == null) return null;
        
        this.setLeft(node.getRightChild());
        node.setRight(this);
        
        return node;
    }
}
