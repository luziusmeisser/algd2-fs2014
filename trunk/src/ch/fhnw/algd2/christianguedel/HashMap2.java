package ch.fhnw.algd2.christianguedel;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2 {

	private final int INITIAL_SIZE = 1000;
	private final double REHASH_AT = 0.8;
	private final double GROW_BY = 2.0;

	private int numberOfElements = 0;
	private Element[] values;

	public HashMap2() {
		this.values = new Element[INITIAL_SIZE];
	}

	@Override
	public void put(String key, String value) {
		rehash();

		int insertAt = findIndex(key, true);

		if (insertAt >= values.length)
			return;
		if (values[insertAt] == null)
			values[insertAt] = new Element();

		values[insertAt].set(key, value);
		numberOfElements++;
	}

	@Override
	public String get(String key) {
		int elementAt = findIndex(key, false);
		return elementAt < values.length && values[elementAt] != null
				&& !values[elementAt].deleted ? values[elementAt].value : null;
	}

	@Override
	public String remove(String key) {
		int elementAt = findIndex(key, false);
		if (elementAt < values.length && values[elementAt] != null
				&& !values[elementAt].deleted) {
			numberOfElements--;
			values[elementAt].deleted = true;
			return values[elementAt].value;
		}

		return null;
	}

	private void rehash() {
		if (numberOfElements / values.length > REHASH_AT) {
			Element[] current = values;
			int newSize = (int) (values.length * GROW_BY);
			values = new Element[newSize];

			for (Element e : current) {
				if (e != null && !e.deleted)
					put(e.key, e.value);
			}
		}
	}

	private int findIndex(String key, boolean deletedValid) {
		int index;
		int offset = 0;

		do {
			index = (key.hashCode() & 0x7FFFFFFF) % values.length + offset;
			offset++;
		} while (index < values.length && values[index] != null
				&& (deletedValid || !values[index].deleted) && !values[index].key.equals(key));

		return index;
	}

	private class Element {
		String key;
		String value;
		boolean deleted;

		void set(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}
