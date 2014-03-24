//Created by Marius Dubach 24.03.2014

package ch.fhnw.algd2.mariusdubach.lesson6;

public class MyHeapTest {
	
	public static void main(String[] args){
		Heap myHeap = new Heap();
		
		for(int i=0; i<26; i++){
			myHeap.offer( String.valueOf(((char) ('A' + i))));
		}
		
		myHeap.offer("K");
		
		for(int i=0; i<26; i++){
			if(i==20){
				System.out.println("bp");
			}
			System.out.println(myHeap.poll());
		}
		
		
	}

}
