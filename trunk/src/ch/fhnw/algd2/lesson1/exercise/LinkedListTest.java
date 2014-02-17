// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.lesson1.exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;



import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.Clock;

public class LinkedListTest {
	
	private ArrayList<AbstractLinkedList<Integer>> lists;
	
	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();
		lists.add(new ch.fhnw.algd2.florianfankhauser.LinkedList<Integer>());
		lists.add(new ch.fhnw.algd2.florianfankhauser.SortedLinkedList<Integer>());
		lists.add(new ch.fhnw.algd2.romangribi.LinkedList<Integer>());
        lists.add(new ch.fhnw.algd2.christianguedel.LinkedList<Integer>());
//      lists.add(new ch.fhnw.algd2.luzius.LinkedList<Integer>()); // add your own LinkedList implementation here
	}
	
	@Test
	public void testList(){
		for (AbstractLinkedList<Integer> list : lists) {
			for (int i=0; i<10000; i++){
				list.add(i);
			}
			Clock c1 = new Clock();
			for (int i=9999; i>=0; i--){
				list.remove((Integer)i);
			}
			c1.stop();
			for (int i=0; i<10000; i++){
				list.add(i);
			}
			Clock c2 = new Clock();
			for (int i=0; i<10000; i++){
				list.remove((Integer)i);
			}
			c2.stop();
			assert c1.tookMuchLongerThan(c2) || c2.tookMuchLongerThan(c1) : list.getClass().getName();
		}
	}
	
	@Test
	public void testStandardOps(){
		for (AbstractLinkedList<Integer> list : lists) {
			try {
				list.iterator().next();
				assert false;
			} catch (NoSuchElementException e){
			}
			list.add(17);
			list.add(17);
			assert list.size() == 2;
			try {
				Iterator<Integer> iter = list.iterator();
				assert iter.next() == 17;
				iter.remove();
				iter.remove();
			} catch (IllegalStateException e){
			}
			assert list.size() == 1;
			list.clear();
			assert list.isEmpty();
		}
	}

}
