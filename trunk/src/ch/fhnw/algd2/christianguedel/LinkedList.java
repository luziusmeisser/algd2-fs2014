// Created by Christian Guedel on 17.02.2014

package ch.fhnw.algd2.christianguedel;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

    private Entry head = new Entry(null);

    @Override
    public boolean add(T e) {
        head.value = e;
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
                if (hasNext())
                {
                    previous = current;
                    current = previous.next;
                    return current.value;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                if (previous == null)
                    throw new IllegalStateException();

                previous.next = current.next;
                current = previous;
                previous = null;
            }
        };
    }

    private class Entry {
        T value;
        Entry next;

        public Entry(Entry next) {
            this.next = next;
        }
    }
}
