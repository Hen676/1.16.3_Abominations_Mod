package com.hen676.abominations.capability;

import com.hen676.abominations.api.capability.IEntityStorage;

import java.util.HashMap;

public class EntityStorage implements IEntityStorage {
    private HashMap<String,Integer> entities;
    private byte maxStorage;
    private short maxSlotStorage = 256;

    public EntityStorage(byte maxStorage) {
        this.maxStorage = maxStorage;
        this.entities = new HashMap<String, Integer>();
    }

    public int getEntityAmount(String name) {
        if(containsEntity(name)) {
            return entities.get(name);
        }
        return 0;
    }

    private boolean containsEntity(String name) {
        return entities.containsKey(name);
    }

    public int getMaxStorage() {
        return maxStorage;
    }

    @Override
    public boolean addEntity(String name, int amount) {
        if(!containsEntity(name)) {
            if(entities.size() <= maxStorage && amount <= maxSlotStorage) {
                entities.put(name,amount);
                return true;
            }
        } else if ((entities.get(name) + amount) <= maxSlotStorage) {
            entities.put(name, amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEntity(String name, int amount) {
        if(containsEntity(name)) {
            Integer stored = entities.get(name);
            if(stored > amount) {
                entities.put(name,stored-amount);
                return true;
            }
            if(stored == amount) {
                entities.remove(name);
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Integer> getEntities() {
        return entities;
    }

    @Override
    public void setEntities(HashMap<String, Integer> hashMap) {
        entities = hashMap;
    }
}
