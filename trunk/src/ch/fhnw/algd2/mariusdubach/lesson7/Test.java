//Created by Marius Dubach 31.03.2014

package ch.fhnw.algd2.mariusdubach.lesson7;

public class Test {

	public static void main(String[] args) {

		HashMap map = new HashMap();
		
		System.out.println("FB".hashCode());
		System.out.println("Ea".hashCode());
		
		map.put("FB", "val1");
		map.put("Ea", "val2");
		
		System.out.println(map.get("FB"));
		System.out.println(map.get("Ea"));

	}

}
