// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2.lesson1.exercise;

import java.util.ArrayList;
import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.Util;

/**
 * Test class for your stack implementation.
 */
public class StackTest {

	private ArrayList<IStack<String>> stacks;

	@Before
	public void setUp() throws Exception {
		stacks = new ArrayList<>();
		stacks.add(new ch.fhnw.algd2.luzius.Stack<String>());
//		stacks.add(new ch.fhnw.algd2.YOURNAME.Stack()); add your own stack implementation here
	}

	@Test
	public void testPushPop() {
		for (IStack<String> stack : stacks) {
			stack.push("A");
			String o1 = "B";
			stack.push(o1);
			stack.push("C");
			stack.pop();
			assert stack.pop() == o1;
		}
	}

	@Test
	public void testSpeed() {
		for (IStack<String> stack : stacks) {
			long t0 = System.nanoTime();
			String object = "asdasd";
			for (int i = 0; i < 100000; i++) {
				stack.push(object);
			}
			for (int i = 0; i < 100000; i++) {
				stack.pop();
			}
			long t1 = System.nanoTime();
			long diff = (t1 - t0)/1000;
			System.out.println(Util.getAuthor(stack) + "'s stack completed speed test in " + diff + " ys");
		}
	}

	@Test
	public void testError() {
		for (IStack<String> stack : stacks) {
			try {
				stack.pop();
				assert false : stack.getClass().getName() + " must throw an EmptyStackException when popping empty";
			} catch (EmptyStackException e) {
			}
		}
	}

}
