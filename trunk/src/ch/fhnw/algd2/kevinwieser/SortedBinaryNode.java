// Created by Kevin Wieser on 10.03.2014

package ch.fhnw.algd2.kevinwieser;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;
import ch.fhnw.algd2.lesson4.exercise.BinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {

	private SortedBinaryNode parent = null;

	public SortedBinaryNode(String value) {
		super(value);
	}

	public void setParent(SortedBinaryNode parent) {
		this.parent = parent;
	}

	@Override
	public int insert(String value) {
		return insert(value, 1);
	}

	private int insert(String value, int level) {
		int compare = value.compareTo(getValue());

		if (compare == 0) {
			return level;
		}

		if (compare < 0) {
			// Mein Value ist kleiner als given value:
			if (left == null) {
				left = new SortedBinaryNode(value);
				((SortedBinaryNode) left).setParent(this);
				return level;
			} else {
				return ((SortedBinaryNode) left).insert(value, level++);
			}
		} else {
			if (right == null) {
				right = new SortedBinaryNode(value);
				((SortedBinaryNode) right).setParent(this);
				return level;
			} else {
				return ((SortedBinaryNode) right).insert(value, level++);
			}
		}
	}

	@Override
	public void remove(String value) {
		int compare = value.compareTo(getValue());

		// ist es genau der wert, an dem ich löschen will:
		if (compare == 0) {
			if (left == null && right == null) {
				// ersetze mich bei den Eltern um einen anderen Node
				if (parent == null) {
					return;
				}
				if (parent.left == this) {
					parent.left = null;
				} else if (parent.right == this) {
					parent.right = null;
				}

				// ist nur mein Linker leer:
			} else if (left == null) {
				if (parent == null) {
					return;
				}  
				if (parent.left == this) {
					// Setze bei den Eltern am Linken Ast meinen Rechten
					parent.left = right;
					((SortedBinaryNode) right).setParent(parent);

					// Haben die Eltern mich als Rechter drin?
				} else if (parent.right == this) {
					parent.right = right;
					((SortedBinaryNode) right).setParent(parent);
				}

			} else if (right == null) {
				if (parent == null) {
					return;
				}
				if (parent.left == this) {
					parent.left = left;
					((SortedBinaryNode) left).setParent(parent);

				} else if (parent.right == this) {
					parent.right = left;
					((SortedBinaryNode) left).setParent(parent);
				}

			} else {
				// gehe nun in den rechten Subbaum und suche den "linkesten"
				// Wert:
				SortedBinaryNode tmpNode = (SortedBinaryNode) right;

				while (tmpNode.left != null) {
					tmpNode = (SortedBinaryNode) tmpNode.left;
				}
				
				remove(tmpNode.getValue());

				// Setze die alten Kinder an den neuen Node:
				tmpNode.left = left;
				tmpNode.right = right;

				// Muss die Childern vom alten Node noch auf die neue Eltern
				// setzen:
				((SortedBinaryNode) tmpNode.left).setParent(tmpNode);
				((SortedBinaryNode) tmpNode.right).setParent(tmpNode);

				// Ersetze ganz unten noch die Parents vom alten auf null
				SortedBinaryNode parentNode = tmpNode.parent;
				if (parentNode.left == tmpNode) {
					parentNode.left = null;
				} else if (parentNode.right == tmpNode) {
					parentNode.right = null;
				}

				// Ersetze mich bei den Parents mit dem tmpNode
				if (parent.left == this) {
					parent.left = tmpNode;
					((SortedBinaryNode) tmpNode).setParent(parent);

				} else if (parent.right == this) {
					parent.right = tmpNode;
					((SortedBinaryNode) tmpNode).setParent(parent);
				}
			}
			
		} else if (compare < 0) {
			if (left != null) {
				((SortedBinaryNode) left).remove(value);

			}
		} else {
			if (right != null) {
				((SortedBinaryNode) right).remove(value);
			}
		}
	}
}
