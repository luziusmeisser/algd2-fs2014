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
		target.key = key;
		target.value = value;
		if(storage[pos] == null){ //noch kein Element hatte denselben Hash
			storage[pos] = target;			 
		}else{
			Bucket acc = storage[pos];
			while(acc.next != null && ! acc.key.equals(key)){	//lineare Suche zum letzten Element dieser verketteten Liste
				acc = acc.next;
			}
			if(acc.key.equals(key)){	//überschreiben falls schon gleicher Key vorhanden
				acc.value = value;
			}else{
				acc.next = target;	//speichere
			}
		}
		target.key = key;
		target.value = value;
	}

	@Override
	public String get(String key) {
		int hash = key.hashCode();
		int pos = Math.abs(hash%size);
		Bucket start = storage[pos];
		if(start != null){
			if(start.key.equals(key)){
				return start.value;
			}else{	//lineare Suche
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


