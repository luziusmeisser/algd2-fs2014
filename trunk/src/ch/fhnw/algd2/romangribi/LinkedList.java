// Created by Roman Gribi on 17.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

    private Item<T> head = new Item<>(null);

    @Override
    public boolean add(T e) {
        Item<T> i = new Item<T>(e);
        i.next = head.next;
        head.next = i;
        
        return true;
    }
    
    public T first() {
        return head.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Item<T> current = head;
            private Item<T> previous = null;

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
                return current.item;
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

    private static class Item<T> {
        private final T item;

        public Item(T item) {
            this.item = item;
        }

        private Item<T> next = null;
    }
}
