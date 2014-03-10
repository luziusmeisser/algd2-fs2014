// Created by Stephan Brunner on 10.03.2014

package ch.fhnw.algd2.stephanbrunner;

import java.util.ArrayList;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

    @Override
    public String assemble(BinaryNode root, boolean depthFirst) {
         if (depthFirst)
             return assambleDepth(root);
         else 
             return assambleWide(root);
    }
    
    private String assambleWide(BinaryNode root) {
        // init
        ArrayList<BinaryNode> nextLevel = new ArrayList<BinaryNode>();
        if (root != null) 
            nextLevel.add(root);
        ArrayList<BinaryNode> thisLevel;
        String ret = "";
        
        do {
            // switch to next level
            thisLevel = new ArrayList<BinaryNode>(nextLevel);
            nextLevel.clear();
            
            // add values of this level to return value and create list of next levels nodes.
            for (BinaryNode b: thisLevel) {
                ret += b.getValue();
                if (b.getLeftChild() != null) 
                    nextLevel.add(b.getLeftChild());
                if (b.getRightChild() != null)
                    nextLevel.add(b.getRightChild());
            }
        } while (nextLevel.size() > 0);
        
        return ret;
    }
    
    private String assambleDepth(BinaryNode root) {
        String left;
        String right;
        
        if (root.getLeftChild() == null)
            left = "";
        else
            left = this.assambleDepth(root.getLeftChild());
        
        if (root.getRightChild() == null)
            right = "";
        else
            right = this.assambleDepth(root.getRightChild());
            
        return left + root.getValue() + right;
    }
}
