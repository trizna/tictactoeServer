package com.znaznazna.storage;

public interface StorageApi<T> {
	
	public int store(T storeObject);
	
	public T get(int uid);
	
	public T update(int uid, T storeObject);

}
