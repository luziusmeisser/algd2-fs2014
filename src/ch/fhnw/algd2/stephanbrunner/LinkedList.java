// Created by Stephan Brunner on 22.02.2014

package ch.fhnw.algd2.stephanbrunner;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {
    private Node root = new Node(null);
    
    @Override
    public boolean add(T e) {
        root.content = e;
        root = new Node(root);
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    
    private class Node {
        private T content;
        private Node nextNode;
                
        public Node(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
    
    private class LinkedListIterator implements Iterator<T> {
        Node previousNode;
        Node currentNode = root;
        
        @Override
        public boolean hasNext() {
            return currentNode.nextNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
                return currentNode.content;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (currentNode.content == null)
                throw new IllegalStateException();
            if (currentNode.nextNode == null)
                previousNode.nextNode = null;
            else 
                previousNode.nextNode = currentNode.nextNode;
        }   
    }
}
