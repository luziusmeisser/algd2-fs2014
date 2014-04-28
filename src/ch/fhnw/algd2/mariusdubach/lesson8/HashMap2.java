//Created by Marius Dubach 28.04.2014

package ch.fhnw.algd2.mariusdubach.lesson8;
import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2{
	
	private int size;
	private int added = 0;
	private Bucket[] store;
	
	public HashMap2(){
		this(1000);
	}
	
	public HashMap2(int size){
		this.size = size;
		this.store = new Bucket[size];
	}

	@Override
	public void put(String key, String value) {
		int pos = getPosition(key);
		Bucket tmp = new Bucket();
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
	
	private class Bucket{
		public boolean deleted;
		public String value;
		public String key;
		
		public Bucket(){
			this.deleted = false;
		}
		
	}
	

}
