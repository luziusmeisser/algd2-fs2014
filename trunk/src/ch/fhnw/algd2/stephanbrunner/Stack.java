// Created by Stephan Brunner on 17.02.2014

package ch.fhnw.algd2.stephanbrunner;

import java.util.EmptyStackException;

import ch.fhnw.algd2.lesson1.exercise.IStack;

public class Stack<T> implements IStack<T> {
    private Node top;
    
    /**
     * Pushes an object o onto the stack.
     */
    @Override
    public void push(T o){
        top = new Node(o, top);
    }
    
    /**
     * Removes the top element from the stack.
     * Throws an EmptyStackException if there is no element left.
     */
    @Override
    public T pop() throws EmptyStackException{
        if (top == null) 
            throw new EmptyStackException();
        T result = top.content;
        top = top.nextNode;
        return result;
    }
    
    /**
     * Holds an Object and a Reference to the next Node.
     * 
     * @author Stephan
     */
    private class Node {
        private T content;
        private Node nextNode;
                
        public Node(T content, Node nextNode) {
            this.content = content;
            this.nextNode = nextNode;
        }
    }
}
