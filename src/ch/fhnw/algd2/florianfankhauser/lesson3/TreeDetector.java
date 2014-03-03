package ch.fhnw.algd2.florianfankhauser.lesson3;

import java.util.Random;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {
	private long MARKER;

	@Override
	public boolean isTree(Node any) {
		MARKER = (new Random()).nextLong();
		any.setMarker(MARKER);
		try {
			for (Node n : any.getNeighbors()) {
				if (n.getMarker() != null && (long) n.getMarker() == MARKER) {
					throw new IllegalStateException();
				} else {
					visit(n, any);
				}
			}
		} catch (IllegalStateException e) {
			return false;
		}
		return true;
	}

	private void visit(Node any, Node from) {
		any.setMarker(MARKER);
		for (Node n : any.getNeighbors()) {
			if (!(n == from)) {
				if (n.getMarker() != null && (long) n.getMarker() == MARKER) {
					throw new IllegalStateException();
				} else {
					visit(n, any);
				}
			}
		}
	}
}
