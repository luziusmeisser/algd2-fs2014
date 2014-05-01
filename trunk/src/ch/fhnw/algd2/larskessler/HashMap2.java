package ch.fhnw.algd2.larskessler;
import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {
	
	private int size;
	private int count;
	private static final float FACTOR = 2.0f;
	private static final int PERCENTAGE = 90;
	private HashMapElement[] elements;
	
	public HashMap2() {
		this.size = 1000;
		this.elements = new HashMapElement[this.size];
	}

	@Override
	public void put(String key, String value) {
		resizeStorage();
		elements[getGap(calcPos(key), key)] = new HashMapElement(key, value);
		this.count++;
	}

	@Override
	public String get(String key) {
		return getElement(key).data;
	}

	@Override
	public String remove(String key) {
		HashMapElement element = getElement(key);
		
		if(element != null) {
			element.removed = true;
			this.count--;
			return element.data;
		}
		return null;
	}
	
	private int calcPos(String key) {
		int hashCode = key.hashCode();
		int pos = hashCode % this.size;
		
		// avoid negative array pos
		if(pos < 0)  pos = pos * (-1);
		return pos;
	}
	
	private int getGap(int pos, String key) {
		if(this.elements[pos] == null) {
			return pos;
		} else {
			while(pos < this.size-1 && this.elements[pos] != null && !this.elements[pos].removed && !this.elements[pos].equals(key)) {
				pos++;
			}
		}
		return pos;
	}
	
	private HashMapElement getElement(String key) {
		int pos = calcPos(key);
		while(pos < this.size && this.elements[pos] != null && !this.elements[pos].key.equals(key)) {
			pos++;
		}
		if(this.elements[pos] != null && this.elements[pos].key.equals(key)) {
			return this.elements[pos];
		}
		return null;
	}
	
	private void resizeStorage() {
		if(this.count > ((this.size / 100) * PERCENTAGE)) {
			HashMapElement[] oldElements = this.elements;
			this.elements = new HashMapElement[(int) (oldElements.length * FACTOR)];
			for (HashMapElement e : oldElements) {
				if (e != null && !e.removed)
					put(e.key, e.data);
			}
			this.size = this.elements.length;
		}
	}
	
	public class HashMapElement {
		private String key;
		private String data;
		private boolean removed;
		
		public HashMapElement(String key, String data) {
			this.key = key;
			this.data = data;
			this.removed = false;
		}
		
		public boolean equals(String key) {
			return this.key.equals(key);
		}
	}
}