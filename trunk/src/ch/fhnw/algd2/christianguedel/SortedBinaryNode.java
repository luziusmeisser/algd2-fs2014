// Created by Christian Guedel on 16.03.2014

package ch.fhnw.algd2.christianguedel;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {
    
    private SortedBinaryNode parent;
    
    public SortedBinaryNode(String value) {
        this(value, null);
    }
    
    private SortedBinaryNode(String value, SortedBinaryNode parent)
    {
        super(value);
        this.parent = parent;
    }
    
    @Override
    public int insert(String value) {
        return insert(value, 0);
    }

    @Override
    public void remove(String value) {
        int comparison = value.compareTo(this.getValue());
        
        if (comparison == 0)
        {
            if (this.left != null && this.right != null)
            {
                SortedBinaryNode lowestKey = ((SortedBinaryNode)this.right).findLowestKey();
                remove(lowestKey.getValue());
                lowestKey.left = this.left;
                fixReferences(lowestKey);
            }
            else if (this.left != null)
            {
                fixReferences((SortedBinaryNode)this.left);
            }
            else if (this.right != null)
            {
                fixReferences((SortedBinaryNode)this.right);
            }
            else 
            {
                fixReferences(null);
            }
        }
        else if (comparison < 0 && this.left != null)
        {
            ((SortedBinaryNode)this.left).remove(value);
        }
        else if (comparison > 0 && this.right != null)
        {
            ((SortedBinaryNode)this.right).remove(value);
        }
    }

    private int insert(String value, int steps)
    {
        steps++;
        
        int comparison = value.compareTo(this.getValue());
        
        if (comparison == 0) return steps;
        if (comparison < 0)
        {
            if (this.left == null)
            {
                this.left = new SortedBinaryNode(value, this);
                return steps;
            } else {
                return ((SortedBinaryNode)this.left).insert(value, steps);
            }
        } else {
            if (this.right == null)
            {
                this.right = new SortedBinaryNode(value, this);
                return steps;
            } else {
                return ((SortedBinaryNode)this.right).insert(value, steps);
            }
        }
    }
    
    private void fixReferences(SortedBinaryNode newNode)
    {
        if (this.parent == null) return;
        
        if (this.parent.left == this) this.parent.left = newNode;
        else if (this.parent.right == this) this.parent.right = newNode;
    }
    
    private SortedBinaryNode findLowestKey()
    {
        if (this.left == null) return this;
        return ((SortedBinaryNode)this.left).findLowestKey();
    }
}
