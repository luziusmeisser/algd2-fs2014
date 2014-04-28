// Created by Luzius on 31.03.2014

package ch.fhnw.algd2.luzius;


import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {

	private Entry[] elements;
	
	public HashMap(){
		this.elements = new Entry[1000];
	}
	
	@Override
	public void put(String key, String value) {
		int pos = key.hashCode() % elements.length;
		Entry existing = find(elements[pos], key);
		if (existing == null){
			elements[pos] = new Entry(key, value, elements[pos]);
		} else {
			existing.value = value;
		}
	}

	private Entry find(Entry entry, String key) {
		if (entry == null){
			return null;
		} else if (entry.key.equals(key)){
			return entry;
		} else {
			return find(entry.next, key);
		}
	}

	@Override
	public String get(String key) {
		int pos = key.hashCode() % elements.length;
		Entry e = find(elements[pos], key);
		return e == null ? null : e.value;
	}
	
	class Entry {
		
		String key;
		String value;
		Entry next;
		
		public Entry(String key, String value, Entry entry) {
			this.key = key;
			this.value = value;
			this.next = entry;
		}
		
		
	}

}
