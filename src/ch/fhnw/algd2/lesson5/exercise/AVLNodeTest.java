// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.lesson5.exercise;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.luzius.BinaryTreeTraverser;
import ch.fhnw.algd2.luzius.IVisitor;

public class AVLNodeTest {

	private ArrayList<AbstractAVLNode> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

		// add your own implementation here
        lists.add(new ch.fhnw.algd2.luzius.AVLNode("first"));
        lists.add(new ch.fhnw.algd2.stephanbrunner.AVLNode("first"));
        lists.add(new ch.fhnw.algd2.yannickaugstburger.AVLNode("first"));
        lists.add(new ch.fhnw.algd2.mariusdubach.lesson5.AVLNode("first"));
        lists.add(new ch.fhnw.algd2.kevinwieser.AVLNode("first"));
        lists.add(new ch.fhnw.algd2.florianfankhauser.lesson5.AVLNode("first"));
        lists.add(new ch.fhnw.algd2.stephenrandles.lesson05.AVLNode("first"));
        lists.add(new ch.fhnw.algd2.christianguedel.AVLNode("first"));
		lists.add(new ch.fhnw.algd2.romangribi.AVLNode("first"));
	}

	@Test
	public void test() {
		for (AbstractAVLNode node : lists) {
			TreeSet<String> verification = new TreeSet<>();
			verification.add("first");
			Random rand = new Random(7);
			final int count = 673;
			for (int i = 0; i < count; i++) {
				String value = Integer.toString(rand.nextInt(1000000));
				verification.add(value);
				node.insert(value);
				node = node.ensureBalance();
				System.out.println(i + " nodes, height: " + node.getHeight());
			}
			BinaryTreeTraverser<AbstractAVLNode> trav = new BinaryTreeTraverser<AbstractAVLNode>();
			String assembled = trav.assemble(node, true);
			for (String s : verification) {
				assert assembled.startsWith(s);
				assembled = assembled.substring(s.length());
			}
			trav.visit(new IVisitor<AbstractAVLNode>() {
				
				@Override
				public void visit(AbstractAVLNode t) {
					assert t.getBalance() >= -1;
					assert t.getBalance() <= 1;
					assert t.getHeight() <= 11;
				}
			}, node);
		}
	}

}
