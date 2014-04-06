// Created by Christian Guedel on 06.04.2014

package ch.fhnw.algd2.christianguedel;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {

    private final int MAP_SIZE = 1000;
    private Element[] map;

    public HashMap() {
        this.map = new Element[MAP_SIZE];
    }

    @Override
    public void put(String key, String value) {
        int hc = this.generateHashCode(key);

        if (this.map[hc] != null) {
            Element e = this.map[hc];
            while (!e.Key.equals(key) && e.next() != null)
                e = e.next;

            if (e.Key.equals(key))
                e.value = value;
            else
                e.next = new Element(key, value);
        } else {
            this.map[hc] = new Element(key, value);
        }
    }

    @Override
    public String get(String key) {
        int hc = this.generateHashCode(key);

        if (this.map[hc] == null)
            return null;

        Element e = this.map[hc];
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
