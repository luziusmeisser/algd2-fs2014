// Created by Kevin Wieser on 10.03.2014

package ch.fhnw.algd2.kevinwieser;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;
import ch.fhnw.algd2.lesson4.exercise.BinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {

	private SortedBinaryNode parent = null;
	private int LEVEL = 0;
	
	public SortedBinaryNode(String value) {
		super(value);
	}

	
	@Override
	public int insert(String value) {
		return insert(value, this);
	}
	
	private int insert(String value, SortedBinaryNode parent) {
		int compare = getValue().compareTo(value);

		if (compare == 0) {
			int tmpLevel = LEVEL;
			LEVEL = 0;
			return tmpLevel;
		}

		if (compare < 0) {
			// Mein Value ist kleiner als given value:
			if (right == null) {
				right = new SortedBinaryNode(value);
				int tmpLevel = LEVEL++;
				// ansonsten wird level bei jedem Insert erhöht
				LEVEL = 0;
				this.parent = parent;
				return tmpLevel;
			} else {
				LEVEL++;
				return ((SortedBinaryNode) right).insert(value);
			}
		} else if (compare > 0) {
			if (left == null) {
				left = new SortedBinaryNode(value);
				int tmpLevel = LEVEL++;
				// ansonsten wird level bei jedem Insert erhöht
				LEVEL = 0;
				this.parent = parent;
				return tmpLevel;
			} else {
				LEVEL++;
				return ((SortedBinaryNode) left).insert(value);
			}
		}
		return 0;
	}

	@Override
	public void remove(String value) {
		int compare = getValue().compareTo(value);
		
		// ist es genau der wert, an dem ich löschen will:
		if (compare == 0) {
			if (left == null && right == null) {
				// ersetze mich bei den Eltern um einen anderen Node
				if (parent == null) {
					return;
				} else {
					if (parent.left == this) {
						parent.left = null;
					}

					if (parent.right == this) {
						parent.right = null;
					}
				}
			} else if (left == null) {
				if (parent.left == this) {
					parent.left = right;
				}

				if (parent.right == this) {
					parent.right = right;
				}
			} else if (right == null) {
				if (parent.left == this) {
					parent.left = left;
				}

				if (parent.right == this) {
					parent.right = left;
				}
			} else if (right != null && left != null) {
				// gehe nun in den rechten Subbaum und suche den "linkesten" Wert:
				SortedBinaryNode tmpNode = this;
				while(tmpNode != null) {
					tmpNode = (SortedBinaryNode) tmpNode.left;
				}
				
				// lösche den tmpNode
				remove(tmpNode.getValue());
				
				// Ersetze mich bei den Parents mit dem tmpNode
				if (parent.left == this) {
					parent.left = tmpNode;
				}

				if (parent.right == this) {
					parent.right = tmpNode;
				}
		
			}
		} else if (compare < 0) {
			if (left != null) {
				((SortedBinaryNode)left).remove(value);
			}
		} else {
			if (right != null) {
				((SortedBinaryNode)right).remove(value);
			}
		}
	}

}
