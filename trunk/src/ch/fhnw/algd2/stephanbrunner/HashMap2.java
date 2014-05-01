// Created by Stephan Brunner on 28.04.2014

package ch.fhnw.algd2.stephanbrunner;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
    private static double MAX_LOAD_FACTOR = 0.7;
    private static double MIN_LOAD_FACTOR = 0.3;
    private static int DEFAULT_INITIAL_SIZE = 1000;

    private Element[] elements = new Element[DEFAULT_INITIAL_SIZE];
    private int loadInclDeleted = 0;
    private int loadExclDeleted = 0;
    
    @Override
    public void put(String key, String value) {
        int position = getPositionToPutElement(key);
//        System.out.println("putting " + key + " to position " + position);
        if (elements[position] == null) {
            loadInclDeleted++;
            loadExclDeleted++;
        }
        elements[position] = new Element(key, value);
        resizeIfNeeded();
    }
    
    @Override
    public String get(String key) {
        int position = getPositionToGetElement(key);
//      System.out.println("getting " + key + " from position " + position);
        if (position == -1)
            return null;
        else if (elements[position] != null)
            return elements[position].value;
        else 
            return null;
    }

    @Override
    public String remove(String key) {
        int position = getPositionToGetElement(key);
//        System.out.println("removing " + key + " from position " + position);
        if (position == -1 || elements[position] == null)
            return null;
        else { 
            elements[position].deleted = true;
            loadExclDeleted--;
        }
        if (elements[position].value == null)
            return null;
        else
            return elements[position].value;
    }
    
    private int getPositionToPutElement(String key) {
        return (getPosition(key, elements, true));
    }
    
    private int getPositionToGetElement(String key) {
        return getPosition(key, elements, false);
    }
    
    private int getPosition(String key, Element[] array, boolean checkDeletedFlag) {
        int hash = Math.abs(key.hashCode()) % array.length;
        int startingPoint = hash;
        while (array[hash] != null && !(array[hash].deleted && checkDeletedFlag) && !array[hash].key.equals(key)) {
            hash = (hash + 1) % array.length;
            if (hash == startingPoint)
                return -1;
        }
//        System.out.print("is not null? " + (array[hash] != null));
//        if (array[hash] != null) {
//             System.out.println(", is not deleted? " + !(array[hash].deleted && checkDeletedFlag) + ", key is not equal? " + !array[hash].key.equals(key));
//        } else 
//            System.out.println();
        return hash;
    }
    
    private void resizeIfNeeded() {
        if (((double)loadInclDeleted / elements.length) > MAX_LOAD_FACTOR)
            resize(elements.length * 2);
        if (((double)loadExclDeleted / elements.length) < MIN_LOAD_FACTOR && elements.length > DEFAULT_INITIAL_SIZE)
            resize(elements.length / 2);
    }
    
    private void resize(int newSize) {
//        System.out.println("resizing: " + newSize + " " + (double)loadInclDeleted/elements.length);
        Element[] newElements = new Element[newSize];
        loadInclDeleted = 0;
        for (Element e: elements) {
            if (e != null && !e.deleted) {
                newElements[getPosition(e.key, newElements, false)] = new Element(e.key, e.value);
                loadInclDeleted++;
            }
        }
        elements = newElements;
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
