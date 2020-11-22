package com.hen676.abominations.api.capability;

import java.util.HashMap;

public interface IEntityStorage {
    HashMap<String, Integer> getEntities();
    void setEntities(HashMap<String, Integer> hashMap);
    int getMaxStorage();
    boolean addEntity(String name,int amount);
    boolean removeEntity(String name,int amount);
}
