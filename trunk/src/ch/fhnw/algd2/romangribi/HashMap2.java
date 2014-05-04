// Created by Roman Gribi on 04.05.2014

package ch.fhnw.algd2.romangribi;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {

    private int used = 0;
    private Element[] data;

    public HashMap2() {
        this(1000);
    }

    public HashMap2(int size) {
        this.data = new Element[size];
        for (int i = 0; i < size; i++) {
            this.data[i] = new Element("", "");
        }
    }

    @Override
    public void put(String key, String value) {
        int i = hash(key);

        while (i < this.data.length && !this.data[i].key.equals(key) && this.data[i].state == State.Used) {
            i += 1;
        }

        if (this.data[i].key.equals(key)) {
            this.data[i].data = value;
        } else {
            Element el = this.data[i];
            el.state = State.Used;
            el.key = key;
            el.data = value;
            ++used;
        }

        resize();
    }

    private void resize() {
        if (((double) used / this.data.length) < 0.75)
            return;

        HashMap2 tmp = new HashMap2(this.data.length * 2);
        for (Element el : this.data) {
            if (el.state == State.Used)
                tmp.put(el.key, el.data);
        }
        this.data = tmp.data;
        this.used = tmp.used;
    }

    @Override
    public String get(String key) {
        Element el = find(key);
        if (el != null) {
            return el.data;
        }
        return null;
    }

    @Override
    public String remove(String key) {
        Element el = find(key);
        if (el != null) {
            --used;
            el.state = State.Removed;
            return el.data;
        }
        return null;
    }

    private Element find(String key) {
        int i = hash(key);

        while (i < this.data.length && !this.data[i].key.equals(key) && this.data[i].state != State.Free) {
            i += 1;
        }

        if (this.data[i].state == State.Used && this.data[i].key.equals(key)) {
            return this.data[i];
        }
        return null;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % this.data.length;
    }

    private static enum State {
        Free, Used, Removed
    }

    private class Element {
        private String key;
        private String data;
        private State state;

        public Element(String key, String data) {
            this.key = key;
            this.data = data;
            this.state = State.Free;
        }
    }
}
