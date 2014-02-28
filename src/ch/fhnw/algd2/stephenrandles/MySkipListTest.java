// Created by Stephen Randles 28.02.2014

package ch.fhnw.algd2.stephenrandles;



public class MySkipListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SkipList<Integer> list = new SkipList<>();
		
		list.add(3);
		list.printList();
		list.add(1);
		list.printList();
		list.add(8);
		list.printList();
		list.add(2);
		list.printList();
		list.add(1);
		list.printList();
		list.add(10);
		list.printList();

	}

}
