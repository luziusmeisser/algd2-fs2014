// Created by Yannick Augstburger on Feb 25, 2014

package ch.fhnw.algd2.yannickaugstburger;

import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {

	private final static int MAX_LEVEL = 60;
    private final static Random RAND = new Random(System.currentTimeMillis());

    private final Element<T> head = new Element<T>(MAX_LEVEL, null);

    @Override
    public void add(T item) {
        @SuppressWarnings("unchecked")
        Element<T>[] update = new Element[MAX_LEVEL];
        Element<T> curr = head;
        
        //fill in update[]
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (curr.next[i] != null && curr.next[i].value.compareTo(item) < 0) {
                curr = curr.next[i];
            }
            update[i] = curr;
        }
        //update
        Element<T> element = new Element<T>(item);
        for (int i = 0; i < element.getSize(); i++) {
            element.next[i] = update[i].next[i];
            update[i].next[i] = element;
        }
    }

    @Override
    public T removeFirst() {
        if (head.next[0] == null){
            throw new NoSuchElementException();
        }
        Element<T> element = head.next[0];
        
        //update 
        int numberNext = head.next[0].getSize();
        for (int i = 0; i < numberNext; i++) {
            if(head.next[i] == element) {
            	head.next[i] = element.next[i];
            }
        }
        return element.value;
    }

    @Override
    public int countStepsTo(T item) {
        Element<T> curr = head;
        int counter = 0;
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (curr.next[i] != null && curr.next[i].value.compareTo(item) < 0) {
                curr = curr.next[i];
                counter++;
            }
        }
        return counter;
    }

    @SuppressWarnings("unchecked")
    private static class Element<T> {
        private final T value;
        private final Element<T>[] next;

        public Element(int level, T value) {
            this.next = new Element[level];
            this.value = value;
        }

        public Element(T value) {
            int level = 1;
            while (level < MAX_LEVEL && RAND.nextBoolean()) {
                level++;
            }
            this.next = new Element[level];
            this.value = value;
        }

        public int getSize() {
            return next.length;
        }
    }

}
