// Created by Christian Guedel on 17.02.2014

package ch.fhnw.algd2.christianguedel;

import java.util.ArrayList;
import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.*;

public class Stack<T> implements IStack<T> {

    private final ArrayList<T> list = new ArrayList<T>();
    
    @Override
    public void push(T o) {
        list.add(o);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (list.size() == 0)
            throw new EmptyStackException();
        else
        {
            T element = list.remove(list.size() - 1);
            return element;
        }
    }

}
