//Created by Marius Dubach 31.03.2014

package ch.fhnw.algd2.mariusdubach.lesson7;

import java.util.ArrayList;
import java.util.LinkedList;

import ch.fhnw.algd2.lesson7.exercise.IHashMap;

public class HashMap implements IHashMap{
	
	private int size = 100;
	Bucket[] storage = new Bucket[size];
	

	@Override
	public void put(String key, String value) {
		if(value.equals("-8344150496992527444")){
			System.out.println("bp");
		}
		int hash = key.hashCode();
		int pos = Math.abs(hash%size);
		Bucket target=new Bucket();
		if(storage[pos] == null){
			storage[pos] = target;			 
		}else{
			if(storage[pos].next == null){
				storage[pos].next = target;
			}else{
				Bucket acc = storage[pos].next;
				while(acc.next != null){
					acc = acc.next;
				}
				acc.next = target;
			}
		}
		target.key = key;
		target.value = value;
	}

	@Override
	public String get(String key) {
		if(key.equals("46049")){
			System.out.println("bp");
		}
		int hash = key.hashCode();
		int pos = Math.abs(hash%size);
		Bucket start = storage[pos];
		if(start != null){
			if(start.key.equals(key)){
				return start.value;
			}else{
				Bucket acc = start;
				do{
					if(acc.next.key.equals(key)){
						return acc.next.value;
					}else{
						acc = acc.next;
					}
				}while( acc != null && acc.next != null);
			}
		}
		
		return null;
	}
	
	class Bucket{
		
		Bucket next;
		String key;
		String value;
		
	}

}


