// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.lesson3.exercise;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.Util;

public class TreeDetectorTest {

	private Random RAND = new Random(11);
	private ArrayList<ITreeDetector> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

		// add your own LinkedList implementation here
		//lists.add(new ch.fhnw.algd2.luzius.TreeDetector());
        lists.add(new ch.fhnw.algd2.romangribi.TreeDetector());
        lists.add(new ch.fhnw.algd2.stephanbrunner.TreeDetector());
		lists.add(new ch.fhnw.algd2.florianfankhauser.lesson3.TreeDetector());
		lists.add(new ch.fhnw.algd2.christianguedel.TreeDetector());
		lists.add(new ch.fhnw.algd2.larskessler.TreeDetector());
		lists.add(new ch.fhnw.algd2.stephenrandles.TreeDetector());
	}

	@Test
	public void test() {
		for (ITreeDetector det : lists) {
			for (int i = 0; i < 100; i++) {
				boolean tree = RAND.nextBoolean();
				Node n1 = createGraph(RAND.nextInt(100), tree);

				assert tree == det.isTree(n1);
				assert tree == det.isTree(n1);
			}
		}
	}
	
	@Test
	public void testSelfPointer() {
		for (ITreeDetector det : lists) {
			TestNode tn = new TestNode();
			assert det.isTree(tn);
			tn.connect(tn);
			assert !det.isTree(tn);
		}
	}

	private Node createGraph(int nodes, boolean tree) {
		if (tree) {
			TestNode tn = new TestNode();
			addNodes(tn, nodes);
			return tn.getRandom();
		} else {
			TestNode tn = new TestNode();
			addNodes(tn, nodes);
			TestNode r = (TestNode) tn.getRandom();
			while (r.isConnectedTo(tn)){
				r = (TestNode) tn.getRandom();
			}
			r.connect(tn);
			tn.connect(r);
			return r;
		}
	}

	private int addNodes(TestNode tn, int nodes) {
		if (nodes > 0) {
			int children = RAND.nextInt(4);
			nodes -= children;
			for (int i = 0; i < children; i++) {
				TestNode ch = new TestNode();
				tn.connect(ch);
				ch.connect(tn);
				nodes = addNodes(ch, nodes);
			}
		}
		return nodes;
	}

	class TestNode extends Node {

		public void connect(Node other) {
			assert other != null;
			super.neighbors = Util.add(super.neighbors, other, new Node[super.neighbors.length + 1]);
		}

		public boolean isConnectedTo(TestNode tn) {
			return Util.contains(neighbors, tn);
		}

		public Node getRandom() {
			if (neighbors.length == 0 || RAND.nextDouble() < 0.1){
				return this;
			} else {
				return neighbors[RAND.nextInt(neighbors.length)];
			}
		}

	}

}
