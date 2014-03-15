package ch.fhnw.algd2.larskessler;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {
	
	private static SortedBinaryNode parent;
	private static char direction = 'x';

	public SortedBinaryNode(String value) {
		super(value);
	}

	@Override
	public int insert(String value) {
		return insertCount(value, 0);
	}

	@Override
	public void remove(String value) {
		int compareValue = value.compareTo(getValue());
		
		if(compareValue == 0) {
			// found deletable node
			if(hasChildren()) {
				// System.out.println("has children");
				if(countChildren() == 1) {
					// 1 child
					if(leftOrRightChild() == 'l') {
						if(direction == 'l') {
							parent.left = getLeftChild();
						} else {
							parent.right = getLeftChild();
						}
					} else {
						if(direction == 'l') {
							parent.left = getRightChild();
						} else {
							parent.right = getRightChild();
						}
					}
				} else {
					// 2 childs
					// find and delete biggest Node in left tree
					SortedBinaryNode biggest = findBiggest((SortedBinaryNode) this.left);
					
					// take biggest node to the deleting position
					biggest.left = getLeftChild();
					biggest.right = getRightChild();
										
					if(direction == 'l') {
						parent.left = biggest;
					} else {
						parent.right = biggest;
					}
				}
			} else {
				// delete				
				setNull(direction);
			}
		} else if(compareValue > 0) {
			//System.out.println("left: "+this.getLeftChild());
			//System.out.println("right: "+this.getRightChild());
			
			if(this.getRightChild() == null) {
				return;
			} else {
				direction = 'r';
				parent = this;
				((SortedBinaryNode) right).remove(value);
			}
		} else {
			if(this.getLeftChild() == null) {
				return;
			} else {
				direction = 'l';
				parent = this;
				((SortedBinaryNode) left).remove(value);
			}			
		}
	}
	
	private SortedBinaryNode findBiggest(SortedBinaryNode node) {
		SortedBinaryNode current = node;
		SortedBinaryNode subParent = node;
		
		while(current.getRightChild() != null) {
			subParent = current;
			// System.out.println("parent: "+parent.getValue());
			
			current = (SortedBinaryNode) current.getRightChild();
			// System.out.println("current: "+current.getValue());
		}
		
		try {
			// System.out.println("return biggest from left side: "+current.getValue());
			return current;
		} finally {
			// System.out.println("changed parent right side to null (delete original "+current.getValue()+" position)");
			subParent.right = null;
		}
	}

	private char leftOrRightChild() {		
		if(getLeftChild() != null) return 'l';
		return 'r';
	}

	private void setNull(char direction) {
		switch(direction) {
			case 'l':
				parent.left = null;
				break;
			case 'r':
				parent.right = null;
				break;
			default:
				System.out.println("You should not be here...");
				break;
		}
	}

	private int countChildren() {
		int count = 0;
		if(getLeftChild() != null) count++;
		if(getRightChild() != null) count++;
		return count;
	}

	private boolean hasChildren() {
		return (getLeftChild() != null || getRightChild() != null);
	}

	private int insertCount(String value, int count) {
		int compareValue = value.compareTo(getValue());
		
		//System.out.println(compareValue);
		
		if(compareValue == 0) {
			// System.out.println("hit!");
			return count;
		};
		
		if(compareValue > 0) {
			SortedBinaryNode rightChild = (SortedBinaryNode) getRightChild();
			// System.out.println("bigger");
			if(rightChild == null) {
				right = new SortedBinaryNode(value);
				return ++count;
			} else {
				((SortedBinaryNode) right).insertCount(value, ++count);
			}
		}
		
		if(compareValue < 0) {
			SortedBinaryNode leftChild = (SortedBinaryNode) getLeftChild();
			// System.out.println("smaller");
			if(leftChild == null) {
				left = new SortedBinaryNode(value);
				return ++count;
			} else {
				((SortedBinaryNode) left).insertCount(value, ++count);
			}
		}
		
		return 0;
	}
}