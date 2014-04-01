package ch.fhnw.algd2.larskessler;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class MyHashArray<T> implements IHashMap {
	
	private T[] m_HashTable;
	private int m_m;
	
	public MyHashArray(int size) {
		m_m = size;
		m_HashTable = (T[])new Object[m_m];
	}

	@Override
	public void put(String key, String value) {
		int hashCode = key.hashCode();
		int pos = hashCode % m_m;
		MyHashArrayElement newItem = new MyHashArrayElement(key, (T) value);
		
		if(m_HashTable[pos] == null) {
			// place newItem to empty position
			m_HashTable[pos] = (T) newItem;
		} else {
			// position already used
			MyHashArrayElement item = (MyHashArrayElement) m_HashTable[pos];
			if(item.hasNext()) {
				while(item.hasNext()) {
					if(item.equals(newItem)) {
						item.m_data = (T) value;
					}
		
					item = item.next;
				}
				
				item.next = newItem;
			} else {
				if(item.equals(newItem)) {
					item.m_data = (T) value;
				} else {
					item.next = newItem;
				}
			}
		}
	}

	@Override
	public String get(String key) {
		int pos = Integer.parseInt(key) % m_m;
		
		if(m_HashTable[pos] == null) {
			return null;
		} 
		
		MyHashArrayElement item = (MyHashArrayElement) m_HashTable[pos];
		
		do {
			if(item.equals(key)) {
				return (String) item.m_data;
			}			
			
			item = item.next;
			
		} while(item != null);
		
		return null;
	}
	
	public class MyHashArrayElement {
		
		private T m_data;
		private String m_key;
		private MyHashArrayElement next;
		
		public MyHashArrayElement(String key, T data) {
			m_data = data;
			m_key = key;
			next = null;
		}
		
		public boolean equals(Object o) {
			return m_key.equals(((MyHashArrayElement)o).m_key);
		}
		
		public boolean equals(String key){
			return m_key.equals(key);
		}
		
		private boolean hasNext() {
			return next != null;
		}
		
	} 

}
