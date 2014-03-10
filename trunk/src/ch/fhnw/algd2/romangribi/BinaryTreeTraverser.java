// Created by Roman Gribi on 10.03.2014

package ch.fhnw.algd2.romangribi;

import java.util.Queue;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

    @Override
    public String assemble(BinaryNode root, boolean depthFirst) {
        if(depthFirst)
            return assembleDepthFirst(root);
        else
            return assembleByLevel(root);
    }

    private String assembleByLevel(BinaryNode node) {
        if(node == null)
            return "";
        
        String value = "";
        BinaryNode current;
        Queue<BinaryNode> q = new java.util.LinkedList<BinaryNode>();
        q.add(node);
        
        while(!q.isEmpty()) {
            current = q.poll();
            value += current.getValue();
            
            if(current.getLeftChild() != null)
                q.add(current.getLeftChild());
            if(current.getRightChild() != null)
                q.add(current.getRightChild());
        }
        
        return value;
    }
    private String assembleDepthFirst(BinaryNode node) {
        if (node == null)
            return "";

        String value = "";
        value += assembleDepthFirst(node.getLeftChild());
        value += node.getValue();
        value += assembleDepthFirst(node.getRightChild());

        return value;
    }

}
