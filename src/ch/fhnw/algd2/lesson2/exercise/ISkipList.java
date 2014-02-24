// Created by Luzius on Feb 22, 2014

package ch.fhnw.algd2.lesson2.exercise;

public interface ISkipList<T extends Comparable<T>> {

	public void add(T item);
	
	public T removeFirst();
	
	/**
	 * Gibt die Anzahl Knoten zur�ck, die traversiert werden m�ssen, um item zu finden.
	 */
	public int countStepsTo(T item);
	
}
