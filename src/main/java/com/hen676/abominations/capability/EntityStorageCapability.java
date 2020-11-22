package com.hen676.abominations.capability;

import com.hen676.abominations.Abominations;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;

//TODO: Implement Entity Storage Capability
@Mod.EventBusSubscriber(modid = Abominations.MOD_ID)
public class EntityStorageCapability {
    public static void attachBlockCapabilities(final AttachCapabilitiesEvent<TileEntity> event) {
        //event.addCapability();
    }
}
