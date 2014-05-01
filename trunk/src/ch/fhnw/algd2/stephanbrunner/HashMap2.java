// Created by Stephan Brunner on 28.04.2014

package ch.fhnw.algd2.stephanbrunner;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
    private Element[] elements = new Element[100000];

    @Override
    public void put(String key, String value) {
        int position = getPosition(key);
        elements[position] = new Element(key, value);
    }

    @Override
    public String get(String key) {
        return elements[getPosition(key)].value;
    }

    @Override
    public String remove(String key) {
        int position = getPosition(key);
        if (elements[position] == null)
            return null;
        else 
            elements[position].deleted = true;
        if (elements[position].value == null)
            return null;
        else
            return elements[position].value;
    }
    
    private int h(String key) {
        return Math.abs(key.hashCode()) % elements.length;
    }
    
    private int getPosition(String key) {
        int hash = h(key);
        while (elements[hash] != null && !elements[hash].deleted && !elements[hash].key.equals(key)) {
            hash = (hash + 1) % elements.length;
        }
        return hash;
    }

    private class Element {
        String key;
        String value;
        boolean deleted;
        
        public Element(String key, String value) {
            this.key = key;
            this.value = value;
            this.deleted = false;
        }
    }
}
