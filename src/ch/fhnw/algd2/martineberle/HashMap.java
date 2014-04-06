// Created by Martin Eberle on 06.04.2014

package ch.fhnw.algd2.martineberle;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {
	public static final int HASH_SIZE = 1000;
	public Element[] storage;

	public HashMap(){
		this.storage = new Element[HASH_SIZE];
		
	}
	@Override
	public void put(String key, String value) {
		int hash = key.hashCode() % HASH_SIZE;
		if(storage[hash] == null){
			storage[hash] = new Element(key, value);
		}
		else {
			storage[hash] = new Element(storage[hash], key, value);
			}
	}

	@Override
	public String get(String key) {
		int hash = key.hashCode() % HASH_SIZE;
		if(storage[hash] == null){
			return null;
		}
		else if(storage[hash].next == null && storage[hash].key == key){
			return storage[hash].val;
		}
		else {
			Element head = storage[hash];
			while(head.next != null){
				if(head.key == key){
					return head.val;
				}
				head = head.next;
			}
			return null;
		}
	}

	public class Element{
		Element next;
		String key;
		String val;
//		public Element(String val){
//			this.val = val;
//			this.next = null;
//		}
//		public Element(Element current, String val){
//			this.next = current;
//			this.val = val;
//		}
		public Element(String val, String key){
			this.next = null;
			this.key = key;
			this.val = val;
		}
		public Element(Element current, String newKey, String newVal){
			this.next = current;
			this.key = newKey;
			this.val = newVal;
		}
	}
}
