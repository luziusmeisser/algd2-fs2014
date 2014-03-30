// Created by Christian Guedel on 30.03.2014

package ch.fhnw.algd2.christianguedel;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {

    private String[] data;
    private int currentPos;
    
    public Heap()
    {
        this.data = new String[500];
        this.currentPos = 0;
    }
    
    @Override
    public synchronized void offer(String s) {
        if (this.currentPos > (this.data.length >> 1)) this.resizeArray(this.data.length << 1);
        
        // add s to the end of the data and sift it up
        this.data[this.currentPos] = s;
        this.siftUp(this.currentPos);
        
        this.currentPos++;
    }

    @Override
    public String peek() {
        return this.data[0];
    }

    @Override
    public synchronized String poll() {
        
        String ret = null;
        if (this.data[0] != null) {
            this.currentPos--;
            ret = this.peek();
            this.data[0] = this.data[this.currentPos];
            this.data[this.currentPos] = null;
            this.siftDown(0);
        }
        
        return ret;
    }
    
    private void siftUp(int pos)
    {
        assert pos < this.data.length;
        
        if (pos <= 0) return;
        
        if (this.data[pos].compareTo(data[this.getParent(pos)]) < 0)
        {
            this.swapElement(pos, this.getParent(pos));
            this.siftUp(this.getParent(pos));
        }
    }
    
    private void siftDown(int pos) 
    {
        assert pos < this.data.length;
        
        int leftPos = this.getLeft(pos);
        int rightPos = this.getRight(pos);
        
        if (leftPos >= this.currentPos) 
        {
            return;
        }
        else if (rightPos >= this.currentPos)
        {
            if (this.data[pos].compareTo(this.data[leftPos]) > 0)
            {
                this.swapElement(pos, leftPos);
                return;
            }
        } else {
            int lowerPos = this.data[leftPos].compareTo(this.data[rightPos]) < 0 ? leftPos : rightPos;
            if (this.data[pos].compareTo(this.data[lowerPos]) > 0)
            {
                this.swapElement(pos, lowerPos);
                siftDown(lowerPos);
            }
        }
    }
    
    private void swapElement(int index1, int index2)
    {
        assert index1 < this.data.length;
        assert index2 < this.data.length;
        
        String tmp = this.data[index1];
        this.data[index1] = this.data[index2];
        this.data[index2] = tmp;
    }

    private void resizeArray(int newSize)
    {
        assert newSize > this.data.length;
        
        String[] newData = new String[newSize];
        
        for (int i = 0; i < this.data.length; i++)
        {
            newData[i] = this.data[i];
        }
        
        this.data = newData;
    }
    
    private int getLeft(int pos)
    {
        return (pos << 1) + 1;
    }
    
    private int getRight(int pos)
    {
        return (pos << 1) + 2;
    }
    
    private int getParent(int pos)
    {
        assert pos < this.data.length;
        return (pos - 1) >> 1;
    }
}
