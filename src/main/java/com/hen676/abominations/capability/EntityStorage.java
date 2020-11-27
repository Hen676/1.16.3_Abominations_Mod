package com.hen676.abominations.capability;

import com.hen676.abominations.api.capability.IEntityStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;

public class EntityStorage implements IEntityStorage, INBTSerializable<ListNBT> {
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

    @Override
    public ListNBT serializeNBT() {
        ListNBT listNBT = new ListNBT();
        entities.forEach((entity,amount)->{
            CompoundNBT temp = new CompoundNBT();
            temp.putString("entity",entity);
            temp.putInt("amount",amount);
            listNBT.add(temp);
        });
        return listNBT;
    }

    @Override
    public void deserializeNBT(ListNBT nbt) {
        nbt.forEach((temp)-> {
            final String entity = ((CompoundNBT)temp).getString("entity");
            final int amount = ((CompoundNBT)temp).getInt("amount");
            entities.put(entity,amount);
        });
    }
}
