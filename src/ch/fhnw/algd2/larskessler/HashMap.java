package ch.fhnw.algd2.larskessler;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap {
	
	private HashMapElement[] m_HashTable;
	private int m_m;
	
	public HashMap() {
		m_m = 1000;
		init();
	}
	
	public HashMap(int size) {
		m_m = size;
		init();
	}
	
	public void init() {
		m_HashTable = new HashMapElement[m_m];
	}
	
	private int calcPos(String key) {
		int hashCode = key.hashCode();
		int pos = hashCode % m_m;
		
		// avoid negative array pos
		if(pos < 0)  pos = pos * (-1);
		
		return pos;
	}

	@Override
	public void put(String key, String value) {
		// int hashCode = key.hashCode();
		// int pos = hashCode % m_m;
		int pos = calcPos(key);
		
		HashMapElement newItem = new HashMapElement(key, value);
		
		if(m_HashTable[pos] == null) {
			// place newItem to empty position
			m_HashTable[pos] = new HashMapElement(key, value);
		} else {
			// position already used
			HashMapElement item = (HashMapElement) m_HashTable[pos];
			if(item.hasNext()) {
				while(item.hasNext()) {
					if(item.equals(newItem)) {
						item.m_data = value;
					}
		
					item = item.next;
				}
				
				item.next = newItem;
			} else {
				if(item.equals(newItem)) {
					item.m_data = value;
				} else {
					item.next = newItem;
				}
			}
		}
	}

	@Override
	public String get(String key) {
		// int pos = key.hashCode() % m_m;
		int pos = calcPos(key);
		
		if(m_HashTable[pos] == null) {
			return null;
		} 
		
		HashMapElement item = (HashMapElement) m_HashTable[pos];
		
		do {
			if(item.equals(key)) {
				return (String) item.m_data;
			}			
			
			item = item.next;
			
		} while(item != null);
		
		return null;
	}
	
	class HashMapElement {
		
		private String m_data;
		private String m_key;
		private HashMapElement next;
		
		public HashMapElement(String key, String data) {
			m_data = data;
			m_key = key;
			next = null;
		}
		
		public boolean equals(Object o) {
			return m_key.equals(((HashMapElement)o).m_key);
		}
		
		public boolean equals(String key){
			return m_key.equals(key);
		}
		
		private boolean hasNext() {
			return next != null;
		}
		
	} 

}
