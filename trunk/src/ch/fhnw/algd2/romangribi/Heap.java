// Created by Roman Gribi on 30.03.2014

package ch.fhnw.algd2.romangribi;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {

    private final String[] values;

    private int pos;

    public Heap(int size) {
        this.pos = 0;
        this.values = new String[size];
    }

    @Override
    public synchronized void offer(String s) {
        values[pos++] = s;
        siftUp(pos - 1);
    }

    @Override
    public synchronized String peek() {
        return pos > 0 ? values[0] : null;
    }

    @Override
    public synchronized String poll() {
        if (pos <= 0)
            return null;
        String value = values[0];
        values[0] = values[--pos];

        siftDown(0);

        return value;
    }

    private void siftUp(int node) {
        int posParent = (node - 1) / 2;
        if (posParent < 0)
            return;

        if (values[node].compareTo(values[posParent]) < 0) {
            String tmp = values[node];
            values[node] = values[posParent];
            values[posParent] = tmp;
            siftUp(posParent);
        }
    }

    private void siftDown(int node) {
        int posLeft = node * 2 + 1;
        int posRight = posLeft + 1;

        if (posLeft >= pos) {
            // no children
            return;
        } else if (posRight >= pos) {
            // only left child
            if (values[node].compareTo(values[posLeft]) > 0) {
                // left child is bigger, swap items
                // siftDown is not necessary because left cannot have more nodes
                String tmp = values[node];
                values[node] = values[posLeft];
                values[posLeft] = tmp;
            }
        } else {
            // both children are set, find the bigger of them
            int posBigger = values[posLeft].compareTo(values[posRight]) < 0 ? posLeft : posRight;
            if (values[node].compareTo(values[posBigger]) > 0) {
                // child is bigger, swap items
                // siftDown is necessary because there are maybe more children available
                String tmp = values[node];
                values[node] = values[posBigger];
                values[posBigger] = tmp;
                siftDown(posBigger);
            }
        }
    }
}
