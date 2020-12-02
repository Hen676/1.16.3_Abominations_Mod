package com.hen676.abominations.capability;

import com.hen676.abominations.Abominations;
import com.hen676.abominations.api.capability.IEntity;
import com.hen676.abominations.tileEntity.ReservoirTileEntity;
import com.hen676.abominations.util.LocationUtil;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityCapability implements ICapabilitySerializable<INBT> {

    @CapabilityInject(IEntity.class)
    public static final Capability<IEntity> ENTITY_STORAGE_CAPABILITY = null;

    private final LazyOptional<IEntity> LazyOptionalInstance;
    private IEntity instance;

    private static final byte DEFAULT_CAPACITY = 10;
    private static final ResourceLocation ID = LocationUtil.location("entity_storage");

    public EntityCapability(IEntity instance) {
        this.instance = instance;
        if(instance != null) {
            this.LazyOptionalInstance = LazyOptional.of(()-> instance);
        } else {
            this.LazyOptionalInstance = LazyOptional.empty();
        }
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(IEntity.class, new EntityStorage(),()-> new Entity(DEFAULT_CAPACITY));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ENTITY_STORAGE_CAPABILITY ? LazyOptionalInstance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return instance != null ? ENTITY_STORAGE_CAPABILITY.getStorage().writeNBT(ENTITY_STORAGE_CAPABILITY,instance,null) : new ListNBT();
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        if(instance != null) {
            ENTITY_STORAGE_CAPABILITY.getStorage().readNBT(ENTITY_STORAGE_CAPABILITY,instance,null,nbt);
        }
    }

    @Mod.EventBusSubscriber(modid = Abominations.MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void attachBlockCapabilities(final AttachCapabilitiesEvent<TileEntity> event) {
            if(event.getObject() instanceof ReservoirTileEntity) {
                final IEntity entityStorage = new Entity(DEFAULT_CAPACITY);
                event.addCapability(ID, new EntityCapability(entityStorage));
            }
        }
    }
}
