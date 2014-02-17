// Created by Christian Guedel on 17.02.2014

package ch.fhnw.algd2.christianguedel;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

    private Entry head = new Entry(null);
    
    @Override
    public boolean add(T e) {
        head.SetValue(e);
        head = new Entry(head);
        
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Entry current = head;
            private Entry previous;
            
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                
                previous = current;
                current = previous.next;
                return current.GetValue();
            }

            @Override
            public void remove() {
                if (current == null)
                    throw new IllegalStateException();
                
                previous.SetNext(current.next);
                current = previous;
            }
        };
    }

    private class Entry {
        private T value;
        private Entry next;
        
        public Entry(Entry next)
        {
            this.next = next;
        }
        
        public T GetValue()
        {
            return this.value;
        }
        
        public void SetValue(T value)
        {
            this.value = value;
        }
        
        public void SetNext(Entry next)
        {
            this.next = next;
        }
    }
}