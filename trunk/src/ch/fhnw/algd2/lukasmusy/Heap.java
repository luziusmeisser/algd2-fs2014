//created by lukas.musy on Mar 26, 2014

package ch.fhnw.algd2.lukasmusy;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

import java.util.ArrayList;

public class Heap implements IHeap {
 ArrayList<String> data;
 
 public Heap() {
     data = new ArrayList<String>();
 }

 @Override
 synchronized public void offer(String s) {
     data.add(s);
     siftUp(data.size() - 1);
 }

 @Override
 public String peek() {
     if (data.size() == 0)
         return null;
     return data.get(0);
 }

 @Override
 synchronized public String poll() {
     if (data.size() == 0)
         return null;
     if (data.size() == 1)
         return data.remove(0);
     String ret = data.get(0);
     String last = data.remove(data.size() - 1);
     data.set(0, last);
     siftDown(0);
     return ret;
 }

 private void siftUp(int index) {
     if (index <= 0)
         return;
     if(data.get(index).compareTo(data.get(getParent(index))) < 0) {
         swap(index, getParent(index));
         siftUp(getParent(index));
     }
 }
 
 private void siftDown(int index) {
     String parent = data.get(index);
     String left = getLeft(index) < data.size() ? data.get(getLeft(index)) : "" + (char)255;
     String right = getRight(index) < data.size() ? data.get(getRight(index)) : "" + (char)255;

     if (parent.compareTo(left) <= 0 && parent.compareTo(right) <= 0)
         return;

     else if(left.compareTo(right) <= 0) { 
         swap(index, getLeft(index));
         siftDown(getLeft(index));
     }
     else { 
         swap(index, getRight(index));
         siftDown(getRight(index));
     }
 }
 
 private void swap(int iA, int iB) {
     String a = data.get(iA);
     data.set(iA, data.get(iB));
     data.set(iB, a);
 }
 
 private int getLeft(int index) {
     return index * 2 + 1;
 }
 
 private int getRight(int index) {
     return index * 2 + 2;
 }
 
 private int getParent(int index) {
     return (index - 1) / 2;
 }
}
