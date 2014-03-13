// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.stephenrandles.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;
import ch.fhnw.algd2.stephenrandles.lesson04.BinaryTreeTraverser;

public class SR_SortedBinaryNodeTest {

	private ArrayList<AbstractSortedBinaryNode> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

		// add your own implementation here
//		lists.add(new ch.fhnw.algd2.florianfankhauser.lesson4.SortedBinaryNode("root"));
//		lists.add(new ch.fhnw.algd2.romangribi.SortedBinaryNode("root"));
//		lists.add(new ch.fhnw.algd2.mariusdubach.lesson4.SortedBinaryNode("root"));
		lists.add(new ch.fhnw.algd2.stephenrandles.lesson04.SortedBinaryNode("root"));
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
					if(assembled.startsWith(s) == false)
						System.out.println("Assertion failed: Expected" + s + "\t//\tActual: " + assembled);
					else
						System.out.println("SUCCESS:\t Expected" + s + "\t//\tActual: " + assembled);
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
}
