package ch.fhnw.algd2.florianfankhauser.lesson4;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		if (depthFirst) {
			return depthAssemble(root);
		} else {
			List<String> strings = new ArrayList<String>();
			widthAssemble(root, strings, 0);
			String string = "";
			for (String s : strings) {
				string += s;
			}
			return string;
		}
	}

	public void widthAssemble(BinaryNode root, List<String> strings, int level) {
		if (root != null) {
			if (strings.size() <= level) {
				strings.add("");
			}
			strings.set(level, strings.get(level) + root.getValue());
			widthAssemble(root.getLeftChild(), strings, level + 1);
			widthAssemble(root.getRightChild(), strings, level + 1);
		}
	}

	public String depthAssemble(BinaryNode root) {
		String s = "";
		if (root.getLeftChild() != null) {
			s += depthAssemble(root.getLeftChild());
		}
		s += root.getValue();
		if (root.getRightChild() != null) {
			s += depthAssemble(root.getRightChild());
		}
		return s;
	}

}
