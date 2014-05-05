// Created by Luzius on 28.04.2014

package ch.fhnw.algd2.luzius;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {

	private static final double LOAD_FACTOR = 0.75;

	private static final String MARKER = new String("");

	private String[] keys;
	private String[] values;

	private int entries;
	private int deletes;

	public HashMap2() {
		this(16);
	}

	public HashMap2(int size) {
		this.keys = new String[size];
		this.values = new String[size];
	}

	@Override
	public void put(String key, String value) {
		considerRehash();
		int pos = find(key);
		if (values[pos] == null){
			entries++;
		}
		if (keys[pos] == MARKER){
			deletes--;
		}
		keys[pos] = key;
		values[pos] = value;
	}

	private void considerRehash() {
		if (entries + deletes >= keys.length * LOAD_FACTOR) {
			HashMap2 larger = new HashMap2((int) (entries / LOAD_FACTOR * 2));
			for (int i = 0; i < keys.length; i++) {
				if (keys[i] != null && keys[i] != MARKER) {
					larger.put(keys[i], values[i]);
				}
			}
			this.keys = larger.keys;
			this.values = larger.values;
		}
	}

	private int find(String key) {
		int base = key.hashCode() % keys.length;
		int i = 0;
		while (true) {
			int pos = getPos(base, i);
			if (keys[pos] == null) {
				return pos;
			} else if (keys[pos].equals(key)) {
				return pos;
			} else {
				i++;
			}
		}
	}

	private int getPos(int base, int i) {
		return (base + i) % keys.length;
	}

	@Override
	public String get(String key) {
		int pos = find(key);
		return values[pos];
	}

	@Override
	public String remove(String key) {
		int pos = find(key);
		String val = values[pos];
		if (keys[pos] != null && keys[pos] != MARKER) {
			keys[pos] = MARKER;
			values[pos] = null;
			entries--;
			deletes++;
		}
		return val;
	}

}
