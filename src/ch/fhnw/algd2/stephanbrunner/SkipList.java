// Created by Stephan Brunner on 24.02.2014

package ch.fhnw.algd2.stephanbrunner;

import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;


public class SkipList<T extends Comparable<T>> implements ISkipList<T> {

    public final int MAX_HEIGHT = 100;
    
    private Node<T> head = new Node<T>(MAX_HEIGHT);
    private int maxHeight = 0;
    
    @Override
    public void add(T item) {
        Node<T> newNode = new Node<T>();
        newNode.content = item;
        head.add(newNode.linkedNodes.length - 1, newNode);
        maxHeight = Math.max(maxHeight, newNode.linkedNodes.length);
    }

    @Override
    public T removeFirst() {
        if (head.linkedNodes[0] == null)
            throw new NoSuchElementException();
        Node<T> firstNode = head.linkedNodes[0];
        for (int i = 0; i < firstNode.linkedNodes.length; i++) {
            head.linkedNodes[i] = firstNode.linkedNodes[i];
        }
        return firstNode.content;
    }

    @Override
    public int countStepsTo(T item) {
        return head.countStepsTo(maxHeight - 1, item);
    }
    
    private class Node<N extends Comparable<N>> {
        private N content;
        private Node<N>[] linkedNodes;
        
        @SuppressWarnings("unchecked")
        public Node() {
            this.linkedNodes = new Node[getRandHeight()];
        }
        
        @SuppressWarnings("unchecked")
        public Node(int height) {
            this.linkedNodes = new Node[height];
        }
        
        public int countStepsTo(int entryLevel, N item) {
            if (this.content != null && this.content.equals(item)) {
                return 1;
            } else if (this.linkedNodes[entryLevel] == null || this.linkedNodes[entryLevel].content.compareTo(item) > 0) {
                if (entryLevel > 0) {
                    return this.countStepsTo(entryLevel - 1, item);
                } else {
                    return 1;
//                    throw new NoSuchElementException();
                }
            } else {
                return this.linkedNodes[entryLevel].countStepsTo(entryLevel, item) + 1;
            }
        }
        
        public void add(int entryLevel, Node<N> newNode) {
            if (this.linkedNodes[entryLevel] == null) {
                // this level is unlinked -> link it
                this.linkedNodes[entryLevel] = newNode;
                if (entryLevel != 0) 
                    this.add(entryLevel - 1, newNode);
            } else if (this.linkedNodes[entryLevel].content.compareTo(newNode.content) >= 0) {
                // this level is linked with bigger/equal Node -> insert Node
                newNode.linkedNodes[entryLevel] = this.linkedNodes[entryLevel];
                this.linkedNodes[entryLevel] = newNode;
                if (entryLevel != 0) 
                    this.add(entryLevel - 1, newNode);
            } else {
                // this level is linked with smaller Node -> try there
                this.linkedNodes[entryLevel].add(entryLevel, newNode);
            }  
        }
        
        public int getRandHeight() {
            int height = 1;
            Random rand = new Random();
            while (rand.nextBoolean() && height < MAX_HEIGHT) {
                height++;
            }
            return height;
        }
    }
}
