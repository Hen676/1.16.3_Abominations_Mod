package com.hen676.abominations.init;

import com.hen676.abominations.api.capability.IEntityStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;
import java.util.HashMap;

public class CapabilityInit {
    public static void init() {
        CapabilityManager.INSTANCE.register(IEntityStorage.class, new Capability.IStorage<IEntityStorage>() {

            @Nullable
            @Override
            public INBT writeNBT(Capability<IEntityStorage> capability, IEntityStorage instance, Direction side) {
                HashMap<String, Integer> hashmap = instance.getEntities();
                CompoundNBT nbt = new CompoundNBT();
                for (String key: hashmap.keySet()) {
                    nbt.putInt(key,hashmap.get(key));
                }
                return nbt;
            }

            @Override
            public void readNBT(Capability<IEntityStorage> capability, IEntityStorage instance, Direction side, INBT nbt) {
                HashMap<String, Integer> hashmap = new HashMap<>();
                for (String key: ((CompoundNBT) nbt).keySet()) {
                    hashmap.put(key,((CompoundNBT) nbt).getInt(key));
                }
                instance.setEntities(hashmap);
            }
        },() -> null);
    }
}
