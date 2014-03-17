// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.lesson5.exercise;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.luzius.BinaryTreeTraverser;

public class AVLNodeTest {

	private ArrayList<AbstractAVLNode> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

		// add your own implementation here
		lists.add(new ch.fhnw.algd2.luzius.AVLNode("first"));
	}

	@Test
	public void test() {
		for (AbstractAVLNode node : lists) {
			TreeSet<String> verification = new TreeSet<>();
			verification.add("first");
			Random rand = new Random(7);
			for (int i = 0; i < 100; i++) {
				String value = Integer.toString(rand.nextInt(1000));
				verification.add(value);
				node.insert(value);
				node = node.ensureBalance();
			}
			BinaryTreeTraverser trav = new BinaryTreeTraverser();
			String assembled = trav.assemble(node, true);
			for (String s : verification) {
				assert assembled.startsWith(s);
				assembled = assembled.substring(s.length());
			}
		}
	}

}
