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
		int hash = key.hashCode();
		int pos = Math.abs(hash%size);
		Bucket target=new Bucket();
		if(storage[pos] == null){
			storage[pos] = target;			 
		}else{
			if(storage[pos].next == null){
				storage[pos].next = target;
			}else{
				Bucket next = storage[pos].next;
				while(next != null){
					next = next.next;
				}
				next = target;
			}
		}
		target.key = key;
		target.value = value;
	}

	@Override
	public String get(String key) {
		int pos = Math.abs(key.hashCode()%size);
		Bucket start = storage[pos];
		if(start != null){
			if(start.key.equals(key)){
				return start.value;
			}else{
				Bucket next = start.next;
				do{
					if(next.key.equals(key)){
						return next.value;
					}else{
						next = next.next;
					}
				}while(next.next != null);
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


