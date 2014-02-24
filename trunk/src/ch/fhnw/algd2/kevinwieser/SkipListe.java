package ch.fhnw.algd2.kevinwieser;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipListe<T extends Comparable<T>> implements ISkipList<T> {

	private Element headAnchor;
	private Element tailAnchor;
	private static int MAXLEVEL = 100;
	// Header soll bei kleinstmöglichen Zahl anfangen:
	private static int HEADER_KEY = Integer.MIN_VALUE;
	// Tail soll bei höchst möglichen Zahl aufhören:
	private static int TAIL_KEY = Integer.MAX_VALUE;
	
	
	class Element {
			// Arrays von Pointer auf Elements:
			private Element[] m_next;
			private int m_key;
			private T m_value;
			
			public Element(int m_key, T m_value) {
				super();
				this.m_key = m_key;
				this.m_value = m_value;
			}
	}
	
	
	public SkipListe() {
		headAnchor = new Element(HEADER_KEY, null);
	}
	
	@Override
	public void add(T item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countStepsTo(T item) {
		// TODO Auto-generated method stub
		return 0;
	}

}
