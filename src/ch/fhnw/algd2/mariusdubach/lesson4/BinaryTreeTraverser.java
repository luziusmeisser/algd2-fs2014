//Created by Marius Dubach 10.03.2014

package ch.fhnw.algd2.mariusdubach.lesson4;

import java.util.ArrayList;
import java.util.HashMap;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser{
	
	StringBuilder string;
	ArrayList<BinaryNode> memory = new ArrayList<>();

	@Override
	public String assemble(BinaryNode root, boolean depthFirst){
		string = new StringBuilder();
		if(depthFirst){
			traverseDeep(root);
			return string.toString();
		}else{
			memory.add(root);
			string.append(root.getValue());
			traverseWide();
			return string.toString();
		}
	}
	
	void traverseDeep(BinaryNode root){
		if(root.getLeftChild() != null){
			traverseDeep(root.getLeftChild());
		}
		string.append(root.getValue());
		if(root.getRightChild() != null){
			traverseDeep(root.getRightChild());
		}		
	}
	
	void traverseWide(){
		while(memory.size() > 0){
			BinaryNode root = memory.get(0);
			if(root.getLeftChild() != null){
				memory.add(root.getLeftChild());
				string.append(root.getLeftChild().getValue());
			}
			if(root.getRightChild() != null){
				memory.add(root.getRightChild());
				string.append(root.getRightChild().getValue());
			}
			memory.remove(root);
		}
	}
}
