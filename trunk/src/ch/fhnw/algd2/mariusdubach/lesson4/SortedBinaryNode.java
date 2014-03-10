//Created by Marius Dubach 10.03.2014

package ch.fhnw.algd2.mariusdubach.lesson4;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode{
	
	public SortedBinaryNode(String value) {
		super(value);
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

	@Override
	public void remove(String value) {
		// TODO Auto-generated method stub		
	}

}
