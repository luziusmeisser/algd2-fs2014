// Created by Kevin Wieser on 24.02.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipListe<T extends Comparable<T>> implements ISkipList<T> {

	private Element<T> headAnchor;

	private static int MAXLEVEL = 20;

	private class Element<T> {
		// Arrays von Pointer auf Elements:
		private Element<T>[] m_next;
		private T m_value;

		public Element(T m_value, int level) {
			this.m_value = m_value;
			m_next = new Element[level];
		}

		public Element(T m_value) {
			assert m_value != null;
			this.m_value = m_value;
			m_next = new Element[getRandom()];
		}

		private int getRandom() {
			double p = 0.5;
			int level = 1;
			while (level < MAXLEVEL && Math.random() < p) {
				level++;
			}
			return level;
		}

		public Element<T> getNext(int i) {
			return m_next[i];
		}

		public T getValue() {
			return m_value;
		}

		public int getNextLenght() {
			return m_next.length;
		}

		public void setNext(int i, Element<T> element) {
			m_next[i] = element;
		}

	}

	public SkipListe() {
		headAnchor = new Element<>(null, MAXLEVEL);
	}

	@Override
	public void add(T item) {
		// wenn countStepsTo(item) = 0 dann ist sicher, dass Element noch nicht
		// vorhanden ist -> Soll verhindern dass 2 gleiche Elemente vorhanden
		// sind
		// if (countStepsTo(item) == 0) {
		Element<T>[] update = new Element[MAXLEVEL];
		Element<T> aktuell = headAnchor;

		for (int i = MAXLEVEL - 1; i >= 0; i--) {
			while (aktuell.getNext(i) != null && aktuell.getNext(i).getValue().compareTo(item) < 0) {
				aktuell = aktuell.getNext(i);
			}
			update[i] = aktuell;
		}

		Element<T> node = new Element<>(item);

		for (int i = 0; i < node.getNextLenght(); i++) {
			node.setNext(i, update[i].getNext(i));
			update[i].setNext(i, node);
		}
		// }
	}

	@Override
	public T removeFirst() {
		if (headAnchor.getNext(0) != null) {
			Element<T> aktuell = headAnchor.getNext(0);
			Element<T>[] update = new Element[aktuell.getNextLenght()];
			for (int i = 0; i < update.length; i++) {
				headAnchor.setNext(i, aktuell.getNext(i));
			}
			return aktuell.getValue();
		} else {
			throw new NoSuchElementException();
		}

	}

	@Override
	public int countStepsTo(T item) {
		int counter = 0;
		Element<T> aktuell = headAnchor;
		for (int i = MAXLEVEL - 1; i >= 0; i--) {
			while (aktuell.getNext(i) != null && aktuell.getNext(i).getValue().compareTo(item) < 0) {
				counter++;
				aktuell = aktuell.getNext(i);
			}
		}
		// Setze auf aktuell.getNext(0), da obere while Schleife bis zu einem
		// Knoten vor dem Suchergebnis geht
		aktuell = aktuell.getNext(0);

		if (aktuell != null && aktuell.getValue().compareTo(item) == 0) {
			counter++; // Berücksichtigt aktuell = aktuell.getNext(0);
			return counter;
		} else {
			return 0;
		}
	}

}
