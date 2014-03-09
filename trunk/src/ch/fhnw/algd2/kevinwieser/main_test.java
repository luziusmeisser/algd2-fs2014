// Created by Kevin Wieser on 28.02.2014
package ch.fhnw.algd2.kevinwieser;

public class main_test {

	public static void main(String[] args) {
		SkipListe<Integer> s = new SkipListe<>();
		s.add(7);
		s.add(8);
		int i = s.removeFirst();
		System.out.println(i);

		System.out.println(s.countStepsTo(7));
		
		
		
	}

}
