package ch.fhnw.algd2.florianfankhauser.lesson4;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

/**
 * SVN Test
 * @author flofa_000
 *
 */
public class SortedBinaryNode extends AbstractSortedBinaryNode {
	public SortedBinaryNode(String value) {
		super(value);
	}

	@Override
	public int insert(String value) {
		return insert(value, 0);
	}

	private int insert(String value, int steps) {
		int comp = value.compareTo(getValue());
		if (comp == 0) {
			return steps;
		}

		if (comp < 0) {
			SortedBinaryNode node = (SortedBinaryNode) getLeftChild();
			if (node != null) {
				return node.insert(value, steps + 1);
			} else {
				left = new SortedBinaryNode(value);
				return steps;
			}
		} else {
			SortedBinaryNode node = (SortedBinaryNode) getRightChild();
			if (node != null) {
				return node.insert(value, steps + 1);
			} else {
				right = new SortedBinaryNode(value);
				return steps;
			}
		}
	}

	@Override
	public void remove(String value) {
		int comp = value.compareTo(getValue());
		if (comp < 0) {
			// go left
			if (left != null) {
				if (left.getValue().equals(value)) {
					// remove left node
					if (left.getLeftChild() == null) {
						left = left.getRightChild();
					} else if (left.getRightChild() == null) {
						left = left.getLeftChild();
					} else {
						// pick highest from left
						String highest = ((SortedBinaryNode) left.getLeftChild()).getHighestValue();
						SortedBinaryNode newNode = new SortedBinaryNode(highest);
						newNode.right = left.getRightChild();
						insertEverything(newNode, (AbstractSortedBinaryNode) left.getLeftChild());
						insertEverything(newNode, (AbstractSortedBinaryNode) left.getRightChild());
						left = newNode;
					}
				} else {
					((SortedBinaryNode) left).remove(value);
				}
			}
		} else {
			// go right
			if (right != null) {
				if (right.getValue().equals(value)) {
					// remove right node
					if (right.getLeftChild() == null) {
						right = right.getRightChild();
					} else if (right.getRightChild() == null) {
						right = right.getLeftChild();
					} else {
						// pick highest from left
						String highest = ((SortedBinaryNode) right.getLeftChild()).getHighestValue();
						SortedBinaryNode newNode = new SortedBinaryNode(highest);
						newNode.right = right.getRightChild();
						insertEverything(newNode, (AbstractSortedBinaryNode) right.getLeftChild());
						insertEverything(newNode, (AbstractSortedBinaryNode) right.getRightChild());
						right = newNode;
					}
				} else {
					((SortedBinaryNode) right).remove(value);
				}
			}
		}
	}

	private String getHighestValue() {
		String value = getValue();
		if (left != null && left.getValue().compareTo(value) > 0) {
			value = left.getValue();
		}
		if (right != null && right.getValue().compareTo(value) > 0) {
			value = right.getValue();
		}
		return value;
	}
	
	private static void insertEverything(SortedBinaryNode root, AbstractSortedBinaryNode from) {
		if (from != null) {
			root.insert(from.getValue());
			insertEverything(root, from.getLeftChild());
			insertEverything(root, from.getRightChild());
		}
		
	}
}
