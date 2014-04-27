// Created by Marco on 27.04.2014
//inspired by Emanuel Mistretta

package ch.fhnw.algd2.marcogaiffi;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

/**
 * Exercise 7:
 * 
 * Implement your own hashmap (you may copy code from the theory sheet). You can choose
 * the way of dealing with collisions.
 * 
 * Initialize the HashMap with a size of 1000. You can assume that this is enough and do
 * not need to implement resize.
 */

public class HashMap implements IHashMap {

    int MAP_SIZE = 1000;
    Element[] map;

    public HashMap() {
        this.map = new Element[MAP_SIZE];
    }

    @Override
    public void put(String key, String value) {
        int hashcode = this.generateHashCode(key);

        if (this.map[hashcode] != null) {
            Element e = this.map[hashcode];
            while (!e.Key.equals(key) && e.next() != null)
                e = e.next;

            if (e.Key.equals(key))
                e.value = value;
            else
                e.next = new Element(key, value);
        } else {
            this.map[hashcode] = new Element(key, value);
        }
    }

    @Override
    public String get(String key) {
        int hashcode = this.generateHashCode(key);

        if (this.map[hashcode] == null)
            return null;

        Element e = this.map[hashcode];
        while (!e.Key.equals(key) && e.next() != null)
            e = e.next;

        return e.Key.equals(key) ? e.value : null;
    }

    private int generateHashCode(String value) {
        // as per http://stackoverflow.com/a/20294714
        return (value.hashCode() & 0x7fffffff) % this.map.length;
    }

    private class Element {
        private Element next;
        private String value;

        public final String Key;

        public Element(String key, String value) {
            this.Key = key;
            this.value = value;
        }

        public Element next() {
            return this.next;
        }
    }
}
