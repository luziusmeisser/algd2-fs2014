// Created by Roman Gribi on 24.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {
    private final static int MAX_LEVEL = 20;
    private final static Random RANDOM = new Random(System.currentTimeMillis());

    private final Element<T> head = new Element<T>(MAX_LEVEL, null);

    @Override
    public void add(T item) {
        @SuppressWarnings("unchecked")
        Element<T>[] updates = new Element[MAX_LEVEL];
        Element<T> current = head;

        // find correct position
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (current.forwards[i] != null && current.forwards[i].value.compareTo(item) < 0) {
                current = current.forwards[i];
            }

            updates[i] = current;
        }

        // update pointers
        Element<T> el = new Element<T>(item);
        for (int i = 0; i < el.getSize(); i++) {
            el.forwards[i] = updates[i].forwards[i];
            updates[i].forwards[i] = el;
        }
    }

    @Override
    public T removeFirst() {
        if (head.forwards[0] == null)
            throw new NoSuchElementException();
        Element<T> el = head.forwards[0];
        
        // update pointers
        int numForwards = head.forwards[0].getSize();
        for (int i = 0; i < numForwards; i++) {
            if(head.forwards[i] == el) {
                head.forwards[i] = el.forwards[i];
            }
        }
        return el.value;
    }

    @Override
    public int countStepsTo(T item) {
        Element<T> current = head;
        int count = 0;
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (current.forwards[i] != null && current.forwards[i].value.compareTo(item) < 0) {
                current = current.forwards[i];
                count++;
            }
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    private static class Element<T> {
        private final T value;
        private final Element<T>[] forwards;

        public Element(int level, T value) {
            this.forwards = new Element[level];
            this.value = value;
        }

        public Element(T value) {
            int level = 1;
            while (level < MAX_LEVEL && RANDOM.nextBoolean()) {
                level++;
            }

            this.forwards = new Element[level];
            this.value = value;
        }

        public int getSize() {
            return forwards.length;
        }
    }
}
