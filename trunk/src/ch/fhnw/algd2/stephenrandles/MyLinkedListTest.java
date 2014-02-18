// Created by Stephen Randles 18.02.2014

package ch.fhnw.algd2.stephenrandles;

public class MyLinkedListTest {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		
		printLinkedList(list);
		list.remove("D");
		printLinkedList(list);
	}
	
	public static void printLinkedList(LinkedList<String> list) {
		for (String s : list) {
			System.out.print(s + " -> ");
		}
		System.out.println();
	}

}
