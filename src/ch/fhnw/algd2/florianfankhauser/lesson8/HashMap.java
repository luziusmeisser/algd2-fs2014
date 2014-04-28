// Created by Florian Fankhauser on 04.04.2014

package ch.fhnw.algd2.florianfankhauser.lesson8;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap implements IHashMap2 {
	private static final MapElement REMOVED_MARKER = new MapElement("", "");

	private MapElement[] map = new MapElement[5];
	private int counter = 0;

	@Override
	public void put(String key, String value) {
		MapElement el = new MapElement(key, value);
		int i = el.hash % map.length;
		for (int n = 0; n < map.length; n++) {
			if (map[(i + n) % map.length] == null
					|| map[(i + n) % map.length].key.equals(key)) {
				map[(i + n) % map.length] = el;
				counter++;
				break;
			}
		}
		if (counter / (double) map.length > 0.75d) {
			resize();
		}
	}

	@Override
	public String get(String key) {
		int i = key.hashCode() % map.length;
		for (int n = 0; n < map.length; n++) {
			if (map[(i + n) % map.length] == null) {
				return null;
			} else if (map[(i + n) % map.length].key.equals(key)) {
				return map[(i + n) % map.length].value;
			}
		}
		return null;
	}

	@Override
	public String remove(String key) {
		int i = key.hashCode() % map.length;
		for (int n = 0; n < map.length; n++) {
			if (map[(i + n) % map.length] == null) {
				return null;
			} else if (map[(i + n) % map.length].key.equals(key)) {
				String val = map[(i + n) % map.length].value;
				map[(i + n) % map.length] = REMOVED_MARKER;
				counter--;
				return val;
			}
		}
		return null;
	}
	
	private void resize() {
		MapElement[] newMap = new MapElement[map.length * 2];
		for (MapElement m : map) {
			if (m != null && m!= REMOVED_MARKER) {
				int i = m.hash % newMap.length;
				for (int n = 0; n < newMap.length; n++) {
					if (newMap[(i + n) % newMap.length] == null) {
						newMap[(i + n) % newMap.length] = m;
						break;
					}
				}
			}
		}
		map = newMap;
	}

	private static class MapElement {
		private String key;
		private int hash;
		private String value;

		private MapElement(String key, String value) {
			this.key = key;
			this.value = value;
			hash = key.hashCode();
			
		}

		public String toString() {
			return key;
		}
	}
}