// Created by Roman Gribi on 06.04.2014

package ch.fhnw.algd2.romangribi;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {

    private static final int MAP_SIZE = 1000;
    private Element[] elements = new Element[MAP_SIZE];

    @Override
    public void put(String key, String value) {
        int idx = createIndex(key);
        if (elements[idx] != null) {
            Element current = elements[idx];
            while (!current.key.equals(key) && current.hasNext())
                current = current.next;

            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = new Element(key, value);
            }
        } else {
            elements[idx] = new Element(key, value);
        }
    }

    @Override
    public String get(String key) {
        int idx = createIndex(key);

        Element current = elements[idx];
        if (current == null) {
            return null;
        } else {
            while(!current.key.equals(key) && current.hasNext())
                current = current.next;
            return current.key.equals(key) ? current.value : null;
        }
    }

    private int createIndex(String key) {
        return Math.abs(key.hashCode()) % MAP_SIZE;
    }

    private static class Element {
        public Element(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public final String key;

        public String value;
        public Element next = null;

        public boolean hasNext() {
            return next != null;
        }
    }
}
