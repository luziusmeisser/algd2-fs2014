// Created by Martin Eberle on 06.04.2014

package ch.fhnw.algd2.martineberle;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;
import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
	public static final int HASH_SIZE = 1000;
	public Element[] storage;

	public HashMap(){
		this.storage = new Element[HASH_SIZE];
		
	}
	@Override
	public void put(String key, String value) {
		int hash = (key.hashCode() % HASH_SIZE);
		if(storage[hash] == null){
			storage[hash] = new Element(key, value);
		}
		else {
			Element newElement = new Element(storage[hash], key, value);
			storage[hash] = newElement;
			}
	}

	@Override
	public String get(String key) {
		int hash = (key.hashCode() % HASH_SIZE);
		if(storage[hash] == null){
			return null;
		}
		else if(storage[hash].next == null && storage[hash].key.equals(key)){
			return storage[hash].val;
		}
		else {
			Element head = storage[hash];
			
			while(true){
				if(head.key.equals(key)){
					return head.val;
				}
				else if(head.next == null){
					return null;
				}
				head = head.next;
			}
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
		public Element(String key, String val){
//			this.next = null;
			this.key = key;
			this.val = val;
		}
		public Element(Element current, String newKey, String newVal){
			this.next = current;
			this.key = newKey;
			this.val = newVal;
		}
	}

	@Override
	public String remove(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
