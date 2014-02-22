// Created by Roman Gribi on 17.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

    private Entry head = new Entry(null);

    @Override
    public boolean add(T e) {
        Entry i = new Entry(e);
        i.next = head.next;
        head.next = i;
        
        return true;
    }
    
    public T first() {
        return head.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Entry current = head;
            private Entry previous = null;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                previous = current;
                current = current.next;
                return current.value;
            }

            @Override
            public void remove() {
                if (previous == null) {
                    throw new IllegalStateException();
                } else {
                    previous.next = current.next;
                    current = previous;
                    previous = null;
                }
            }
        };
    }

    private class Entry {
        private final T value;

        public Entry(T value) {
            this.value = value;
        }

        private Entry next = null;
    }
}
