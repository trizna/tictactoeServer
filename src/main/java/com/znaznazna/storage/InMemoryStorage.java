package com.znaznazna.storage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class InMemoryStorage<T> implements  StorageApi<T>
{
	private Map<Integer, T> objectStore;
	private LinkedList<Integer> keyStore;
	private int storeCapacity;
	
	public InMemoryStorage(int capacity)
	{
		objectStore = new HashMap<>(capacity);
		keyStore = new LinkedList<>();
		storeCapacity = capacity;
	}
	
	public InMemoryStorage()
	{
		storeCapacity = 100;
		objectStore = new HashMap<>(storeCapacity);
		keyStore = new LinkedList<>();
	}
	
	@Override
	public int store(T storeObject)
	{
		while (objectStore.size() >=  storeCapacity)
		{
			Integer oldestObjectKey = keyStore.removeFirst();
			objectStore.remove(oldestObjectKey);
		}
		
		Integer newObjectKey = storeObject.hashCode();
		keyStore.add(newObjectKey);
		objectStore.put(newObjectKey, storeObject);
		return newObjectKey;
		
	}
	
	@Override
	public T get(int uid)
	{
		return objectStore.get(uid);
	}
	
	@Override
	public T update(int uid, T storeObject)
	{
		return objectStore.put(uid, storeObject);
	}
}
