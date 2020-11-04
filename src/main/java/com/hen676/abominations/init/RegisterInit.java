package com.hen676.abominations.init;

import com.hen676.abominations.Abominations;
import com.hen676.abominations.block.ForgeBlock;
import com.hen676.abominations.block.ReservoirBlock;
import com.hen676.abominations.entity.GeminiEntity;
import com.hen676.abominations.inventory.ReservoirContainer;
import com.hen676.abominations.item.CapsuleItem;
import com.hen676.abominations.item.CodexItem;
import com.hen676.abominations.item.SceptreItem;
import com.hen676.abominations.tileEntity.ForgeTileEntity;
import com.hen676.abominations.tileEntity.ReservoirTileEntity;
import com.hen676.abominations.util.LocationUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterInit {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Abominations.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Abominations.MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Abominations.MOD_ID);
    private static final DeferredRegister<ContainerType<?>> CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS, Abominations.MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITIES, Abominations.MOD_ID);

    /**
     * Entity Type Builders
     */
    private static EntityType<GeminiEntity> GEMINI_ENTITY_TYPE = EntityType.Builder.create(GeminiEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F).immuneToFire().trackingRange(32).build(LocationUtil.location("gemini").toString());

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITY_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINER_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
        //Dimensions
    }

    /**
     * Blocks
     */
    public static final RegistryObject<Block> FORGE = BLOCKS.register("forge",()-> new ForgeBlock());
    public static final RegistryObject<Block> RESERVOIR = BLOCKS.register("reservoir",()-> new ReservoirBlock());

    /**
     * Items
     */
    // Block Items
    public static final RegistryObject<Item> FORGE_ITEM = ITEMS.register("forge",() -> new BlockItem(FORGE.get(),Abominations.GROUP));
    public static final RegistryObject<Item> RESERVOIR_ITEM = ITEMS.register("reservoir",() -> new BlockItem(RESERVOIR.get(),Abominations.GROUP));
    //public static final RegistryObject<Item> DIS_INTEGRATOR;
    //public static final RegistryObject<Item> LINKER;
    // Default Items
    public static final RegistryObject<Item> LASER = ITEMS.register("laser",() -> new Item(Abominations.GROUP));
    // Tool Items
    public static final RegistryObject<Item> SCEPTRE = ITEMS.register("sceptre",() -> new SceptreItem(Abominations.GROUP));
    public static final RegistryObject<Item> CODEX = ITEMS.register("codex",() -> new CodexItem(Abominations.GROUP));
    public static final RegistryObject<Item> PERSONAL_DIS_INTEGRATOR = ITEMS.register("personal_dis_integrator",() -> new Item(Abominations.GROUP));
    public static final RegistryObject<Item> CAPSULE = ITEMS.register("capsule",() -> new CapsuleItem(Abominations.GROUP));
    // Mob Eggs
    public static final RegistryObject<Item> GEMINI_MOB_EGG = ITEMS.register("gemini_mob_egg", () -> new SpawnEggItem(GEMINI_ENTITY_TYPE, 0xde16a2,0xeef520,Abominations.GROUP));

    /**
     * TileEntities
     */
    public static final RegistryObject<TileEntityType<ForgeTileEntity>> FORGE_TILE_ENTITY = TILE_ENTITY_TYPE.register("forge",() -> TileEntityType.Builder.create(ForgeTileEntity::new, FORGE.get()).build(null));
    public static final RegistryObject<TileEntityType<ReservoirTileEntity>> RESERVOIR_TILE_ENTITY = TILE_ENTITY_TYPE.register("reservoir",() -> TileEntityType.Builder.create(ReservoirTileEntity::new, RESERVOIR.get()).build(null));

    /**
     * Containers
     */
    public static final RegistryObject<ContainerType<ReservoirContainer>> RESERVOIR_CONTAINER = CONTAINER_TYPE.register("reservoir",() -> IForgeContainerType.create(((windowId, inv, data) -> new ReservoirContainer(windowId, inv.player.getEntityWorld(), data.readBlockPos(), inv, inv.player))));

    /**
     * Entities
     */
    public static final RegistryObject<EntityType<GeminiEntity>> GEMINI_ENTITY = ENTITY_TYPE.register("gemini",() -> GEMINI_ENTITY_TYPE);
}
