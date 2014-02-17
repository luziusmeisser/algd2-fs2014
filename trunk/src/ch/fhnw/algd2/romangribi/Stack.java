// Created by Roman Gribi on 17.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
    private Entry<T> _current = null;

    @Override
    public void push(T o) {
        Entry<T> e = new Entry<T>(o);
        e.next = _current;
        _current = e;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (_current == null)
            throw new EmptyStackException();

        Entry<T> e = _current;
        _current = _current.next;
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
