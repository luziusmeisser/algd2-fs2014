// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2;

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

}
