// Created by Stephan Brunner on 24.03.2014

package ch.fhnw.algd2.stephanbrunner;

import java.util.AbstractList;
import java.util.ArrayList;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {
    AbstractList<String> data;
    
    public Heap() {
        data = new ArrayList<String>();
    }

    @Override
    public void offer(String s) {
        data.add(s);
        seepUp(data.size() - 1);
    }

    @Override
    public String peek() {
        return data.get(0);
    }

    @Override
    public String poll() {
        String ret = data.get(0);
        data.set(0, data.remove(data.size() - 1));
        seepDown(0);
        return ret;
    }

    private void seepUp(int index) {
        if(data.get(index).compareTo(data.get(getParent(index))) > 0) {
            swap(index, getParent(index));
            seepUp(getParent(index));
        }
    }
    
    private void seepDown(int index) {
        if (data.get(index).compareTo(data.get(getLChild(index))) < 0) {
            swap(index, getLChild(index));
            seepDown(getLChild(index));
        }
        else if (data.get(index).compareTo(data.get(getRChild(index))) < 0) {
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
