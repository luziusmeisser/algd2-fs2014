// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.lesson4.exercise;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTraverserTest {

	private ArrayList<IBinaryTreeTraverser> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

	// add your own implementation here
	lists.add(new ch.fhnw.algd2.emanuelmistretta.BinaryTreeTraverser());
        lists.add(new ch.fhnw.algd2.luzius.BinaryTreeTraverser());
        lists.add(new ch.fhnw.algd2.stephanbrunner.BinaryTreeTraverser());
	}

	@Test
	public void test() {
		for (IBinaryTreeTraverser test: lists){
			BinaryNode root = createTree();
			assert test.assemble(root, true).equals("evangelist");
			assert test.assemble(root, false).equals("evilsagent");
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
