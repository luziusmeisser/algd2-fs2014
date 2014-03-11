// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.lesson4.exercise;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.luzius.BinaryTreeTraverser;
import ch.fhnw.algd2.mariusdubach.lesson4.SortedBinaryNode;

public class SortedBinaryNodeTest {

	private ArrayList<AbstractSortedBinaryNode> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

		// add your own implementation here
		lists.add(new ch.fhnw.algd2.florianfankhauser.lesson4.SortedBinaryNode("root"));
		lists.add(new ch.fhnw.algd2.romangribi.SortedBinaryNode("root"));
		lists.add(new ch.fhnw.algd2.mariusdubach.lesson4.SortedBinaryNode("root"));
	}

	@Test
	public void test() {
		for (AbstractSortedBinaryNode test : lists) {
			TreeSet<String> verification = new TreeSet<>();
			Random rand = new Random(7);
			for (int i = 0; i < 100; i++) {
				String value = Integer.toString(rand.nextInt(1000));
				verification.add(value);
				test.insert(value);
			}
			BinaryTreeTraverser trav = new BinaryTreeTraverser();
			String assembled = trav.assemble(test, true);
			for (String s : verification) {
				assert assembled.startsWith(s);
				assembled = assembled.substring(s.length());
			}
			for (int i = 0; i < 100; i++) {
				String value = Integer.toString(rand.nextInt(100));
				test.remove(value);
				verification.remove(value);
				
				assembled = trav.assemble(test, true);
				for (String s : verification) {
					assert assembled.startsWith(s);
					assembled = assembled.substring(s.length());
				}
			}
			
			assembled = trav.assemble(test, true);
			for (String s : verification) {
				assert assembled.startsWith(s);
				assembled = assembled.substring(s.length());
			}
		}
	}

	private BinaryNode createTree() {
		BinaryNode n = new BinaryNode("n");
		BinaryNode t = new BinaryNode("t");
		BinaryNode a = new BinaryNode("a");
		BinaryNode ge = new BinaryNode("ge", n, null);
		BinaryNode t1 = new BinaryNode("", a, ge);
		BinaryNode l = new BinaryNode("l");
		BinaryNode t2 = new BinaryNode("", t1, l);
		BinaryNode t3 = new BinaryNode("", null, t);
		BinaryNode s = new BinaryNode("s", null, t3);
		BinaryNode t4 = new BinaryNode("", null, s);
		BinaryNode i = new BinaryNode("i", t2, t4);
		return new BinaryNode("ev", null, i);
	}
}
