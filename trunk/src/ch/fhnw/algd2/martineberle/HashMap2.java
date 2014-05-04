// Created by Martin Eberle on 06.04.2014

package ch.fhnw.algd2.martineberle;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
	public int hashSize;
	public int size = 0;
	public Element[] storage;

	public HashMap2(){
		this(1000);
	}
	public HashMap2(int hashSize){
		this.hashSize = hashSize;
		this.storage = new Element[hashSize];
	}
	
	public void resize(){
		if((double)size / (double)hashSize >= 0.75){
			HashMap2 newMap = new HashMap2(hashSize*2);
			for(int i = 0; i < hashSize; i++){
				if(storage[i] != null && !storage[i].deleted){
					newMap.put(storage[i].key, storage[i].val);
				}
			}
			this.size = newMap.size;
			this.hashSize = newMap.hashSize;
			this.storage = newMap.storage;
		}
	}
	
	public int findPos(String key){
		int hash = key.hashCode() % hashSize;
		int i = hash;
		while(true){
			if(storage[i] == null){
				return i;
			}
			if(storage[i].key.equals(key) && storage[i].deleted){
				return i;
			}
			if(storage[i].key.equals(key)){
				return i;
			}
			i++;
			i = i % hashSize;
		}
	}
	
	@Override
	public void put(String key, String value) {
		size++;
		resize();
		int position = findPos(key);
		Element temp = new Element(key, value);
		storage[position] = temp;
	}

	@Override
	public String get(String key) {
		int hash = (key.hashCode() % hashSize);
		for(int i = hash; i < hashSize; i++){
			if(storage[i] == null){
				return null;
				}
			if(storage[i].key.equals(key) && !storage[i].deleted){
				return storage[i].val;
				}
			if(storage[i].key.equals(key) && storage[i].deleted){
				return null;
			}
		}
		throw new OutOfMemoryError();
	}
	
	@Override
	public String remove(String key) {
		int hash = (key.hashCode() % hashSize);
		int i = hash;
		while(true){
			if(storage[i] == null){
				return null;
				}
			if(storage[i].key.equals(key) && !storage[i].deleted){
				storage[i].deleted = true;
				size--;
				return storage[i].val;
				}
			if(storage[i].key.equals(key) && storage[i].deleted){
				return null;
			}
			i++;
			i = i % hashSize;
		}
	}

	public class Element{
		boolean deleted;
		String key;
		String val;
		
		public Element(){
			this.deleted = false;
		}
		
		public Element(String key, String val){
			this.deleted = false;
			this.key = key;
			this.val = val;
		}
		
		public Element(boolean delete){
			this.deleted = delete;
		}
	}
}
