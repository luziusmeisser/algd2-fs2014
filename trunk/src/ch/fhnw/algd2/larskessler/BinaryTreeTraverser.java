package ch.fhnw.algd2.larskessler;

import java.util.ArrayList;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {
	String s = "";
	ArrayList<BinaryNode> listFrom;
	ArrayList<BinaryNode> listNext;
	
	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		if(depthFirst) {
			s = "";
			s = assembleDepth(root);
		} else {
			s = "";
			listFrom = new ArrayList<BinaryNode>();
			listFrom.add(root);
			s = assembleWide(listFrom);
			System.out.println(s+"\n");
		}
		
		return s;
	}
	
	private String assembleDepth(BinaryNode node) {
		if(node.getLeftChild() != null) {
			s = assembleDepth(node.getLeftChild());
		} else {
			s = "";
		}
		
		s += node.getValue();
		
		if(node.getRightChild() != null) {
			s += assembleDepth(node.getRightChild());
		} else {
			s += "";
		}
		
		return s;
	}
	
	private String assembleWide(ArrayList<BinaryNode> listFrom) {
		listNext = new ArrayList<BinaryNode>();
		
		/*
		if(listFrom.size() < 1) {
			listNext.add(node);
			assembleWide(node, listNext);
		}
		*/
		
		for (BinaryNode listElement : listFrom) {
			if(listElement.getLeftChild() != null) {
				listNext.add(listElement.getLeftChild());
			}
			if(listElement.getRightChild() != null) {
				listNext.add(listElement.getRightChild());
			}
			s += listElement.getValue();
		}
		
		if(listFrom.size() > 0) {
			assembleWide(listNext);
		}
		
		return s;
	}
}
