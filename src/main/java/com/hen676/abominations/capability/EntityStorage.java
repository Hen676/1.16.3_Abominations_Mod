package com.hen676.abominations.capability;

import com.hen676.abominations.api.capability.IEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.HashMap;

public class EntityStorage implements Capability.IStorage<IEntity> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IEntity> capability, IEntity instance, Direction side) {
        final HashMap<String, Integer> entities = instance.getEntities();
        ListNBT nbt = new ListNBT();
        entities.forEach((entity,amount)-> {
            CompoundNBT temp = new CompoundNBT();
            temp.putString("entity",entity);
            temp.putInt("amount",amount);
            nbt.add(temp);
        });
        return nbt;
    }

    @Override
    public void readNBT(Capability<IEntity> capability, IEntity instance, Direction side, INBT nbt) {
        if(!(instance instanceof Entity))
            throw new IllegalArgumentException("instance is not default implementation of IEntity");
        ListNBT listNBT = ((ListNBT)nbt);
        listNBT.forEach((temp)->{
            final String entity = ((CompoundNBT)temp).getString("entity");
            final int amount = ((CompoundNBT)temp).getInt("amount");
            instance.addEntity(entity,amount);
        });
    }
}
