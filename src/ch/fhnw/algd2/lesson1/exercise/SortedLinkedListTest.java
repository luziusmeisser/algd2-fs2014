// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.lesson1.exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.Clock;
import ch.fhnw.algd2.luzius.LinkedList;

public class SortedLinkedListTest {

	private ArrayList<AbstractSortedLinkedList<Integer>> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();
		lists.add(new ch.fhnw.algd2.romangribi.SortedLinkedList<Integer>());
		lists.add(new ch.fhnw.algd2.florianfankhauser.lesson1.SortedLinkedList<Integer>());
		// add your own LinkedList implementation here
//		lists.add(new ch.fhnw.algd2.luzius.SortedLinkedList<Integer>());
	}

	@Test
	public void testList() {
		for (AbstractLinkedList<Integer> list : lists) {
			for (int i = 0; i < 10000; i++) {
				list.add(i);
			}
			Clock c1 = new Clock();
			for (int i = 9999; i >= 0; i--) {
				list.remove((Integer) i);
			}
			c1.stop();
			for (int i = 0; i < 10000; i++) {
				list.add(i);
			}
			Clock c2 = new Clock();
			for (int i = 0; i < 10000; i++) {
				list.remove((Integer) i);
			}
			c2.stop();
			assert c1.tookMuchLongerThan(c2) || c2.tookMuchLongerThan(c1) : list.getClass().getName();
		}
	}
	
	@Test
	public void testSort1() {
		for (AbstractLinkedList<Integer> list : lists) {
			Random rand = new Random(13);
			for (int i = 0; i < 10000; i++) {
				list.add(rand.nextInt());
			}
			int current = Integer.MIN_VALUE;
			for (int val: list){
				assert val >= current;
				current = val;
			}
		}
	}
	
	@Test
	public void testSort2() {
		for (AbstractSortedLinkedList<Integer> list : lists) {
			Random rand = new Random(13);
			LinkedList<Integer> helper = new LinkedList<>();
			for (int i = 0; i < 10000; i++) {
				helper.add(rand.nextInt());
			}
			for (int i = 0; i < 10000; i++) {
				list.add(rand.nextInt());
			}
			list.merge(helper);
			int count = 0;
			int current = Integer.MIN_VALUE;
			for (int val: list){
				assert val >= current;
				current = val;
				count++;
			}
			assert count == 20000;
		}
	}

	@Test
	public void testStandardOps() {
		for (AbstractLinkedList<Integer> list : lists) {
			try {
				list.iterator().next();
				assert false;
			} catch (NoSuchElementException e) {
			}
			list.add(17);
			list.add(17);
			assert list.size() == 2;
			try {
				Iterator<Integer> iter = list.iterator();
				assert iter.next() == 17;
				iter.remove();
				iter.remove();
			} catch (IllegalStateException e) {
			}
			assert list.size() == 1;
			list.clear();
			assert list.isEmpty();
		}
	}
	
	@Test
	public void testSpecialCases() throws InstantiationException, IllegalAccessException {
		for (AbstractSortedLinkedList<Integer> list : lists) {
			assert list.size() == 0;
			list.merge(list);
			assert list.size() == 0;
			list.add(7);
			list.merge(list);
			assert list.size() == 2;
			AbstractSortedLinkedList<Integer> other = list.getClass().newInstance();
			list.merge(other);
			assert list.size() == 2;
			other.merge(list);
			assert other.size() == 2;
			other.merge(other);
			other.merge(other);
			assert other.size() == 8;
		}
	}

}
