// Created by Roman Gribi on 17.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
    private Entry current = null;

    @Override
    public void push(T o) {
        current = new Entry(o, current);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (current == null) {
            throw new EmptyStackException();
        } else {
            T val = current.value;
            current = current.prev;
            return val;
        }
    }

    private class Entry {
        private Entry prev;
        private final T value;

        public Entry(T value, Entry prev) {
            this.value = value;
            this.prev = prev;
        }
    }
}
