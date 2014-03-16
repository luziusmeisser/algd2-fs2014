// Created by Martin Eberle on 14.03.2014

package ch.fhnw.algd2.martineberle;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode{
SortedBinaryNode parent;
int lastCompare;

	public SortedBinaryNode(String value) {
		super(value);
	}

	@Override
	public int insert(String value) {
		int comp = value.compareTo(this.getValue());
		//if equal, end
		//if smaller & nextLeft == null, insert
			//if smaller, go left
		//if bigger & nextRight == null, insert
			//if bigger, go right
		
		if(comp == 0){
			return 0;
		}
		if(comp < 0){
			if(this.left == null){
				this.left = new SortedBinaryNode(value);
				return 1;
			}
			else {
				//each step equals +1, so adds all steps needed together and returns them.
				return 1 + this.getLeftChild().insert(value);
			}
			
		}
		if(comp > 0){
			if(this.right == null){
				this.right = new SortedBinaryNode(value);
				return 1;
			}
			else {
				return 1 + this.getRightChild().insert(value);
			}
		}
		//actually unreachable code...
		return 0;
	}

	@Override
	public void remove(String value) {
		removeIt(value, this, lastCompare);
	}
	public void removeIt(String value, SortedBinaryNode parentNode, int lastComparison){
		parent = parentNode;
		lastCompare = lastComparison;
		int comp = value.compareTo(getValue());
		//if value found, check child values
		if(comp == 0){
			//if no Children, remove node immediately
			if(this.left == null && this.right == null){
				if(lastCompare < 0){
					parent.left = null;
					return;
				}
				if(lastCompare > 0){
					parent.right = null;
					return;
				}
			}
			//If one child(right), bend pointer from this to child
			if(this.left == null && this.right != null){
				if(lastCompare < 0){
					parent.left = this.right;
					return;
				}
				if(lastCompare > 0){
					parent.right = this.right;
					return;
				}
			}
			//If one child(left), bend pointer from this to child
			if(this.left != null && this.right == null){
				if(lastCompare < 0){
					parent.left = this.left;
					return;
				}
				if(lastCompare > 0){
					parent.right = this.left;
					return;
				}
			}
			//If two children, find lowest of right part, replace this with new node and delete original lowest
			if(this.left != null && this.right != null){
				String lowValue = this.getRightChild().findLowestValue();
				SortedBinaryNode newNode = new SortedBinaryNode(lowValue);
				newNode.left = this.left;
				newNode.right = this.right;
				((SortedBinaryNode)newNode.getRightChild()).removeIt(lowValue,newNode, 1);
				if(lastCompare < 0){
					parent.left = newNode;
					return;
				}
				if(lastCompare > 0){
					parent.right = newNode;
					return;
				}
			}
		}
		if(comp < 0){
			if(this.left != null){
				((SortedBinaryNode) this.getLeftChild()).removeIt(value, this, comp);
			}
		}
		if(comp > 0){
			if(this.right != null){
				((SortedBinaryNode) this.getRightChild()).removeIt(value,  this,  comp);
			}
		}
	}


}
