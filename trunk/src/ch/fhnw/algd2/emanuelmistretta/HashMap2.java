// Created by Emanuel Mistretta on 04.05.2014

package ch.fhnw.algd2.emanuelmistretta;

import ch.fhnw.algd2.lesson8.IHashMap2;

public class HashMap2 implements IHashMap2{
    
    private int mapSize;
    private HashItem[] map;
    private double itemCounter;
    
    public HashMap2(){
	this(1000);
    }
    
    public HashMap2(int mapSize){
	this.mapSize = mapSize;
	this.map = new HashItem[this.mapSize];
	this.itemCounter = 0;
    }

    @Override
    public void put(String key, String value) {

	if(updateSizeStatus())
	    resizeMap();

	int hashCode = getHash(key);
	
	while(true){
	    if(map[hashCode] == null || map[hashCode].Key.equals(key)){
		break;
	    }
	    hashCode = getNextHash(hashCode);
	}
	
	this.map[hashCode] = new HashItem(key, value);
    }
    
    //Calculate hash code like in HashMap1
    private int getHash(String key) {
        return  (key.hashCode() & 0x7fffffff) % this.mapSize;
    }
    
    //Get the hashCode for the position
    private int getNextHash(int hashCode) {
        return  (++hashCode & 0x7fffffff) % this.mapSize;
    }
    
    @Override
    public String get(String key) {
	 int hashCode = findItem(key);
	 return (hashCode < 0) ? null : this.map[hashCode].Value;
    }

    @Override
    public String remove(String key) {
	int hashCode = findItem(key);
	
	if(hashCode < 0){
	    return null;
	}

	 map[hashCode].SoftDeleted = true;
	 return map[hashCode].Value;
    }
    
    private int findItem(String key){
	int hashCode = getHash(key);
	//Iterate through all items 
	while(true){   
	    if(map[hashCode] == null){
		return -1;
	    }else if(map[hashCode].Key.equals(key)){
		return hashCode;
	    }
	    hashCode = getNextHash(hashCode);
	}
    }
    
    /*
     * Update the item count and check if map is too small for another item
     */
    private boolean updateSizeStatus(){
	this.itemCounter++;
	if(((double)this.mapSize / this.itemCounter) <= 1){
	    return true;
	}
	return false;
    }
    
    /*
     * Save all items from map into a temporary bigger map.
     * Resize current map and get new hashed items from temp map
     */
    private void resizeMap(){
	HashMap2 temporaryContainer = new HashMap2((int)this.mapSize * 2);
	for(HashItem hashItem : this.map){
	    if(hashItem != null && !hashItem.SoftDeleted){
		temporaryContainer.put(hashItem.Key, hashItem.Value);
	    }
	}
	this.map = temporaryContainer.map;
	this.mapSize = temporaryContainer.mapSize;
	this.itemCounter = temporaryContainer.itemCounter;
    }
    
    private class HashItem{
	public String Value;
	public String Key;
	public boolean SoftDeleted = false;
	
	public HashItem(String key, String value){
	    this.Key = key;
	    this.Value = value;
	}
    }

}
