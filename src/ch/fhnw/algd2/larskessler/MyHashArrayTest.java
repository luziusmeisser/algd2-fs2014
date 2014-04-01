package ch.fhnw.algd2.larskessler;

public class MyHashArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyHashArray arr = new MyHashArray(1000);
		String key1 = "1";
		String value1 = "Eins";
		
		// String key2 = "71443";
		String key2 = "7144453";
		String value2 = "Zwei";
		
		String key3 = "2";
		
		// put element in array
		System.out.println(key1.hashCode() % 1000);
		arr.put(key1, value1);
		String get1 = arr.get(key1);
		System.out.println(get1);
		
		System.out.print("\n");
		
		// element same position
		System.out.println(key2.hashCode() % 1000);
		arr.put(key2, value2);
		String get2 = arr.get(key2);
		System.out.println(get2);
		
		System.out.print("\n");
		
		// element not in array
		System.out.println(key3.hashCode() % 1000);
		String get3 = arr.get(key3);
		System.out.println(get3);
		
		System.out.print("\n");
		
		// same key
		String key4 = "1";
		String value4 = "Nicht Eins";
		
		System.out.println(key4.hashCode() % 1000);
		arr.put(key4, value4);
		String get4 = arr.get(key4);
		System.out.println(get4);
	}

}
