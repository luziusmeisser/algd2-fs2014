// Created by Roman Gribi on 17.02.2014

package ch.fhnw.algd2.romangribi;

import java.util.ArrayList;
import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
    private final ArrayList<T> _list = new ArrayList<T>();

    @Override
    public void push(T o) {
        _list.add(o);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (_list.size() == 0)
            throw new EmptyStackException();
        
        return _list.remove(_list.size() - 1);
    }

}
