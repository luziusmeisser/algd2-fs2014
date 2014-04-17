// Created by Stephan Brunner on 17.04.2014

package ch.fhnw.algd2.stephanbrunner;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {
    
    private Element[] elements = new Element[1000];
    
    @Override
    public void put(String key, String value) {
        Element target = elements[h(key)];
        Element prev = target;
        if (target == null) {
            elements[h(key)] = new Element(key, value);
        }
        else {
            while (target != null && !target.key.equals(key)) {
                prev = target;
                target = target.next;
            }
            if (target == null) {
                prev.next = new Element(key, value);
            }
            else {
                target.value = value;
            }
        }
    }

    @Override
    public String get(String key) {
        Element target = elements[h(key)];
        while (target != null) {
            if (target.key == key) 
                return target.value;
            target = target.next;
        }
        return null;
    }
    
    private int h(String key) {
        return Math.abs(key.hashCode()) % elements.length;
    }
    
    private class Element {
        String key;
        String value;
        Element next;
        
        public Element(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}
