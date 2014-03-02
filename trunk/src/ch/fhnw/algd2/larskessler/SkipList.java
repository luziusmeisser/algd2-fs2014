package ch.fhnw.algd2.larskessler;

import java.util.NoSuchElementException;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {
	
	private SkipListElement<T> root = new SkipListElement<T>(null);
	private final static int MAX_HEIGHT = 20;
	private int STEPS;

	@Override
	public void add(T item) {
		SkipListElement<T> next = new SkipListElement<T>(item);
		root.addToList(next, next.getMaxLevel());
	}

	@Override
	public T removeFirst() {
		SkipListElement<T> first = root.removeFirstElement();
		return first.value;
	}

	@Override
	public int countStepsTo(Comparable item) {
		return root.countStepsTo(item);
	}
	
	class SkipListElement<T extends Comparable<T>> implements Comparable<SkipListElement<T>> {
		// first element in array is always next element
		protected SkipListElement<T>[] links;
		// current element
		private T value;
		
		public SkipListElement(T e) {
			// set value to new element
			value = e;
			
			if(e == null) {
				// generate array with max size
				links = new SkipListElement[MAX_HEIGHT];
			} else {
				// generate array with calced size
				links = new SkipListElement[calcHeight()];
			}
		}

		public int countStepsTo(Comparable<T> item) {
			SkipListElement<T> next = this;
			int level = MAX_HEIGHT-1;
			STEPS = 0;

			do {
				while(next.links[level] != null && item.compareTo(next.links[level].value) >= 0) {
					next = next.links[level];
					STEPS++;
				}
				level--;
			} while(level >= 0);
			
			return STEPS;
		}

		public SkipListElement<T> removeFirstElement() {
			SkipListElement<T> element = links[0];
			if(element == null) {
				// if next first element is null, no such element!
				throw new NoSuchElementException();
			} else {
				// save links of first element to root element
				for(int i = 0; i < element.links.length; i++) {
					links[i] = element.links[i];
				}
				return element;
			}			
		}

		public void addToList(SkipListElement<T> next, int level) {
			if(level >= 0) {
				if(links[level] == null) {
					links[level] = next;
					addToList(next, level-1);
				} else if (next.compareTo(links[level]) < 0) {
					// add element in between or two elements
					next.links[level] = links[level];
					links[level] = next;
					addToList(next, level-1);
				} else {
					// add next element
					links[level].addToList(next, level);
				}
			}
		}

		public int getHeight() {
			return this.links.length;
		}
		
		public int getMaxLevel() {
			return this.links.length-1;
		}
		
		/*
		public void connectTo(SkipListElement<T> e) {
			for(int i = 0; i < e.getHeight(); i++) {
				// get next element
				SkipListElement next = getNext(i);
				// fill array with next element
				links[i] = next;
			}
		}
		*/
		
		
		/*
		private SkipListElement getNext(int level) {
			// TODO: implement getNext element functionality
			SkipListElement e = null;
			int skip = 1;
			
			if(level == 1) {
				e = links[0];
			} else {
				
			}
			
			for(int i = 0; i < level; i++) {
				
				skip = skip << 1;
			}
			
			return e;
		}
		*/

		private int calcHeight() {
			boolean decide = true;
			int height = 0;
			
			// get next boolean and increment height anyway
			do {
				decide = calcBoolean();
				height++;
			} while(height <= MAX_HEIGHT && decide == true);
			
			return height;
		}

		private boolean calcBoolean() {
			return Math.random() < 0.5;
		}

		@Override
		public int compareTo(SkipListElement<T> o) {
			// TODO Auto-generated method stub
			return value.compareTo(o.value);
		}
	}
}
