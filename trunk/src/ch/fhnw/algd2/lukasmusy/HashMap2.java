//created by lukas.musy on May 4, 2014

package ch.fhnw.algd2.lukasmusy;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
	private int size;
	private int count = 0;
	private Element[] store;
	
	public HashMap2(){
		this(1000);
	}
	
	public HashMap2(int size){
		this.size = size;
		this.store = new Element[size];
	}

	@Override
	public void put(String key, String value) {
		++count;
		resize();
		int pos = getPosition(key);
		Element tmp = new Element();
		store[pos] = tmp;
		tmp.key = key;
		tmp.value = value;
	}

	@Override
	public String get(String key) {
		int pos = search(key);
		if(pos == -1){
			return null;
		}else{
			return store[pos].value;
		}
	}

	@Override
	public String remove(String key) {
		int pos = search(key);
		if(pos == -1){
			return null;
		}else{
			store[pos].deleted = true;
			return store[pos].value;
		}
	}
	
	private int getPosition(String key){
		int pos = key.hashCode() % size;
		while(true){
			if(store[pos] == null){
				return pos;
			}else if(store[pos].key.equals(key)){
				return pos;
			}
			++pos;
			pos = pos % size;
		}
	}
	
	private int search(String key){
		int pos = key.hashCode() % size;
		while(true){
			if(store[pos] == null){
				return -1;
			}else if(store[pos].key.equals(key)){
				return pos;
			}
			pos++;
			pos = pos % size;
		}
	}
	
	private void resize(){
		if(((double) size / count) < 1.3){
			HashMap2 tmp = new HashMap2(size * 2);
			for(int i=0; i<size; ++i){
				if(store[i] != null && !(store[i].deleted)){
					tmp.put(store[i].key, store[i].value);
				}
			}
			this.size = tmp.size;
			this.count = tmp.count;
			this.store = tmp.store;
		}
	}
	
	private class Element{
		public boolean deleted;
		public String value;
		public String key;
		
		public Element(){
			this.deleted = false;
		}
		
	}
	

}

