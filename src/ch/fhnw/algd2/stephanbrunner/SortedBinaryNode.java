// Created by Stephan Brunner on 10.03.2014

package ch.fhnw.algd2.stephanbrunner;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {

    public SortedBinaryNode(String value) {
        super(value);
    }
    
    public SortedBinaryNode(String value, AbstractSortedBinaryNode left, AbstractSortedBinaryNode right) {
        super(value);
        this.left = left;
        this.right = right;
    }

    @Override
    public int insert(String value) {
        int ret = 0;
        if (value.compareTo(this.getValue()) < 0) {
            if (this.getLeftChild() == null) {
                left = new SortedBinaryNode(value);
            }
            else
                ret += this.getLeftChild().insert(value);
        } else if (value.compareTo(this.getValue()) > 0) {
            if (this.getRightChild() == null)
                right = new SortedBinaryNode(value);
            else
                ret += this.getRightChild().insert(value);
        }
        return ret + 1;
    }

    @Override
    public void remove(String value) {
        if (left != null && this.getLeftChild().getValue().equals(value)) {
            left = newChild(this.getLeftChild());
            if (left != null)
                this.getLeftChild().remove(left.getValue());
        }
        else if (right != null && this.getRightChild().getValue().equals(value)) {
            right = newChild(this.getRightChild());
            if (right != null)
                this.getRightChild().remove(right.getValue());
        }
        else {
            if (left != null)
                this.getLeftChild().remove(value);
            if (right != null)
                this.getRightChild().remove(value);
        }
    }
    
    private AbstractSortedBinaryNode newChild(AbstractSortedBinaryNode child) {
        if (hasNoChild(child)) {
            return null;
        } else if (hasTwoChilds(child)){
            String newValue = child.getRightChild().findLowestValue(); 
            AbstractSortedBinaryNode ret = new SortedBinaryNode(newValue, child.getLeftChild(), child.getRightChild()); 
            return ret;
        } else { // => has one child
            if (child.getLeftChild() != null) {
                return child.getLeftChild();
            } else
                return child.getRightChild();
        }
    }

    private boolean hasTwoChilds(AbstractSortedBinaryNode child) {
        return !(child.getLeftChild() == null) && !(child.getRightChild() == null);
    }

    private boolean hasNoChild(AbstractSortedBinaryNode child) {
        return child.getLeftChild() == null && child.getRightChild() == null;
    }
}
