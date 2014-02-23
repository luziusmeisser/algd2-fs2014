// Created by Kevin Wieser on 22.02.2014
package ch.fhnw.algd2.kevinwieser;

import java.util.Iterator;

import ch.fhnw.algd2.Clock;

public class main {

	public static void main(String[] args) {
		//ch.fhnw.algd2.luzius.LinkedList<Integer> list = new ch.fhnw.algd2.luzius.LinkedList<>();
		//ch.fhnw.algd2.yannickaugstburger.LinkedList<Integer> list = new ch.fhnw.algd2.yannickaugstburger.LinkedList<>();
		ch.fhnw.algd2.kevinwieser.LinkedList<Integer> list = new ch.fhnw.algd2.kevinwieser.LinkedList<>();
		
		 for (int i=0; i<10000; i++){
             list.add(i);
         }
         Clock c1 = new Clock();
         for (int i=9999; i>=0; i--){
             list.remove(i); // dauert länger -.-
         }
         c1.stop();
         System.out.println(c1.toString());
         for (int i=0; i<10000; i++){
             list.add(i);
         }
         Clock c2 = new Clock();
         for (int i=0; i<10000; i++){
             list.remove(i);	// ist schneller 
         }
         c2.stop();
         System.out.println(c2.toString());
         
         System.out.println(c1.tookMuchLongerThan(c2) || c2.tookMuchLongerThan(c1));
		
		
//		Iterator<Integer> it = list.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}

	}

}
