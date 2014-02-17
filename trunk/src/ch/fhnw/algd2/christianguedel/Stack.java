// Created by Christian Guedel on 17.02.2014

package ch.fhnw.algd2.christianguedel;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.*;

public class Stack<T> implements IStack<T> {

    private Entry top;
    
    @Override
    public void push(T o) {
        top = new Entry(top, o);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (top == null)
            throw new EmptyStackException();

        T value = top.value;
        top = top.prev;
        
        return value;
    }

    private class Entry {
        private T value;
        private Entry prev;
        
        public Entry(Entry prev, T value)
        {
            this.value = value;
            this.prev = prev;
        }
    }
}
