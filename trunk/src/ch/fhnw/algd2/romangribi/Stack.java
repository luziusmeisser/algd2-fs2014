// Created by Roman Gribi on 17.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
    private Entry<T> current = null;

    @Override
    public void push(T o) {
        Entry<T> e = new Entry<T>(o);
        e.next = current;
        current = e;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (current == null)
            throw new EmptyStackException();

        Entry<T> e = current;
        current = current.next;
        return e.value;
    }

    private static class Entry<T> {
        private Entry<T> next;
        private final T value;

        public Entry(T value) {
            this.value = value;
        }
    }
}
