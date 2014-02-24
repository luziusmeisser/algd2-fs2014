package ch.fhnw.algd2.florianfankhauser.lesson2;

import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<S extends Comparable<S>> implements ISkipList<S> {
	private static final int MAX_SKIPS = 64;
	private static final Random RANDOMIZER = new Random();
	private Element<S> firstElement = null;
	
	public SkipList() {
		firstElement = new Element<S>(null, new Element[MAX_SKIPS + 1]);
	}
	@Override
	public void add(S item) {
		int level = 0; 
		while (RANDOMIZER.nextBoolean() && ++level<MAX_SKIPS);
		Element<S>[] skips = new Element[MAX_SKIPS + 1];
		Element<S> n = new Element<S>(item, skips);
		Element<S> e = firstElement;
		int l = MAX_SKIPS;
		while (l-- > 0) {
			while (e.skips[l] != null && e.skips[l].value.compareTo(item) < 0) {
				e = e.skips[l];
			}
			if (level >= l) {
				n.skips[l] = e.skips[l];
				e.skips[l] = n;
			}
		}
	}

	@Override
	public S removeFirst() {
		Element<S> first = firstElement.skips[0];
		if (first != null) {
			for (int l = 0; l < MAX_SKIPS; l++) {
				firstElement.skips[l] = first.skips[l] == null ? firstElement.skips[l] : first.skips[l];
			}
			return first.value;
		}
		throw new NoSuchElementException();
	}

	@Override
	public int countStepsTo(S item) {
		Element<S> e = firstElement;
		int count = 0;
		int l = MAX_SKIPS;
		while (l-- > 0) {
			while (e.skips[l] != null && e.skips[l].value.compareTo(item) < 0) {
				e = e.skips[l];
				count++;
			}
		}
		return count;
	}
	

	private class Element<T extends Comparable<T>> {
		private Element<T>[] skips;
		private T value;
		
		Element(T value, Element<T>[] skips) {
			this.value = value;
			this.skips = skips;
		}
		
		public String toString() {
			return value.toString();
		}
	}
}
