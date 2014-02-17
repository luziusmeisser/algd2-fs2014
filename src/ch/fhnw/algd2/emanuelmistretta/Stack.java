// Created by Emanuel Mistretta on 17.02.2014

package ch.fhnw.algd2.emanuelmistretta;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T>{

    private final Deque<T> list = new ArrayDeque<T>();
    
    @Override
    public void push(T o) {
	list.push(o);
    }

    @Override
    public T pop() throws EmptyStackException {
	  if (list.size() == 0){
	      throw new EmptyStackException();
	  }
	  return list.pop();
    }

}
