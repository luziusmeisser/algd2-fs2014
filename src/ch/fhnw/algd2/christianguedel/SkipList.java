// Created by Christian Guedel on 24.02.2014

package ch.fhnw.algd2.christianguedel;

import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {

    private static final int MAX_DEPTH = 20;
    private SkipListEntry<T> head = new SkipListEntry<T>(null, MAX_DEPTH);

    @SuppressWarnings("unchecked")
    @Override
    public void add(T item) {

        Random r = new Random();
        int depth = 1;
        while (r.nextBoolean() && depth < MAX_DEPTH)
            depth++;

        SkipListEntry<T>[] fixReferences = new SkipListEntry[depth];

        // find insert point
        int currentDepth = MAX_DEPTH - 1;
        SkipListEntry<T> current = head;

        while (currentDepth >= 0) {
            if (current.references[currentDepth] == null || current.references[currentDepth].value.compareTo(item) > 0) {
                if (currentDepth < depth)
                    fixReferences[currentDepth] = current;
                currentDepth--;
            } else {
                current = current.references[currentDepth];
            }
        }

        SkipListEntry<T> newItem = new SkipListEntry<T>(item, depth);

        for (int i = 0; i < fixReferences.length; i++) {
            newItem.references[i] = fixReferences[i].references[i];
            fixReferences[i].references[i] = newItem;
        }
    }

    @Override
    public T removeFirst() {
        SkipListEntry<T> first = head.references[0];

        if (first == null)
            throw new NoSuchElementException();

        for (int i = 0; i < first.references.length; i++) {
            head.references[i] = first.references[i];
        }

        return first.value;
    }

    @Override
    public int countStepsTo(T item) {
        int currentDepth = MAX_DEPTH - 1;
        int steps = 0;
        SkipListEntry<T> current = head;

        while (currentDepth >= 0) {
            if (current.references[currentDepth] == null || current.references[currentDepth].value.compareTo(item) > 0) {
                steps++;
                currentDepth--;
            } else if (current.references[currentDepth].value.compareTo(item) <= 0) {
                return steps;
            } else {
                current = current.references[currentDepth];
            }
        }

        return steps;
    }

    private class SkipListEntry<T extends Comparable<T>> {
        public SkipListEntry<T>[] references;
        public T value;

        @SuppressWarnings("unchecked")
        public SkipListEntry(T value, int depth) {
            this.references = new SkipListEntry[depth];
            this.value = value;
        }
    }
}
