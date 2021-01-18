package com.hen676.abominations.init;

import com.hen676.abominations.Abominations;
import com.hen676.abominations.inventory.container.ReservoirContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerInit {
    private static final DeferredRegister<ContainerType<?>> CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS, Abominations.MOD_ID);

    public static void init() {
        CONTAINER_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /**
     * Containers
     */
    public static final RegistryObject<ContainerType<ReservoirContainer>> RESERVOIR_CONTAINER = CONTAINER_TYPE.register("reservoir", () -> IForgeContainerType.create(((windowId, inv, data) -> new ReservoirContainer(windowId, inv.player.getEntityWorld(), data.readBlockPos(), inv, inv.player))));

}
