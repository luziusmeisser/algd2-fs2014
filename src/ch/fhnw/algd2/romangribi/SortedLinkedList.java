// Created by Roman Gribi on 18.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson1.exercise.AbstractLinkedList;
import ch.fhnw.algd2.lesson1.exercise.AbstractSortedLinkedList;

public class SortedLinkedList<T extends Comparable<T>> extends AbstractSortedLinkedList<T> {
    private Entry first = new Entry(null);

    @Override
    public void merge(AbstractLinkedList<T> other) {
        AbstractLinkedList<T> otherSorted;
        
        // Create sorted list
        if(other instanceof AbstractSortedLinkedList) {
            otherSorted = other;   
        } else {
            otherSorted = mergesort(other);
        }
        
        // Add elements
        for(T item : otherSorted) {
            add(item);
        }
    }

    private AbstractLinkedList<T> mergesort(AbstractLinkedList<T> list) {
        if (list.size() <= 1)
            return list;

        AbstractLinkedList<T> left = new LinkedList<>();
        AbstractLinkedList<T> right = new LinkedList<>();
        int m = list.size() >>> 1;
        int idx = 0;

        for (T item : list) {
            if (idx++ < m)
                left.add(item);
            else
                right.add(item);
        }

        left = mergesort(left);
        right = mergesort(right);
        return domerge(left, right);
    }

    private T getFirstElement(AbstractLinkedList<T> list) {
        Iterator<T> it = list.iterator();
        return it.hasNext() ? it.next() : null;
    }

    private AbstractLinkedList<T> domerge(AbstractLinkedList<T> left, AbstractLinkedList<T> right) {
        LinkedList<T> newList = new LinkedList<>();

        while (left.size() > 0 && right.size() > 0) {
            T firstLeft = getFirstElement(left);
            T firstRight = getFirstElement(right);
            
            if (firstLeft.compareTo(firstRight) <= 0) {
                newList.add(firstLeft);
                left.remove(firstLeft);
            } else {
                newList.add(firstRight);
                right.remove(firstRight);
            }
        }

        while (left.size() > 0) {
            T firstLeft = getFirstElement(left);
            newList.add(firstLeft);
            left.remove(firstLeft);
        }
        while (right.size() > 0) {
            T firstRight = getFirstElement(right);
            newList.add(firstRight);
            right.remove(firstRight);
        }

        return newList;
    }

    @Override
    public boolean add(T e) {
        MyIterator it = (MyIterator) iterator();

        // first entry
        if (!it.hasNext()) {
            first = new Entry(e);
            first.next = null;
        }

        boolean found = false;
        Entry current = first;
        Entry next = null;
        Entry prev = null;

        // find position and add
        while (!found && it.hasNext()) {
            next = it.nextEntry();

            // before current
            if (current.value.compareTo(e) > 0) {
                Entry newEntry = new Entry(e);
                newEntry.next = current;

                if (prev == null)
                    first = newEntry;
                else
                    prev.next = newEntry;
                found = true;
            }
            // between current and next
            if (current.value.compareTo(e) <= 0 && next.value.compareTo(e) >= 0) {
                Entry newEntry = new Entry(e);
                newEntry.next = current.next;
                current.next = newEntry;
                found = true;
            }

            prev = current;
            current = next;
        }

        // not found, add to end
        if (!found) {
            current.next = new Entry(e);
        }

        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private Entry current = first;
        private Entry previous = null;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            return nextEntry().value;
        }

        private Entry nextEntry() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previous = current;
            current = current.next;
            return current;
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
    }

    private class Entry {
        private final T value;

        public Entry(T value) {
            this.value = value;
        }

        private Entry next = null;
    }

}
