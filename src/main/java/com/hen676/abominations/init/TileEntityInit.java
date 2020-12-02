package com.hen676.abominations.init;

import com.hen676.abominations.Abominations;
import com.hen676.abominations.tileEntity.ForgeTileEntity;
import com.hen676.abominations.tileEntity.IncineratorTileEntity;
import com.hen676.abominations.tileEntity.ReservoirTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Abominations.MOD_ID);

    public static void init() {
        TILE_ENTITY_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    /**
     * TileEntities
     */
    public static final RegistryObject<TileEntityType<ForgeTileEntity>> FORGE_TILE_ENTITY = TILE_ENTITY_TYPE.register("forge", () -> TileEntityType.Builder.create(ForgeTileEntity::new, BlockInit.FORGE.get()).build(null));
    public static final RegistryObject<TileEntityType<ReservoirTileEntity>> RESERVOIR_TILE_ENTITY = TILE_ENTITY_TYPE.register("reservoir", () -> TileEntityType.Builder.create(ReservoirTileEntity::new, BlockInit.RESERVOIR.get()).build(null));
    public static final RegistryObject<TileEntityType<IncineratorTileEntity>> INCINERATOR_TILE_ENTITY = TILE_ENTITY_TYPE.register("incinerator", () -> TileEntityType.Builder.create(IncineratorTileEntity::new, BlockInit.INCINERATOR.get()).build(null));
}