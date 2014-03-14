// Created by Martin Eberle on 10.03.2014

package ch.fhnw.algd2.martineberle;

import java.util.ArrayList;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {
	String word = new String();
	public ArrayList<String> tree = new ArrayList<String>();
	//BinaryNode current; -> must be methodVariable...
	BinaryNode parent;
	
	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		word = "";
	if(depthFirst){
		traverseLeft(root);
		}
	if(!depthFirst){
		otherStuff(root);
	}
	
	return word;
	
	}
	
	public void otherStuff(BinaryNode root){
		traverse(root, 0);
		word = "";
		for (int i = 0; i < tree.size();i++){
			word+=tree.get(i);
		}
	}
	
	public void traverse(BinaryNode next, int lvl){
		int x = lvl;
		BinaryNode curr = next;
		//create new level in Array if not exists
		if(tree.size() == x || tree.size() == 0){
			String empty = new String("");
			tree.add(x,empty);
		}
		//add value of current node to current level of tree
		joinText(x, curr.getValue());
		//actual value written, go to nxt level
		x++;
		if(curr.getLeftChild() != null){
			traverse(curr.getLeftChild(),x);
		}
		if(curr.getRightChild() != null){
			traverse(curr.getRightChild(),x);
		}
		
	}
	
	//concatenates the text that is already from that lvl with the new value
	public String joinText(int lvl, String toAdd){
		String temp = tree.remove(lvl);
		temp += toAdd;
		tree.add(lvl, temp);
		return temp;
	}
	public void traverseLeft(BinaryNode currentNode){
			BinaryNode current;
			current = currentNode;
			
			if(current.getLeftChild() != null){
				traverseLeft(current.getLeftChild());
			}
			word += current.getValue();
			if(current.getRightChild() != null){
					traverseLeft(current.getRightChild());
			}	
			}
}
