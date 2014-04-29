package ch.fhnw.algd2.larskessler;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
	
	private final static int RESIZE = 80;
	private final static float FACTOR = 1.5f;
	private HashMapElement[] m_HashTable;
	private int m_m;
	private int fillStatus;
	
	public HashMap2() {
		this.m_m = 1000;
		m_HashTable = new HashMapElement[m_m];
	}

	@Override
	public void put(String key, String value) {
		resize();
		add(calcPos(key), key, value);
	}

	@Override
	public String get(String key) {
		HashMapElement item = getElement(key);
		if(item == null) {
			return null;
		} else {
			return getElement(key).m_data;
		}
	}

	@Override
	public String remove(String key) {
		HashMapElement item = getElement(key);
		
		if(item != null) {
			fillStatus--;
			item.m_deleted = true;
			return item.m_data;
		}
		
		return null;
	}
	
	private void resize() {
		if(fillStatus > ((this.m_m / 100) * RESIZE)) {
			HashMapElement[] oldElements = m_HashTable;
			int size = (int) (m_m * FACTOR);
			m_HashTable = new HashMapElement[size];
			
			for(int i = 0; i < m_m; i++) {
				if(oldElements[i] != null && !oldElements[i].m_deleted) {
					m_HashTable[i] = oldElements[i];
				}
			}
			m_m = size;
		}
	}
	
	private int calcPos(String key) {
		int hashCode = key.hashCode();
		int pos = hashCode % m_m;
		
		// avoid negative array pos
		if(pos < 0)  pos = pos * (-1);
		
		//System.out.println(pos);
		return pos;
	}
	
	private HashMapElement add(int pos, String key, String value) {
		HashMapElement item = new HashMapElement(key, value);
		fillStatus++;
		
		if(m_HashTable[pos] == null) {
			return m_HashTable[pos] = item;
		} else {
			return m_HashTable[findNextGap(pos, key)] = item;
		}
	}
	
	private int findNextGap(int pos, String key) {
		while(pos < m_m-1 && m_HashTable[pos] != null && !m_HashTable[pos].m_key.equals(key) && !m_HashTable[pos].m_deleted) {			
			pos++;
		}
		return pos;
	}
	
	private HashMapElement getElement(String key) {
		int pos = calcPos(key);
		
		if(m_HashTable[pos] == null) {
			return null;
		} else {
			while(pos < m_m && m_HashTable[pos] != null) {
				if(m_HashTable[pos].equals(key)) {
					return m_HashTable[pos];
				}
				pos++;
			}
			return null;
		}
	}
	
	class HashMapElement {
		private String m_data;
		private String m_key;
		private boolean m_deleted;
		
		public HashMapElement(String key, String data) {
			m_data = data;
			m_key = key;
			m_deleted = false;
		}
		
		public boolean equals(Object o) {
			return m_key.equals(((HashMapElement)o).m_key);
		}
		
		public boolean equals(String key){
			return m_key.equals(key);
		}
	} 
}