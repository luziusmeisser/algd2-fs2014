// Created by Roman Gribi on 17.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

    private Item<T> _first = new Item<T>(null);
    
    @Override
    public boolean add(T e) {
        Item<T> i = new Item<T>(e);
        i.next = _first;
        _first = i;
        
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Item<T> _current = _first;
            private Item<T> _previous = null;
            
            @Override
            public boolean hasNext() {
                return _current.next != null;
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                _previous = _current;
                _current = _current.next;
                return _current.item;
            }

            @Override
            public void remove() {
                if(_previous == null) {
                    throw new IllegalStateException();
                } else {
                    _previous.next = _current.next;
                    _current = _previous;
                    _previous = null;
                }
            }
        };
    }
    
    private static class Item<T> {
        private final T item;
        
        public Item(T item) {
            this.item = item;
        }
        
        private Item<T> next;
    }
}
