// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2;

import ch.fhnw.algd2.lesson3.exercise.Node;

public class Util {

	private static final String PREFIX = "ch.fhnw.algd2.";

	public static String getAuthor(Object o) {
		String pack = o.getClass().getPackage().getName();
		if (pack.startsWith(PREFIX)) {
			pack = pack.substring(PREFIX.length());
			int dot = pack.indexOf('.');
			if (dot == -1){
				return pack;
			} else {
				return pack.substring(0, dot);
			}
		} else {
			return "unknown";
		}
	}

	public static Node[] add(Node[] nodes, Node other, Node[] nodes2) {
		System.arraycopy(nodes, 0, nodes2, 0, nodes.length);
		nodes2[nodes.length] = other;
		return nodes2;
	}

	public static boolean contains(Node[] neighbors, Node tn) {
		for (Node n: neighbors){
			if (n == tn){
				return true;
			}
		}
		return false;
	}

}
