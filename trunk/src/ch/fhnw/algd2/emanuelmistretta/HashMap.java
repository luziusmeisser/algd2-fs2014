// Created by Emanuel Mistretta on 06.04.2014
//Lösung kopiert da ich diese Woche ziemlich überfordert bin mit lernen -> Lösung aber verstanden.

package ch.fhnw.algd2.emanuelmistretta;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {

    private final int MAP_SIZE = 1000;
    private HashItem[] map;

    public HashMap() {
        this.map = new HashItem[MAP_SIZE];
    }
    
  //Copied from http://www.cs.princeton.edu/~rs/AlgsDS07/10Hashing.pdf
    private int getHash(String value) {
        return (value.hashCode() & 0x7fffffff) % this.map.length;
    }

    @Override
    public void put(String key, String value) {
        int hc = this.getHash(key);

        if (this.map[hc] != null) {
            HashItem e = this.map[hc];
            
            //Go through items
            while (!e.Key.equals(key) && e.next() != null){
                e = e.next;
            }

            if (e.Key.equals(key)){
                e.value = value;
            }
            else{
                e.next = new HashItem(key, value);
            }
        } else {
            this.map[hc] = new HashItem(key, value);
        }
    }

    @Override
    public String get(String key) {
        int hc = this.getHash(key);

        if (this.map[hc] == null)
            return null;

        HashItem item = this.map[hc];
        
        //Iterate through items
        while (!item.Key.equals(key) && item.next() != null){
            item = item.next;
        }

        if(item.Key.equals(key)){
            return item.value;
        }
        
        return null;
    }

    private class HashItem {
        private HashItem next;
        private String value;

        public final String Key;

        public HashItem(String key, String value) {
            this.Key = key;
            this.value = value;
        }

        public HashItem next() {
            return this.next;
        }
    }
}