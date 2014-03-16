// Created by Christian Guedel on 16.03.2014

package ch.fhnw.algd2.christianguedel;

import java.util.ArrayList;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

    @Override
    public String assemble(BinaryNode root, boolean depthFirst) {
        
        StringBuilder sb = new StringBuilder();
        
        if (depthFirst)
            traverseDepthFirst(root, sb);
        else
            traverseDepthLast(root, sb);
        
        return sb.toString();
    }

    private void traverseDepthFirst(BinaryNode node, StringBuilder sb)
    {
        if (node.getLeftChild() != null) traverseDepthFirst(node.getLeftChild(), sb);
        sb.append(node.getValue());
        if (node.getRightChild() != null) traverseDepthFirst(node.getRightChild(), sb);
    }
    
    private void traverseDepthLast(BinaryNode node, StringBuilder sb)
    {
        ArrayList<BinaryNode> nodeList = new ArrayList<BinaryNode>();
        nodeList.add(node);
        
        while (nodeList.size() > 0)
        {
            BinaryNode top = nodeList.get(0);
            sb.append(top.getValue());
            
            if (top.getLeftChild() != null) nodeList.add(top.getLeftChild());
            if (top.getRightChild() != null) nodeList.add(top.getRightChild());
            
            nodeList.remove(top);
        }
    }
}
