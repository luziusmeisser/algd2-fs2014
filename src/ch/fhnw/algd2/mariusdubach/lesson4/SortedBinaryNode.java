//Created by Marius Dubach 10.03.2014

package ch.fhnw.algd2.mariusdubach.lesson4;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;
import ch.fhnw.algd2.lesson4.exercise.BinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode{
	
	private static SortedBinaryNode father=null;
	
	public SortedBinaryNode(String value) {
		super(value);
		if(father == null) father = this;		
	}

	@Override
	public int insert(String value) {
		int comp = value.compareTo(this.getValue());
		if(comp == 0) return 0;
		if(comp < 0){	//value ist kleiner
			if(left == null){
				left = new SortedBinaryNode(value);
				return 1;
			}else{
				return 1 + ((AbstractSortedBinaryNode)left).insert(value);
			}
		}else{ // value ist groesser
			if(right == null){
				right = new SortedBinaryNode(value);
				return 1;
			}else{
				return 1 + ((AbstractSortedBinaryNode)right).insert(value);
			}
		}
	}
	
	private int hasSons(){
		int children=0;
		if (left != null) children++;
		if (right != null) children++;
		return children;
	}
	
	private SortedBinaryNode getHighestKey(SortedBinaryNode bn){
		if(bn.getLeftChild() != null){
			return getHighestKeyRec((SortedBinaryNode)bn.getLeftChild());
		}else{
			return bn;
		}
	}
	
	private SortedBinaryNode getHighestKeyRec(SortedBinaryNode bn){
		while(bn.getRightChild() != null){
			father = bn;
			bn = (SortedBinaryNode) bn.getRightChild();
		}
		return bn;
	}

	@Override
	public void remove(String value) {
		if(left != null && value.compareTo(getValue()) < 0){			
			if(((SortedBinaryNode)left).hasSons() == 0 && left.getValue().equals(value)){
				left = null;
				return;
			}else if(((SortedBinaryNode)left).hasSons() == 1 && left.getValue().equals(value)){
				if(left.getLeftChild() != null){
					this.left = left.getLeftChild();
					return;
				}
				if(left.getRightChild() != null) {
					this.left = left.getRightChild();
					return;
				}
			}else if(((SortedBinaryNode)left).hasSons() == 2 && left.getValue().equals(value)){
				SortedBinaryNode repl = getHighestKey((SortedBinaryNode)left);
				SortedBinaryNode replFather = father;
				
				((SortedBinaryNode)repl).left = this.left.getLeftChild();
				((SortedBinaryNode)repl).right = this.left.getRightChild();				
				this.left = repl;
				deleteNodeFromFather(replFather, replFather);
				return;			
			}
			father = this;
			((SortedBinaryNode)left).remove(value);
			return;
			
		}else if(right != null && value.compareTo(getValue()) > 0){
			if(((SortedBinaryNode)right).hasSons() == 0 && right.getValue().equals(value)){
				right = null;
				return;
			}else if(((SortedBinaryNode)right).hasSons() == 1 && right.getValue().equals(value)){
				if(right.getLeftChild() != null){
					this.right = right.getLeftChild();
					return;
				}
				if(right.getRightChild() != null){
					this.right = right.getRightChild();
					return;
				}
			}else if(((SortedBinaryNode)right).hasSons() == 2 && right.getValue().equals(value)){
				SortedBinaryNode repl = getHighestKey((SortedBinaryNode)left);
				SortedBinaryNode replFather = father;
				
				((SortedBinaryNode)repl).left = this.right.getLeftChild();
				((SortedBinaryNode)repl).right = this.right.getRightChild();				
				this.right = repl;				
				deleteNodeFromFather(replFather, replFather);
				return;
				
			}
			father = this;
			((SortedBinaryNode)right).remove(value);
			return;
		}
		
	}
	
	void deleteNodeFromFather(SortedBinaryNode replFather, SortedBinaryNode repl){
		if(replFather.left == repl) {
			replFather.left = null;
			return;
		}else{
			replFather.right = null;
			return;
		}
	}

}
