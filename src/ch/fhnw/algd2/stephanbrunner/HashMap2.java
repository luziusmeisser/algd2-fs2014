// Created by Stephan Brunner on 28.04.2014

package ch.fhnw.algd2.stephanbrunner;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
    private static double MAX_LOAD_FACTOR = 0.6;
    private static double MIN_LOAD_FACTOR = 0.25;
    private static int DEFAULT_INITIAL_SIZE = 997;

    private Element[] elements = new Element[DEFAULT_INITIAL_SIZE];
    private int countLoad = 0;
    private int countDeleted = 0;
    
    @Override
    public void put(String key, String value) {
        int position = getPosition(key);
        if (elements[position] == null) 
            countLoad++;
        elements[position] = new Element(key, value);
        resizeIfNeeded();
    }
    
    @Override
    public String get(String key) {
        int position = getPosition(key);
        if (position == -1 || elements[position] == null || elements[position].deleted)
            return null;
        if (elements[position].value == null)
            return null;
        else 
            return elements[position].value;
    }

    @Override
    public String remove(String key) {
        int position = getPosition(key);
        if (position == -1 || elements[position] == null || elements[position].deleted)
            return null;
        else { 
            elements[position].deleted = true;
            countDeleted++;
        }
        if (elements[position].value == null)
            return null;
        else
            return elements[position].value;
    }
    
    private int getPosition(String key) {
        int position = Math.abs(key.hashCode()) % elements.length;
        int startingPosition = position;
        int i = 1;
        while (!aGoodPlace(position, key)) {
            position = (startingPosition + i*i) % elements.length;
            i++;
            if (position == startingPosition)
                return -1;
        }
        return position;
    }
    
    private boolean aGoodPlace(int position, String key) {
        if (elements[position] == null)
            return true;
        else if (elements[position].key.equals(key))
            return true;
        else 
            return false;
    }
    
    private void resizeIfNeeded() {
        if (((double)countLoad / elements.length) > MAX_LOAD_FACTOR)
            resize(elements.length * 2 + 1);
        if (((double)(countLoad - countDeleted) / elements.length) < MIN_LOAD_FACTOR && elements.length > DEFAULT_INITIAL_SIZE)
            resize((elements.length - 1) / 2);
    }
    
    private void resize(int newSize) {
        Element[] oldElements = elements;
        elements = new Element[newSize];
        countLoad = 0;
        for (Element old: oldElements) {
            if (old != null && !old.deleted) {
                elements[getPosition(old.key)] = new Element(old.key, old.value);
                countLoad++;
            }
        }
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
