// Created by Stephan Brunner on 24.03.2014

package ch.fhnw.algd2.stephanbrunner;

import java.util.ArrayList;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {
    ArrayList<String> data;
    
    public Heap() {
        data = new ArrayList<String>();
    }

    @Override
    synchronized public void offer(String s) {
        data.add(s);
        seepUp(data.size() - 1);
    }

    @Override
    public String peek() {
        if (data.size() == 0)
            return null;
        return data.get(0);
    }

    @Override
    synchronized public String poll() {
        if (data.size() == 0)
            return null;
        if (data.size() == 1)
            return data.remove(0);
        String ret = data.get(0);
        String last = data.remove(data.size() - 1);
        data.set(0, last);
        seepDown(0);
        return ret;
    }

    private void seepUp(int index) {
        if (index <= 0)
            return;
        if(data.get(index).compareTo(data.get(getParent(index))) < 0) {
            swap(index, getParent(index));
            seepUp(getParent(index));
        }
    }
    
    private void seepDown(int index) {
        String parent = data.get(index);
        String childL = getLChild(index) < data.size() ? data.get(getLChild(index)) : "" + (char)255;
        String childR = getRChild(index) < data.size() ? data.get(getRChild(index)) : "" + (char)255;
        // exit condition
        if (parent.compareTo(childL) <= 0 && parent.compareTo(childR) <= 0)
            return;
        // recursion condition
        else if(childL.compareTo(childR) <= 0) { 
            swap(index, getLChild(index));
            seepDown(getLChild(index));
        }
        else { 
            swap(index, getRChild(index));
            seepDown(getRChild(index));
        }
    }
    
    private void swap(int iA, int iB) {
        String a = data.get(iA);
        data.set(iA, data.get(iB));
        data.set(iB, a);
    }
    
    private int getLChild(int index) {
        return index * 2 + 1;
    }
    
    private int getRChild(int index) {
        return index * 2 + 2;
    }
    
    private int getParent(int index) {
        return (index - 1) / 2;
    }
}
